package com.happydeliv.happydelivapp.utils

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by ibnumuzzakkir on 10/02/18.
 * Android Engineer
 * SCO Project
 */
class FirebaseDB(context : Context){
    companion object {
        /**
         * List of Firebase DB Tables name
         * */
        val TABLE_PACKAGE_IN_PROGRESS = "package_in_progress"

    }
    internal var mFirebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Sending data to firebase
     * */
    fun gettingCourierLastLocation(trackId :String, callback: GetFireBaseCallBack?) {
        if (callback != null) {
            val mDatabaseReference = mFirebaseDatabase
                    .getReference(TABLE_PACKAGE_IN_PROGRESS)
            var query = mDatabaseReference
                    .orderByChild("trackId")
                    .equalTo(trackId)
            mDatabaseReference.keepSynced(true)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError?) {
                    databaseError?.let { callback.onError(it) }
                }

                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    if (dataSnapshot != null) {
                        callback.onSuccess(dataSnapshot)
                    }
                }

            })
        }
    }



    /**
     * Interface for callback data from search
     */

    interface GetFireBaseCallBack {
        fun onSuccess(dataSnapshot: DataSnapshot)
        fun onError(databaseError: DatabaseError)
    }

}