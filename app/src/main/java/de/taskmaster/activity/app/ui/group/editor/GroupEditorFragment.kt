package de.taskmaster.activity.app.ui.group.editor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
import de.taskmaster.model.data.impl.ObservableViewModel

class GroupEditorFragment : SubFragment<FragmentGroupEditBinding>(R.layout.fragment_group_edit) {

    lateinit var viewModel: GroupEditorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        binder.viewPager.adapter = GroupTabAdapter(requireActivity().supportFragmentManager)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
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

        viewModel = ViewModelProvider(this).get(GroupEditorViewModel::class.java)

        binder.model = viewModel
    }

}

class GroupEditorViewModel : ObservableViewModel() {
    var title = ""
    var description = ""
}

class GroupTabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf(GroupListsFragment(), GroupMembersFragment())

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

}
