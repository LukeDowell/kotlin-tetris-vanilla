package org.badgrades.tetris.world

import javafx.scene.canvas.Canvas

class WorldRenderer(val tetrisWorld: TetrisWorld, val canas: Canvas) {

    companion object {
        /** The value we use to convert from game units to visual units */
        const val VISUAL_UNITS = 32f
    }

    fun render(delta: Long) {

    }
}