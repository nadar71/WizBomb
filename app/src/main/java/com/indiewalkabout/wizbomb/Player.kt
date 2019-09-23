package com.indiewalkabout.wizbomb

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

/**
 * -------------------------------------------------------------------------------------------------
 * Player Class.
 * -------------------------------------------------------------------------------------------------
 */

class Player(private val image: Bitmap) {
    private var x: Int = 0
    private var y: Int = 0
    private val w: Int
    private val h: Int
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    // collision detection box
    private var hitBox : Rect

    init {
        w = image.width
        h = image.height

        x = screenWidth/2
        y = screenHeight - 200

        hitBox = Rect(x, y, w, h)
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * Draws the object on to the canvas.
     * ---------------------------------------------------------------------------------------------
     */
    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * update properties for the game object
     * when the player touches the screen, position the player bitmap there
     * ---------------------------------------------------------------------------------------------
     */
    fun update(touch_x: Int, touch_y: Int) {
        x = touch_x - w / 2
        y = touch_y - h / 2

        // hitbox follow player position
        hitBox.left   = x
        hitBox.top    = y
        hitBox.right  = x + w
        hitBox.bottom = y + h

    }

}
