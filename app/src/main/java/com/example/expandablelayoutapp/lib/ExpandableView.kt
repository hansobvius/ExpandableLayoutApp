package com.example.expandablelayoutapp.lib

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import com.example.expandablelayoutapp.R

class ExpandableView constructor(
    context: Context,
    attr: AttributeSet): LinearLayout(context, attr) {

    private var isExpandable: Boolean =
        INITIAL_EXPANDABLE_LAYOUT_VALUE

    private var enableExpandLayout: Boolean =
        INITIAL_CLICK_ENABLED_VALUE

    private var nestedViewValue: Int =
        INITIAL_CHILD_VIEW_VALUE

    init{
        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.ExpandableView,
            0,
            0
        ).apply {
            recycle()
        }
        val view = this.rootView.height
        this.visibility = View.GONE
        Log.i("TEST", "Height: $view")
    }

    override fun addView(child: View?) {
        child?.hasNestedScrollingParent()
        super.addView(child)
    }

    override fun shouldDelayChildPressedState(): Boolean = SHOULD_DELAY_CHILD

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val childCount = childCount
        val viewHeightSize = this.rootView.height
        val viewWidthSize = this.rootView.width
        for(i in 0..childCount){
            val childView: View? = getChildAt(i)
//            if(childView != null) nestedViewValue += childView.height
            childView?.let{
                nestedViewValue += childView.height
            }
        }
        if(this.orientation == LinearLayout.VERTICAL){
            setMeasuredDimension(viewWidthSize, viewHeightSize)
        }else{
            setMeasuredDimension(viewWidthSize, viewHeightSize)
        }
        Log.i("TEST", "Nested Child Value: $nestedViewValue")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val childCount = childCount
        val childContainerWidth = right - left
        for(i in 0..childCount){
            val childView: View? = getChildAt(i)
            childView?.layout(1, 1, childContainerWidth, childContainerWidth)
        }
    }

    private fun layoutVisibility(){
        this.visibility = View.VISIBLE
    }

    fun initClickAction(){
        layoutVisibility()
        val animator = getObjectAnimator()
        animator.apply{
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            enableClick()
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
                - this.rootView.height.toFloat()
            )
        }else{
            ObjectAnimator.ofFloat(
                this,
                View.TRANSLATION_Y,
                - this.rootView.height.toFloat(),
                0f
            )
        }

    private fun ObjectAnimator.enableClick(){
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                enableExpandLayout = !enableExpandLayout
            }
        })
    }

    private fun ObjectAnimator.expandLayout(){
        if(enableExpandLayout){
            enableExpandLayout = !enableExpandLayout
            isExpandable = !isExpandable
            start()
        }
    }

    companion object{
        private const val SHOULD_DELAY_CHILD = true
        private const val INITIAL_EXPANDABLE_LAYOUT_VALUE = false
        private const val INITIAL_CLICK_ENABLED_VALUE = true
        private const val INITIAL_CHILD_VIEW_VALUE = 0
    }
}