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
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.rootView.bringToFront() // does't work
    }

    override fun addView(child: View?) {
        child?.hasNestedScrollingParent()
        super.addView(child)
    }

    override fun performClick(): Boolean {
        if(super.performClick()) return true
        initClickAction()
        return true
    }

    override fun shouldDelayChildPressedState(): Boolean = SHOULD_DELAY_CHILD

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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
        this.visibility = if(!isExpandable) View.GONE else View.VISIBLE
    }

    private fun initClickAction(){
        this.alpha = 1.0.toFloat()
        val animator = getObjectAnimator()
        animator.apply{
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            start()
        }
        isExpandable = !isExpandable
    }

    //TODO - change rootView target to ExpandableView target only
    private fun getObjectAnimator(): ObjectAnimator =
        if(isExpandable){
            ObjectAnimator.ofFloat(
                this.rootView,
                View.TRANSLATION_Y,
                0f,
                -this.rootView.height.toFloat())
        }else{
            ObjectAnimator.ofFloat(
                this.rootView,
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

    private fun setCount(){
        count++
        Log.i("TEST", "CLICK COUNT: $count")
    }

    companion object{
        const val SHOULD_DELAY_CHILD = true
    }
}