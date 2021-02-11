package de.taskmaster.activity.app.ui.group.editor

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.taskmaster.R
import de.taskmaster.activity.util.SubFragment
import de.taskmaster.databinding.FragmentGroupEditBinding

class GroupEditorFragment : SubFragment<FragmentGroupEditBinding>(R.layout.fragment_group_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: refactor
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = GroupTabAdapter(2, requireActivity().supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        super.onViewCreated(view, savedInstanceState)
    }

}