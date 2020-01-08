package com.example.expandablelayoutapp

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class ExpandableLayout: View.OnClickListener {

    var view: View? = null
        set(view){
            field = view
        }

    var isExpandable: Boolean = false

    override fun onClick(v: View?) {
        view!!.alpha =1.0.toFloat()
        val animator = getObjectAnimator()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 1000
        animator.start()
        isExpandable = !isExpandable
    }

    private fun getObjectAnimator(): ObjectAnimator {
        if(isExpandable){
            Log.i("TEST", "Expandable Clicked " + isExpandable)
            return ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                0f,
                -view!!.rootView.height.toFloat())
        }else{
            Log.i("TEST", "Expandable Clicked " + isExpandable)
            return ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                -view!!.rootView.height.toFloat(),
                0f)
        }
    }
}