package com.ekachandra.suitmedia.ui.third_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekachandra.suitmedia.R
import com.ekachandra.suitmedia.data.remote.response.DataItem
import com.ekachandra.suitmedia.databinding.ItemUserBinding

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.apply {
                Glide.with(root.context)
                    .load(data.avatar)
                    .into(ivUser)

                val fullName =
                    root.context.getString(R.string.fullname, data.firstName, data.lastName)

                tvUserName.text = fullName
                tvEmail.text = data.email

                root.setOnClickListener {
                    val viewModel = ViewModelProvider(
                        root.context as ViewModelStoreOwner
                    )[ThirdViewModel::class.java]

                    viewModel.saveUser(fullName)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}