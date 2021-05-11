package de.taskmaster.activity.app.ui.group

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.auth.LocalAuthHelper
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Group
import kotlinx.coroutines.launch

class GroupOverviewFragment : TopLevelFragment(R.layout.fragment_group, R.menu.lists_groups_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment)
        }
        val userId = LocalAuthHelper.getUserId(requireContext())
        val viewModel = ViewModelProvider(this, GroupViewModelFactory(requireActivity().application, userId, viewLifecycleOwner))
            .get(GroupViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BigGroupAdapter(this)
        recyclerView.adapter = adapter
        viewModel.groups.observe(viewLifecycleOwner, { adapter.setData(it) })
    }

}

class GroupViewModel(userId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {

    private val _groups = MutableLiveData<List<Group>>()
    val groups: LiveData<List<Group>> = _groups

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.groupDAO.getByID(userId).observe(viewLifecycleOwner, { _groups.postValue(it) })
        }
    }
}

class GroupViewModelFactory(application: Application, private val userId: Int, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroupViewModel(userId, viewLifecycleOwner) as T
    }

}

class BigGroupAdapter(private val fragment: Fragment) : BasicAdapter<Group, BigGroupAdapter.GroupViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.item_group_big, parent, false) as CardView
        return GroupViewHolder(listView)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(data[position])
        listView.setOnClickListener {
            fragment.findNavController().navigate(R.id.action_navigation_group_to_groupEditorFragment, bundleOf("id" to holder.itemId))
        }
    }

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group) {
            val icon = itemView.findViewById<ImageView>(R.id.item_icon)
            val name = itemView.findViewById<TextView>(R.id.item_name)
            val description = itemView.findViewById<TextView>(R.id.item_description)

            //TODO: icon.setImageDrawable()
            name.text = group.title
            description.text = group.description
        }
    }
}
