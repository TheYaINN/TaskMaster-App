package de.taskmaster.activity.app.ui.profile.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentNotificationBinding
import de.taskmaster.model.data.User

class AccountNotificationSettings : SubFragment<FragmentNotificationBinding>(R.layout.fragment_notification, null) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.items)

        val notificationAdapter = NotificationAdapter()
        recyclerView.adapter = notificationAdapter

        notificationAdapter.setData(userViewModel.places)
    }

}