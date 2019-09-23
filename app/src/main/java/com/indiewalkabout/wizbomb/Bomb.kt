package com.indiewalkabout.wizbomb

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * -------------------------------------------------------------------------------------------------
 * Bomb enemy type
 * -------------------------------------------------------------------------------------------------
 */

class Bomb(image: Bitmap):Enemy(image) {

    init {

        xVelocity = 20
        yVelocity = 20

        x = screenWidth/2
        y = screenHeight/2
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * Draws the object on to the canvas.
     * ---------------------------------------------------------------------------------------------
     */
    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * update properties for the game object
     * ---------------------------------------------------------------------------------------------
     */
    override fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)

        if (x > screenWidth - image.width || x < image.width) {
            xVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            yVelocity = yVelocity * -1
        }

        x += (xVelocity)
        y += (yVelocity)


        updateHitBox()
    }

}
