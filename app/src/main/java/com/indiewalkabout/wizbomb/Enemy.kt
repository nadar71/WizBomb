package com.indiewalkabout.wizbomb

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
/**
 * ---------------------------------------------------------------------------------------------
 * Parent class about all enemies
 * ---------------------------------------------------------------------------------------------
 */
open class Enemy(var image:Bitmap) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0
    protected var xVelocity = 10
    protected var yVelocity = 10
    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    // collision detection box
    protected var hitBox : Rect

    init {
        w = image.width
        h = image.height

        x = screenWidth/2
        y = 0 +  w

        hitBox = Rect(x, y, w, h)
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
     * update properties for game object
     * ---------------------------------------------------------------------------------------------
     */
    open fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)

        if (x > screenWidth - w || x < w*0.5) {
            xVelocity = xVelocity * -1
        }

        x += (xVelocity)

        updateHitBox()

    }

    /**
     * ---------------------------------------------------------------------------------------------
     * update hit box for game object
     * ---------------------------------------------------------------------------------------------
     */
    open fun updateHitBox(){
        // hitbox follow player position
        hitBox.left   = x
        hitBox.top    = y
        hitBox.right  = x + w
        hitBox.bottom = y + h
    }
}