package de.taskmaster.ui.app.group.editor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import de.taskmaster.ui.app.group.tabs.GroupListsFragment
import de.taskmaster.ui.app.group.tabs.GroupMembersFragment

class GroupTabAdapter(private val totalTabs: Int, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GroupMembersFragment()
            1 -> GroupListsFragment()
            else -> error("Nothing selected")
        }
    }

}
