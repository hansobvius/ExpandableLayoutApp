package com.example.expandablelayoutapp

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout

class ExpandableView constructor(
    context: Context,
    attr: AttributeSet): LinearLayout(context, attr) {

    var isExpandable: Boolean = false

    init{
        isClickable = true
        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.ExpandableView,
            0,
            0
        ).apply {
            recycle()
        }
    }

    override fun performClick(): Boolean {
        if(super.performClick()) return true
        this.rootView.alpha = 1.0.toFloat()
        val animator = getObjectAnimator()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 1000
        animator.start()
        isExpandable = !isExpandable
        return true
    }

    private fun getObjectAnimator(): ObjectAnimator {
        if(isExpandable){
            Log.i("TEST", "Expandable Clicked " + isExpandable)
            return ObjectAnimator.ofFloat(
                this.rootView,
                View.TRANSLATION_Y,
                0f,
                -this.rootView!!.rootView.height.toFloat())
        }else{
            Log.i("TEST", "Expandable Clicked " + isExpandable)
            return ObjectAnimator.ofFloat(
                this.rootView,
                View.TRANSLATION_Y,
                -this.rootView!!.rootView.height.toFloat(),
                0f)
        }
    }
}