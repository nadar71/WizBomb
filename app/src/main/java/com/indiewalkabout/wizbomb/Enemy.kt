package com.indiewalkabout.wizbomb

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

open class Enemy(var image:Bitmap) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0
    protected var xVelocity = 10
    protected var yVelocity = 10
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height

        x = screenWidth/2
        y = 0 +  w
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * Draws the object on to the canvas.
     * ---------------------------------------------------------------------------------------------
     */
    open fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * update properties for the game object
     * ---------------------------------------------------------------------------------------------
     */
    open fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)

        if (x > screenWidth - w || x < w*0.5) {
            xVelocity = xVelocity * -1
        }

        x += (xVelocity)

    }
}