package org.badgrades.tetris

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage
import org.badgrades.tetris.world.TetrisWorld
import org.badgrades.tetris.world.WorldRenderer
import java.util.concurrent.TimeUnit

class TetrisGameApplication : Application() {

    val renderer: WorldRenderer
    val gameHandler: GameHandler
    val inputHandler: InputHandler
    val tetrisWorld: TetrisWorld
    val canvas: Canvas

    init {
        canvas = Canvas(
                TetrisWorld.GRID_WIDTH * WorldRenderer.VISUAL_UNITS,
                TetrisWorld.GRID_HEIGHT * WorldRenderer.VISUAL_UNITS
        )

        tetrisWorld = TetrisWorld()
        gameHandler = GameHandler(tetrisWorld)
        inputHandler = InputHandler(gameHandler)
        renderer = WorldRenderer(tetrisWorld, canvas)

        gameHandler.start()
    }

    override fun start(primaryStage: Stage?) {
        val root = Group()
        val gameScene = Scene(root)

        primaryStage?.title = "Tetris"
        primaryStage?.scene = gameScene

        root.children.add(canvas)

        gameScene.setOnKeyReleased { inputHandler.handleReleased(it) }
        gameScene.setOnKeyPressed { inputHandler.handlePressed(it) }

        var lastUpdateTime = System.nanoTime()
        object : AnimationTimer() { // https://kotlinlang.org/docs/reference/object-declarations.html
            override fun handle(now: Long) {
                val delta = TimeUnit.MILLISECONDS.convert(
                        now - lastUpdateTime,
                        TimeUnit.NANOSECONDS)

                gameHandler.update(delta)
                renderer.render(delta)

                lastUpdateTime = now
            }
        }.start()

        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(TetrisGameApplication::class.java)
        }
    }
}
