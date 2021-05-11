package de.taskmaster.activity.app.ui.list.edit

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.BR
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.databinding.FragmentListEditBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.data.impl.Deadline
import de.taskmaster.model.data.impl.Group
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.data.impl.Repeat
import de.taskmaster.model.data.impl.Status
import de.taskmaster.model.data.impl.Task
import de.taskmaster.model.data.impl.ToDoList
import de.taskmaster.model.handler.GroupSelector
import de.taskmaster.model.handler.ToggleEditableComponentHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class ListEditorFragment : SubFragment<FragmentListEditBinding>(R.layout.fragment_list_edit),
    GroupSelector {

    private lateinit var viewModel: ListEditorViewModel
    private val smallGroupAdapter = SmallGroupAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.presenter = ToggleEditableComponentHandler(requireContext())

        viewModel = ViewModelProvider(requireActivity()).get(ListEditorViewModel::class.java)
        binder.model = viewModel

        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.group_items)
        recyclerView.adapter = smallGroupAdapter
        //TODO: groups binding to groupAdapter
    }

    override fun selectGroup(group: Group) {
        viewModel.group = group
    }

    override fun save(): Boolean {
        val userId = context?.getSharedPreferences(
            LocalAuthHelper.preferencesKey,
            AppCompatActivity.MODE_PRIVATE
        )?.getInt(LocalAuthHelper.useridKey, -1)
        GlobalScope.launch {
            if (userId != null) {
                LocalDataBaseConnector.instance.toDoListDAO.insert(viewModel.build(userId))
            }
        }
        return super.save()
    }
}

class ListEditorViewModel : ObservableViewModel() {

    val today = Deadline(LocalDate.now())
    val tomorrow = Deadline(LocalDate.now().plusDays(1))
    val nextWeek = Deadline(LocalDate.now().plusDays(7))
    val reset = Deadline(null)

    var title: String = ""

    @get:Bindable
    @set:Bindable
    var deadline: Deadline = Deadline(null)
        set(value) {
            field = value
            notifyPropertyChanged(BR.deadline)
        }

    fun dateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        deadline = Deadline(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
    }

    fun build(userId: Int): ToDoList {

        val result = ToDoList(
            listId = 0,
            userId = userId,
            title = title,
            description = description,
            deadline = deadline
        )
        reset()
        return result

    }

    private fun reset() {
        //TODO: reset everything in the view
    }

    var place: Address? = null

    var description: String = ""

    var tags: String = ""

    @get:Bindable
    @set:Bindable
    var repeat: Repeat = Repeat.NEVER
        set(value) {
            field = value
            notifyPropertyChanged(BR.repeat)
        }

    var group: Group? = null

    var status: Status = Status.OPEN

    var tasks: List<Task>? = null

}
