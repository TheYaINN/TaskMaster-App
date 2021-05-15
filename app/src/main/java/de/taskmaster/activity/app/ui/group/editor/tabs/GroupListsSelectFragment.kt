package de.taskmaster.activity.app.ui.group.editor.tabs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentListsSelectorBinding
import de.taskmaster.model.data.impl.TodoListWithAssociations
import kotlinx.coroutines.launch

class GroupListsSelectFragment : SubFragment<FragmentListsSelectorBinding>(R.layout.fragment_lists_selector) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binder.model = ViewModelProvider(this).get(GroupListsSelectViewModel::class.java)
    }

}

// TODO es macht kein Sinn zwei models hier zu haben die das gleiche machen
//TODO: es sollte das aus den GroupLists verwendet werden
class GroupListsSelectViewModel(groupId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _lists = MutableLiveData<List<TodoListWithAssociations>>()
    val lists: LiveData<List<TodoListWithAssociations>> = _lists

    init {
        viewModelScope.launch {
            //TODO load data
        }
    }
}