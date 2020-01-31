package com.example.expandablelayoutapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.expandablelayoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResume(){
        super.onResume()
        initListener()
        binding.frameLayout.bringToFront()
    }

    private fun initListener(){
        binding.actionView.setOnClickListener{
            binding.expandableLayout.initClickAction()
        }
    }
}
