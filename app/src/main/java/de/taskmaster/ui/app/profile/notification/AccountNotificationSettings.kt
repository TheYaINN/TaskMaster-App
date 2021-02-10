package de.taskmaster.ui.app.profile.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.databinding.FragmentNotificationBinding
import de.taskmaster.model.model.UserViewModel
import de.taskmaster.ui.app.SubFragment

class AccountNotificationSettings : SubFragment(R.layout.fragment_notification, null) {

    private val userViewModel = UserViewModel()
    private val notificationAdapter = NotificationAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val fragmentBinding = DataBindingUtil.inflate<FragmentNotificationBinding>(inflater, R.layout.fragment_notification, container, false)
        val recyclerView = fragmentBinding.root.findViewById<RecyclerView>(R.id.items)
        recyclerView.adapter = notificationAdapter
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userViewModel.user.observe(viewLifecycleOwner, { notificationAdapter.setData(it.places) })
    }

}