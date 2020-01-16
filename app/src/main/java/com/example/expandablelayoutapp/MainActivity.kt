package com.example.expandablelayoutapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.expandablelayoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var expandableLayout: ExpandableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
//        expandableLayout = ExpandableLayout()
//        expandableLayout.view = binding.expandableView
    }

    override fun onResume(){
        super.onResume()
        initListener()
    }

    private fun initListener(){
//        binding.actionView.setOnClickListener{
//            expandableLayout.onClick(null)
//        }
    }
}
