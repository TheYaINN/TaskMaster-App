package de.taskmaster.activity.util.fragment

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity
import de.taskmaster.auth.LocalAuthHelper

open class SavableFragment : Fragment(), Savable {

    var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = LocalAuthHelper.getUserId(requireContext())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                (activity as AppActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
                true
            }
            R.id.save_action -> {
                save()
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun save(): Boolean {
        return findNavController().popBackStack()
    }
}