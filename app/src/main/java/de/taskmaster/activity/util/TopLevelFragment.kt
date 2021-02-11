package de.taskmaster.activity.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity

open class TopLevelFragment(private val resourceId: Int, private val menuResourceId: Int? = R.menu.lists_groups_menu) : SavableFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)
        return inflater.inflate(resourceId, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menuResourceId != null) {
            inflater.inflate(menuResourceId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}