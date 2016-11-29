package org.badgrades.tetris.world

import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import org.badgrades.tetris.model.Block

class WorldRenderer(val tetrisWorld: TetrisWorld, val canvas: Canvas) {

    companion object {
        /** The value we use to convert from game units to visual units */
        const val VISUAL_UNITS = 32.0
    }

    fun render(delta: Long) {
        val g = canvas.graphicsContext2D
        g.fill = Color.BLACK
        g.fillRect(
                0.0,
                0.0,
                canvas.width,
                canvas.height
        )

        tetrisWorld.placedBlocks.forEach { drawBlock(g, it) }

        drawBlock(g, tetrisWorld.playerBlock)
    }

    fun drawBlock(g: GraphicsContext, block: Block) {
        g.stroke = Color.BLACK
        g.fill = block.blockType.color
        block.cells.forEach {
            g.fillRect(
                    it.x * VISUAL_UNITS,
                    it.y * VISUAL_UNITS,
                    VISUAL_UNITS,
                    VISUAL_UNITS
            )
        }
    }
}