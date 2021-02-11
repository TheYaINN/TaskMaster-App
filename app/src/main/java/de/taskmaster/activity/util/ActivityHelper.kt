package de.taskmaster.activity.util

import android.app.Activity
import android.content.Intent

class ActivityHelper {

    companion object {
        fun startActivity(callingActivity: Activity, activityToStart: Class<*>) {
            callingActivity.startActivity(Intent(callingActivity, activityToStart))
            callingActivity.finish()
        }
    }
}