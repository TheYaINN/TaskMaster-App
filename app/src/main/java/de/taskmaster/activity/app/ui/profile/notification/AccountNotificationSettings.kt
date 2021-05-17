package de.taskmaster.activity.app.ui.profile.notification

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import de.taskmaster.R
import de.taskmaster.activity.util.BasicAdapter
import de.taskmaster.activity.util.fragment.SubFragment
import de.taskmaster.databinding.FragmentNotificationBinding
import de.taskmaster.db.LocalDataBaseConnector
import de.taskmaster.model.data.impl.Address
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountNotificationSettings : SubFragment<FragmentNotificationBinding>(R.layout.fragment_notification, null) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binder.root.findViewById<RecyclerView>(R.id.items)

        val viewModel = ViewModelProvider(this, AccountNotificationViewModelFactory(requireActivity().application, userId, viewLifecycleOwner))
            .get(AccountNotificationViewModel::class.java)

        val adapter = NotificationAdapter()
        recyclerView.adapter = adapter
        viewModel.places.observe(viewLifecycleOwner, { adapter.setData(it) })
    }

}

class AccountNotificationViewModel(userId: Int, viewLifecycleOwner: LifecycleOwner) : ViewModel() {
    private val _places = MutableLiveData<List<Address>>()
    val places: LiveData<List<Address>> = _places

    init {
        viewModelScope.launch {
            LocalDataBaseConnector.instance.userWithAssociationsDAO.getByUserId(userId).observe(viewLifecycleOwner, { _places.postValue(it.places) })
        }
    }
}

class AccountNotificationViewModelFactory(application: Application, private val userId: Int, private val viewLifecycleOwner: LifecycleOwner) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountNotificationViewModel(userId, viewLifecycleOwner) as T
    }

}

class NotificationAdapter : BasicAdapter<Address, NotificationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(address: Address) {
            val title = itemView.findViewById<TextView>(R.id.item_title)
            title.text = address.toString()

            val notify = itemView.findViewById<SwitchCompat>(R.id.item_switch)
            notify.isChecked = address.notifiable

            notify.setOnCheckedChangeListener { _, isChecked ->
                GlobalScope.launch {
                    LocalDataBaseConnector.instance.addressDAO.update(address.apply { notifiable = isChecked })
                }
            }
        }
    }

}