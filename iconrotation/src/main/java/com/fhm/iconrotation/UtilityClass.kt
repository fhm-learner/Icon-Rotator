package com.fhm.iconrotation

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.ImageView
import androidx.annotation.Nullable

class UtilityClass {

    //initialRotationAngle = 0.0f, endRotationAngle = 90.0f, duration = 500
    companion object {
        fun rotateToUpOrDown(ivRotate: ImageView, initialRotationAngle: Float, endRotationAngle: Float, duration: Long) = run {
            val rotateAnimation = RotateAnimation(initialRotationAngle, endRotationAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.interpolator = DecelerateInterpolator()
            rotateAnimation.duration = duration
            rotateAnimation.fillAfter = true
            ivRotate.startAnimation(rotateAnimation)
        }

        //collapsingSpeedValue = it should be 4 ideally(Always use collapsingSpeedValue in Long). Higher the value,higher the speed
        fun expandViewSoftly(v : View,expandingSpeedValue : Long) = run {
            val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((v.getParent() as View).width, View.MeasureSpec.EXACTLY)
            val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
            val targetHeight = v.measuredHeight
            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
            v.layoutParams.height = 1
            v.visibility = View.VISIBLE
            val a: Animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                    v.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
                override fun willChangeBounds(): Boolean {
                    return true
                }
            }
            // Expansion speed of 1dp/ms
            a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt() * expandingSpeedValue
            v.startAnimation(a)
        }

        //collapsingSpeedValue = it should be 2 ideally(Always use collapsingSpeedValue in Long). Higher the value,higher the speed
        fun collapseViewSoftly(v: View,collapsingSpeedValue : Long) = run {
            val initialHeight = v.measuredHeight
            val a: Animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                    if (interpolatedTime == 1f) {
                        v.visibility = View.GONE
                    } else {
                        v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                        v.requestLayout()
                    }
                }
                override fun willChangeBounds(): Boolean {
                    return true
                }
            }
            // Collapse speed of 1dp/ms
            a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt() * collapsingSpeedValue
            v.startAnimation(a)
        }

    }



}