package org.badgrades.tetris.world

import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import org.badgrades.tetris.model.Block

class WorldRenderer(val tetrisWorld: TetrisWorld, val canvas: Canvas) {

    companion object {
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
        block.cells.forEach {
            g.fill = block.blockType.color
            g.fillRect(
                    it.x * VISUAL_UNITS,
                    it.y * VISUAL_UNITS,
                    VISUAL_UNITS,
                    VISUAL_UNITS
            )

            g.stroke = Color.BLACK
            g.strokeRect(
                    it.x * VISUAL_UNITS,
                    it.y * VISUAL_UNITS,
                    VISUAL_UNITS,
                    VISUAL_UNITS
            )
        }
    }
}