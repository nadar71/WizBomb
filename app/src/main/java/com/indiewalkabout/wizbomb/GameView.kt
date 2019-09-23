package com.indiewalkabout.wizbomb

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.SurfaceHolder
import java.util.ArrayList

/**
 * ---------------------------------------------------------------------------------------------
 * GameView is our playground.
 * ---------------------------------------------------------------------------------------------
 */

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
        SurfaceHolder.Callback {
    private val thread:      GameThread
    private var bomb:        Bomb? = null
    private var basic_enemy: Enemy?   = null
    private var player:      Player?   = null

    private var touched: Boolean = false
    private var touched_x: Int = 0
    private var touched_y: Int = 0

    private var enemies : List<Enemy> = ArrayList<Enemy>()
    private var bombs   : List<Bomb>  = ArrayList<Bomb>()

    init {
        // add callback
        holder.addCallback(this)

        // instantiate the game thread
        thread = GameThread(holder, this)
    }


    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        // game objects
        bomb        = Bomb(BitmapFactory.decodeResource(resources, R.drawable.bomb_01))
        basic_enemy = Enemy(BitmapFactory.decodeResource(resources, R.drawable.monster_01))
        player      = Player(BitmapFactory.decodeResource(resources, R.drawable.razor_saw))

        // start the game thread
        thread.setRunning(true)
        thread.start()
    }


    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }



    /**
     * ---------------------------------------------------------------------------------------------
     * Function to update the positions of player and game objects
     * ---------------------------------------------------------------------------------------------
     */
    fun update() {
        bomb!!.update()
        basic_enemy!!.update()

        // update player position
        if(touched) {
            player!!.update(touched_x+5, touched_y+5)
        }

        // check collisions
        for(enemy in enemies){}
        for(bomb  in bombs){}


    }

    /**
     * ---------------------------------------------------------------------------------------------
     * Everything that has to be drawn on Canvas
     * ---------------------------------------------------------------------------------------------
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        bomb!!.draw(canvas)
        basic_enemy!!.draw(canvas)
        player!!.draw(canvas)
    }


    



    override fun onTouchEvent(event: MotionEvent): Boolean {
        // when ever there is a touch on the screen,
        // we can get the position of touch
        // which we may use it for tracking some of the game objects
        touched_x = event.x.toInt()
        touched_y = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> touched = false
            MotionEvent.ACTION_CANCEL -> touched = false
            MotionEvent.ACTION_OUTSIDE -> touched = false
        }
        return true
    }

}

