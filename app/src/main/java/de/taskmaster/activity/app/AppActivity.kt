package de.taskmaster.activity.app

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.taskmaster.R

const val AUTHORITY = "de.taskmaster.datasync.provider"

// An account type, in the form of a domain name
const val ACCOUNT_TYPE = "de.taskmaster.datasync"

// The account name
const val ACCOUNT = "babaAccount"

class AppActivity : AppCompatActivity() {

    //TODO: darf eigentlich nicht so, aber geht nicht besser z.Z.
    var userId: Int = -1

    private lateinit var mAccount: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        PushNotificationManager(this)

        //TODO: mAccount = createSyncAccount()
    }


    /**
     * Create a new placeholder account for the sync adapter
     */
    private fun createSyncAccount(): Account {
        val accountManager = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        return Account(ACCOUNT, ACCOUNT_TYPE).also { newAccount ->
            /*
             * Add the account and account type, no password or user data
             * If successful, return the Account object, otherwise report an error.
             */
            if (accountManager.addAccountExplicitly(newAccount, null, null)) {
                /*
                 * If you don't set android:syncable="true" in
                 * in your <provider> element in the manifest,
                 * then call context.setIsSyncable(account, AUTHORITY, 1)
                 * here.
                 */
            } else {
                //TODO: replace with proper logging
                println("Error while trying to sync")
            }
        }
    }
}