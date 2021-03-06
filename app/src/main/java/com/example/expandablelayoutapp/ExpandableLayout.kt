package com.example.expandablelayoutapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd

class ExpandableLayout: View.OnClickListener {

    private lateinit var animator: ObjectAnimator

    var view: View? = null
        set(view){
            field = view
        }

    var isExpandable: Boolean = false

    override fun onClick(view: View?) {
        view!!.alpha =1.0.toFloat()
        animator = getObjectAnimator()
        animator.apply{
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            expandAction(view)
            start()
        }
//        isExpandable = !isExpandable
    }

    private fun getObjectAnimator(): ObjectAnimator {
        if(isExpandable){
            return ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                0f,
                -view!!.rootView.height.toFloat())
        }else{
            return ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                -view!!.rootView.height.toFloat(),
                0f)
        }
    }

    fun ObjectAnimator.expandAction(view: View?){
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view!!.isEnabled = true
            }

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                view!!.isEnabled = false
            }
        })
    }
}