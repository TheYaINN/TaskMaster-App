package de.taskmaster.db.sync

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * A bound Service that instantiates the authenticator
 * when started.
 */
class AuthenticatorService : Service() {

    // Instance field that stores the authenticator object
    private lateinit var mAuthenticator: DataBaseAuthenticator

    override fun onCreate() {
        // Create a new authenticator object
        mAuthenticator = DataBaseAuthenticator(this)
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    override fun onBind(intent: Intent?): IBinder = mAuthenticator.iBinder
}