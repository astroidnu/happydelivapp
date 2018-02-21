package com.happydeliv.happydelivapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import com.happydeliv.happydelivapp.HappyDelivApp
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.utils.AppConstants
import com.happydeliv.happydelivapp.utils.RxBus
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 21/02/18.
 * Android Engineer
 * SCO Project
 */
class SMSReceiver : BroadcastReceiver(){
    private val LOG_TAG = javaClass.name

    @Inject
    lateinit var mRxBus : RxBus

    init {
        HappyDelivApp.appComponent
                .inject(this)
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val data = intent!!.getExtras()
            val pdus = data!!.get("pdus") as Array<*>
            Log.d(LOG_TAG, "TEST SMSRecieve")

            for (key in pdus.indices) {
                val smsMessage = SmsMessage.createFromPdu(pdus[key] as ByteArray)

                // Get the SMS sender
                val sender = smsMessage.displayOriginatingAddress

                Log.d(LOG_TAG, "Sender: " + sender)

                // Check is SMS coming from official
                if (sender == context!!.getString(R.string.sms_official_sender)) {
                    // Get OTP code from SMS
                    var messageBody: String? = smsMessage.messageBody
                    if (messageBody != null) {
                        // trim whitespace
                        messageBody = messageBody.trim { it <= ' '}.toLowerCase()

                        if (messageBody.startsWith("tunaiku")) {
                            // substring for the last characters (the OTP Code)
                            messageBody = messageBody.substring(messageBody.length - 4)
                            Log.d(LOG_TAG, "Message: " + messageBody)
                            Log.d(LOG_TAG, "Password Text : " + messageBody)
                            // Pass on the text to our listener.
                            broadCastSMSValue(messageBody)
                        }
                    }
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Broadcast SMS Value to Subscriber
     * @param msg String
     * */

    fun broadCastSMSValue(msg : String){
        var data = HashMap<String, Any>()
        data.put(AppConstants.EVENT_NAME.EVENT_SMS, msg)
        mRxBus.send(data)
    }

}