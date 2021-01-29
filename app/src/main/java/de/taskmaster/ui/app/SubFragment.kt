package de.taskmaster.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.taskmaster.R

abstract class SubFragment(private val resourceID: Int) : Fragment(), Savable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            show()
        }
        setHasOptionsMenu(true)
        return inflater.inflate(resourceID, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as AppActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back)
        inflater.inflate(R.menu.save, menu)
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
        return false
    }

}