package com.fhm.iconrotation

import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView

class IconRotator {

    //initialRotationAngle = 0.0f, endRotationAngle = 90.0f, duration = 500
    fun rotateToUpOrDown(ivRotate: ImageView, initialRotationAngle: Float, endRotationAngle: Float, duration: Long) {
        val rotateAnimation = RotateAnimation(initialRotationAngle, endRotationAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.duration = duration
        rotateAnimation.fillAfter = true
        ivRotate.startAnimation(rotateAnimation)
    }

}