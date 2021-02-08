package de.taskmaster.ui.util

import android.app.Activity
import android.content.Intent
import de.taskmaster.ui.app.AppActivity

class ActivityHelper {

    companion object {
        fun startAppActivity(activity: Activity) {
            activity.startActivity(Intent(activity, AppActivity::class.java))
            activity.finish()
        }
    }
}