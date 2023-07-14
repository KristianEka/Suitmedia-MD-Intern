package com.ekachandra.suitmedia.ui.second_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ekachandra.suitmedia.R
import com.ekachandra.suitmedia.ViewModelFactory
import com.ekachandra.suitmedia.databinding.ActivitySecondBinding
import com.ekachandra.suitmedia.ui.third_screen.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val viewModel by viewModels<SecondViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAppBar()
        initializeScreen()
        showSelectedUser()
    }

    private fun showAppBar() {
        binding.apply {
            appBar.tvTitle.text = getString(R.string.second_screen)
            appBar.ibBack.setOnClickListener { finish() }
        }
    }

    private fun initializeScreen() {
        binding.tvName.text = intent.getStringExtra(username)

        binding.btnChoose.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }

    private fun showSelectedUser() {
        viewModel.getUser().observe(this) { username ->
            binding.tvSelectedUsername.text = username
        }
    }

    companion object {
        const val username = "USERNAME"
    }
}