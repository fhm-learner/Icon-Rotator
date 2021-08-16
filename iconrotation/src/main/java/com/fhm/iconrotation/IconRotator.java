package com.fhm.iconrotation;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class IconRotator {

    //initialRotationAngle = 0.0f, endRotationAngle = 90.0f, duration = 500
    public static void rotateToUpOrDown(ImageView ivRotate, float initialRotationAngle, float endRotationAngle, long duration){
        RotateAnimation rotateAnimation = new RotateAnimation(initialRotationAngle, endRotationAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        ivRotate.startAnimation(rotateAnimation);
    }

}
