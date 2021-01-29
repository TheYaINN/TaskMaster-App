package de.taskmaster.ui.app.group.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.taskmaster.R
import de.taskmaster.databinding.FragmentGroupEditBinding
import de.taskmaster.ui.app.SubFragment

class GroupEditorFragment : SubFragment(R.layout.fragment_group_edit) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val groupBinding = DataBindingUtil.inflate<FragmentGroupEditBinding>(inflater, R.layout.fragment_group_edit, container, false)
        return groupBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: refactor
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = GroupTabAdapter(2, requireActivity().supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        super.onViewCreated(view, savedInstanceState)
    }

}