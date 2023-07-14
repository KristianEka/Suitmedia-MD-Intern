package com.ekachandra.suitmedia.ui.first_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ekachandra.suitmedia.R
import com.ekachandra.suitmedia.databinding.ActivityFirstBinding
import com.ekachandra.suitmedia.ui.second_screen.SecondActivity

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        nextWithName()
        checkPalindrome()
    }

    private fun nextWithName() {
        binding.btnNext.setOnClickListener {
            val tvName = binding.nameEditText.text.toString()
            if (tvName.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(SecondActivity.username, tvName)
                startActivity(intent)
            }
        }
    }

    private fun checkPalindrome() {
        binding.btnCheck.setOnClickListener {
            val tvPalindrome = binding.palindromeEditText.text.toString()
            if (tvPalindrome.isNotEmpty()) {
                if (isPalindrome(tvPalindrome)) {
                    showAlert(getString(R.string.ispalindrome))
                } else {
                    showAlert(getString(R.string.not_palindrome))
                }
            }

        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        val reverseString = sentence.reversed()

        return sentence == reverseString
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.palindrome)
            .setMessage(message)
            .show()
    }
}