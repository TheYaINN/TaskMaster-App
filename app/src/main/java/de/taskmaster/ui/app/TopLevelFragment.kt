package de.taskmaster.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class TopLevelFragment(private val resourceId: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as AppActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)
        return inflater.inflate(resourceId, container, false)
    }
}