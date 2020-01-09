package com.example.expandablelayoutapp

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

//class ExpandableView @JvmOverloads constructor(
//    context: Context,
//    attr: AttributeSet,
//    defStyleAttr: Int): View(context, attr, defStyleAttr) {
//
//    var isExpandable: Boolean = false
//
//    init{
//        isClickable = true
//    }
//
//    override fun performClick(): Boolean {
//        if(super.performClick()) return true
//        val animator = getObjectAnimator()
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.duration = 1000
//        animator.start()
//        return true
//    }
//
//    private fun getObjectAnimator(): ObjectAnimator {
//        if(isExpandable){
//            Log.i("TEST", "Expandable Clicked " + isExpandable)
//            return ObjectAnimator.ofFloat(
//                this.rootView,
//                View.TRANSLATION_Y,
//                0f,
//                -this.rootView!!.rootView.height.toFloat())
//        }else{
//            Log.i("TEST", "Expandable Clicked " + isExpandable)
//            return ObjectAnimator.ofFloat(
//                this.rootView,
//                View.TRANSLATION_Y,
//                -this.rootView!!.rootView.height.toFloat(),
//                0f)
//        }
//    }
//}