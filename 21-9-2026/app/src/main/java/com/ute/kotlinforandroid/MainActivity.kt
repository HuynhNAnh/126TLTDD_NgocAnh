package com.ute.kotlinforandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ute.kotlinforandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // ── 1. Gom nhóm thao tác hiển thị với 'with(binding)' ─────
    private fun displayStudent(name: String, gpa: Double, email: String) {
// Bên trong with(binding), mọi View thuộc binding đều là 'this'
        with(binding) {
            tvName.text = name
            tvGpa.text = "Điểm tích lũy: $gpa"
            tvEmail.text = email
            btnUpdate.isEnabled = true
            progressBar.visibility = View.GONE
        }
    }
    // ── 2. Cấu hình Intent hoặc View mới với 'apply' ──────────
    private fun openDetailActivity(studentId: String) {
        val detailIntent = Intent(this, DetailActivity::class.java).apply {
            putExtra("KEY_STUDENT_ID", studentId)
            putExtra("KEY_TIMESTAMP", System.currentTimeMillis())
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(detailIntent)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvWelcome.text = "Chào mừng bạn đến với ViewBinding!"
    }
}