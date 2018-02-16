package com.happydeliv.happydelivapp.utils

import android.content.Context
import com.google.firebase.database.*

/**
 * Created by ibnumuzzakkir on 10/02/18.
 * Android Engineer
 * SCO Project
 */
class FirebaseDB(context : Context): ValueEventListener{

    companion object {
        /**
         * List of Firebase DB Tables name
         * */
        val TABLE_PACKAGE_IN_PROGRESS = "package_in_progress"

    }

    var mQuery : Query? = null
    lateinit var mCallBack : GetFireBaseCallBack
    internal var mFirebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Sending data to firebase
     * */
    fun gettingCourierLastLocation(trackId :String, callback: GetFireBaseCallBack?) {
        if (callback != null) {
            val mDatabaseReference = mFirebaseDatabase
                    .getReference(TABLE_PACKAGE_IN_PROGRESS)
            mQuery = mDatabaseReference
                    .orderByChild("trackId")
                    .equalTo(trackId)
            mCallBack = callback
            mDatabaseReference.keepSynced(true)
            mQuery?.addValueEventListener(this)
        }
    }

    fun removeListener(){
        mQuery?.removeEventListener(this)
    }

    override fun onCancelled(databaseError: DatabaseError?) {
        databaseError?.let {
            mCallBack.onError(it)
        }
    }

    override fun onDataChange(dataSnapshot: DataSnapshot?) {
        dataSnapshot?.let {
            mCallBack.onSuccess(it)
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