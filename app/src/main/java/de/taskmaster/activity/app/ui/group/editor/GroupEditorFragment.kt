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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroupEditorFragment : SubFragment<FragmentGroupEditBinding>(R.layout.fragment_group_edit) {

    lateinit var viewModel: GroupEditorViewModel

    var isEditMode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val groupId = arguments?.getInt("groupId")
        isEditMode = groupId != null

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        binder.viewPager.adapter = GroupTabAdapter(requireActivity().supportFragmentManager, groupId)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                tabLayout.setScrollPosition(position, positionOffset, false)
            }

            override fun onPageSelected(position: Int) {
                //wird nicht unterstützt
            }

            override fun onPageScrollStateChanged(state: Int) {
                //wird nicht unterstützt
            }
        })
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                tabLayout.setScrollPosition(tab.position, 0f, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //wird nicht unterstützt
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //wird nicht unterstützt
            }
        })

        viewModel = ViewModelProvider(this, GroupEditorViewModelFactory(requireActivity().application, isEditMode, groupId ?: 0))
            .get(GroupEditorViewModel::class.java)
        binder.model = viewModel
    }

    override fun save(): Boolean {
        GlobalScope.launch {
            val dao = LocalDataBaseConnector.instance.groupDAO
            val group = viewModel.build()
            if (isEditMode) {
                dao.update(group)
            } else {
                dao.insert(group)
            }
        }
        return super.save()
    }
}

class GroupEditorViewModel(private val groupId: Int, private val isEditMode: Boolean) : ObservableViewModel() {

    var group = Group.EMPTY

    init {
        if (isEditMode) {
            GlobalScope.launch {
                group = LocalDataBaseConnector.instance.groupDAO.getGroupByGroupId(groupId) ?: Group.EMPTY
            }
        }
    }

    fun build(): Group {
        val group = Group(groupId = groupId, title = group.title, description = group.description)
        return if (isEditMode) group.apply { group.groupId = this@GroupEditorViewModel.groupId } else group
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

class GroupTabAdapter(fragmentManager: FragmentManager, private val groupId: Int?) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf(GroupListsFragment(groupId), GroupMembersFragment(groupId))

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

}
