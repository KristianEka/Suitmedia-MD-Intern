package com.ekachandra.suitmedia.ui.third_screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekachandra.suitmedia.R
import com.ekachandra.suitmedia.ViewModelFactory
import com.ekachandra.suitmedia.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapter: UserAdapter

    private val viewModel by viewModels<ThirdViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAppBar()
        getData()
        swipeAction()
    }

    private fun showAppBar() {
        binding.apply {
            appBar.tvTitle.text = getString(R.string.third_screen)
            appBar.ibBack.setOnClickListener { finish() }
        }
    }

    private fun swipeAction() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.users.observe(this) { data ->
                if (data != null) {
                    adapter.submitData(lifecycle, data)
                    binding.swipeRefresh.isRefreshing = false
                } else {
                    showToast()
                }

            }
        }
    }

    private fun getData() {
        adapter = UserAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.apply {
            rvUsers.layoutManager = layoutManager
            rvUsers.addItemDecoration(
                DividerItemDecoration(
                    this@ThirdActivity,
                    layoutManager.orientation
                )
            )
            rvUsers.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
        }

        viewModel.users.observe(this) {
            if (it != null) {
                adapter.submitData(lifecycle, it)
            } else {
                showToast()
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.data_is_empty), Toast.LENGTH_LONG).show()
    }
}