package com.example.expandablelayoutapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.view.marginBottom
import kotlinx.android.synthetic.main.activity_main.view.*


class ExpandableView constructor(
    context: Context,
    attr: AttributeSet): LinearLayout(context, attr) {

    private var isExpandable: Boolean = false
    private var enableExpandLayout: Boolean = true
    private var count: Int = 0
    private var countChildView: Int = 0

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
        this.visibility = View.GONE
    }

    override fun addView(child: View?) {
        child?.hasNestedScrollingParent()
        super.addView(child)
    }

    override fun shouldDelayChildPressedState(): Boolean = SHOULD_DELAY_CHILD

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val childCount = childCount
        val childContainerWidth = right- left
        for(i in 0..childCount){
            countChildView = i
            val childView: View? = getChildAt(i)
            childView?.layout(1, 1, childContainerWidth, childContainerWidth)
            Log.i("TEST", "CHILD COUNT IS: $countChildView")
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    private fun layoutVisibility(){
        this.visibility = View.VISIBLE
    }

    fun initClickAction(){
        layoutVisibility()
        this.alpha = 1.0.toFloat()
        val animator = getObjectAnimator()
        animator.apply{
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            expandLayout()
        }.also{
            Log.i("LOG_DEBUG", "layout expandable value: $isExpandable")
        }
    }

    private fun getObjectAnimator(): ObjectAnimator =
        if(isExpandable){
            ObjectAnimator.ofFloat(
                this,
                View.TRANSLATION_Y,
                0f,
                -this.rootView.height.toFloat())
        }else{
            ObjectAnimator.ofFloat(
                this,
                View.TRANSLATION_Y,
                -this.rootView.height.toFloat(),
                0f)
        }

    private fun ObjectAnimator.enableClick(){
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if(enableExpandLayout){
                    animation?.start()
                }
            }
        })
    }

    private fun ObjectAnimator.expandLayout(){
        start()
        isExpandable = !isExpandable
    }

    companion object{
        const val SHOULD_DELAY_CHILD = true
    }
}