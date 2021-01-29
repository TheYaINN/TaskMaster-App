package de.taskmaster.ui.app.list.editor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import de.taskmaster.R
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.model.binding.ListEditorContract
import de.taskmaster.model.binding.ListEditorPresenter
import de.taskmaster.model.data.MyList
import de.taskmaster.ui.app.SubFragment
import java.time.LocalDateTime

class ListEditorFragment : SubFragment(R.layout.fragment_list_edit), ListEditorContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        val fragmentBinding = DataBindingUtil.setContentView<FragmentListEditBinding>(requireActivity(), R.layout.fragment_list_edit)
        fragmentBinding.presenter = ListEditorPresenter(this, requireContext())
        fragmentBinding.model = MyList()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: refactor
        val picker = view.findViewById<DatePicker>(R.id.deadline_picker)

        view.findViewById<Button>(R.id.date_today).setOnClickListener {
            val current = LocalDateTime.now()
            picker.updateDate(current.year, current.monthValue, current.dayOfMonth)
        }
        view.findViewById<Button>(R.id.date_tomorrow).setOnClickListener {
            val tomorrow = LocalDateTime.now().plusDays(1L)
            picker.updateDate(tomorrow.year, tomorrow.monthValue, tomorrow.dayOfMonth)
        }
        view.findViewById<Button>(R.id.date_next_week).setOnClickListener {
            val nextWeek = LocalDateTime.now().plusDays(7L)
            picker.updateDate(nextWeek.year, nextWeek.monthValue, nextWeek.dayOfMonth)
        }

        val test = view.findViewById<LinearLayout>(R.id.place_linearLayout)
        test.addView(TextView(context).apply { text = "FICKEEEEN" })
    }

    override fun toggle(caller: Int) {
        val view = requireView()
        val layoutID = R.id::class.java.fields.find { it.name == "lower_$caller" }?.get(this) ?: error("Could not access field")
        val toggle = view.findViewById<TextView>(caller)
        toggle.setOnClickListener {
            val pickerLayout = view.findViewById<View>(layoutID as Int)
            pickerLayout.visibility = when (pickerLayout.visibility) {
                View.GONE -> {
                    toggle.text = getString(R.string.select)
                    View.VISIBLE
                }
                View.VISIBLE -> {
                    toggle.text = getString(R.string.edit)
                    View.GONE
                }
                else -> error("not supposed to happen")
            }
        }
    }
}