package de.taskmaster.activity.app.ui.group.editor

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import de.taskmaster.R
import de.taskmaster.activity.app.ui.group.editor.tabs.GroupListsFragment
import de.taskmaster.activity.app.ui.group.editor.tabs.GroupMembersFragment
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentGroupEditBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Group
import de.taskmaster.model.data.impl.ObservableViewModel
import de.taskmaster.model.data.impl.UserGroupCrossRef
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroupEditorFragment : SubFragment<FragmentGroupEditBinding>(R.layout.fragment_group_edit) {

    lateinit var viewModel: GroupEditorViewModel

    var isEditMode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        binder.viewPager.adapter = GroupTabAdapter(requireActivity().supportFragmentManager)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                tabLayout.setScrollPosition(position, positionOffset, false)
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                tabLayout.setScrollPosition(tab.position, 0f, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        val groupId = arguments?.getInt("groupId")
        isEditMode = groupId != null

        viewModel = ViewModelProvider(this, GroupEditorViewModelFactory(requireActivity().application, isEditMode, groupId ?: 0))
            .get(GroupEditorViewModel::class.java)
        binder.model = viewModel
    }

    override fun save(): Boolean {
        GlobalScope.launch {
            if (isEditMode) {
                LocalDataBaseConnector.instance.groupDAO.update(viewModel.build())
            } else {
                val group = viewModel.build()
                LocalDataBaseConnector.instance.groupDAO.insert(group)

                val userGroupCrossRef = UserGroupCrossRef(userId, group.groupId)
                LocalDataBaseConnector.instance.userGroupCrossRefDAO.insert(userGroupCrossRef)
            }
        }
        return super.save()
    }
}

class GroupEditorViewModel(private val groupId: Int, private val isEditMode: Boolean) : ObservableViewModel() {

    var group = Group(title = "", description = "")

    init {
        if (isEditMode) {
            GlobalScope.launch {
                group = LocalDataBaseConnector.instance.groupDAO.getGroupByGroupId(groupId) ?: Group(title = "", description = "")
            }
        }
    }

    fun build(): Group {
        return Group(
            groupId = groupId,
            title = group.title,
            description = group.description,
        )
    }

}

class GroupEditorViewModelFactory(
    application: Application,
    private val isEditMode: Boolean,
    private val groupId: Int,
) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroupEditorViewModel(groupId, isEditMode) as T
    }

}

class GroupTabAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf(GroupListsFragment(), GroupMembersFragment())

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

}
