package de.taskmaster.ui.app.list.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import de.taskmaster.R
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.model.binding.ListEditorPresenter
import de.taskmaster.model.data.MyList
import de.taskmaster.ui.app.SubFragment
import java.time.LocalDateTime

class ListEditorFragment : SubFragment(R.layout.fragment_list_edit) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentBinding = DataBindingUtil.inflate<FragmentListEditBinding>(inflater, R.layout.fragment_list_edit, container, false)
        fragmentBinding.presenter = ListEditorPresenter(fragmentBinding.root, requireContext())
        fragmentBinding.model = MyList()
        return fragmentBinding.root
    }

    fun setDate(daysAhead: Int) {
        val current = LocalDateTime.now().plusDays(daysAhead.toLong())
        requireView().findViewById<DatePicker>(R.id.deadline_picker).updateDate(current.year, current.monthValue, current.dayOfMonth)
    }
}