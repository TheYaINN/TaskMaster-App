package de.taskmaster.activity.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.app.ui.task.TaskAdapter
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.activity.util.fragment.TopLevelFragment
import de.taskmaster.model.data.Group
import de.taskmaster.model.data.Task
import de.taskmaster.model.data.TaskList
import de.taskmaster.model.data.User
import de.taskmaster.model.handler.GroupSelector
import de.taskmaster.model.toggleVisibility

class HomeFragment : TopLevelFragment(R.layout.fragment_home, null) {

    private lateinit var viewModel: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(User::class.java)
        val recyclerview = view.findViewById<RecyclerView>(R.id.calendar_events)
        val adapter = HomeAdapter(this)
        recyclerview.adapter = adapter
        adapter.setData(viewModel.lists)
    }

}

class HomeAdapter(val fragment: Fragment) : BasicAdapter<TaskList, HomeAdapter.HomeViewHolder>() {

    private lateinit var listView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        listView = LayoutInflater.from(parent.context).inflate(R.layout.component_home_list, parent, false) as CardView
        return HomeViewHolder(listView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(taskList: TaskList) {
            val title = itemView.findViewById<TextView>(R.id.title)
            val linearLayout = itemView.findViewById<LinearLayout>(R.id.items)

            title.text = taskList.title
            title.setOnClickListener {
                linearLayout.toggleVisibility()
            }

            taskList.tasks?.forEach { linearLayout.addView(TextView(fragment.requireContext()).apply { text = it.title }) }
        }
    }
}
