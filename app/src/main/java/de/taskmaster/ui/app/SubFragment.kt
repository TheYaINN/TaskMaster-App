package de.taskmaster.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import de.taskmaster.R

open class SubFragment<T : ViewDataBinding>(private val layoutResourceId: Int, private val menuId: Int? = R.menu.save) : SavableFragment() {

    lateinit var binder: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            show()
        }
        binder = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        setHasOptionsMenu(true)
        return binder.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as AppActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back)
        if (menuId != null) {
            inflater.inflate(menuId, menu)
        }
    }

}