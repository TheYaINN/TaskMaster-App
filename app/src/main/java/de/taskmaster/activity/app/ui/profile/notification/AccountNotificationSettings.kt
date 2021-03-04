package de.taskmaster.activity.app.ui.profile.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentNotificationBinding
import de.taskmaster.model.data.impl.Address

class AccountNotificationSettings : SubFragment<FragmentNotificationBinding>(R.layout.fragment_notification, null) {

    private lateinit var viewModel: AccountNotificationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.items)

        viewModel = ViewModelProvider(this).get(AccountNotificationViewModel::class.java)

        val adapter = NotificationAdapter()
        recyclerView.adapter = adapter
        viewModel.places.observe(viewLifecycleOwner, { adapter.setData(it) })
    }

}

class AccountNotificationViewModel : ViewModel() {
    val places: LiveData<List<Address>> = MutableLiveData()
}