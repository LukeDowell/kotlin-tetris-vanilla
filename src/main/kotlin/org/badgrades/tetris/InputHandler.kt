package org.badgrades.tetris

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

class InputHandler(val gameHandler: GameHandler) {

    var isShiftPressed = false

    fun handlePressed(keyEvent: KeyEvent) = when(keyEvent.code) {
        KeyCode.UP -> {
            gameHandler.attemptToMove(0, -1)
        }
        KeyCode.DOWN -> {
            gameHandler.gravityPeriod = GameHandler.FAST_DROP_SPEED
        }
        KeyCode.LEFT -> {
            gameHandler.attemptToMove(0, -1)
        }
        KeyCode.RIGHT -> {
            gameHandler.attemptToMove(0, -1)
        }
        KeyCode.SPACE -> {
            if(isShiftPressed)
                gameHandler.attemptToRotate(clockwise = false)
            else
                gameHandler.attemptToRotate(clockwise = true)
        }
        KeyCode.SHIFT -> {
            isShiftPressed = true
        }
        else -> Any() // This is kind of weird, not sure how to handle it better
    }

    fun handleReleased(keyEvent: KeyEvent) = when(keyEvent.code) {
        KeyCode.DOWN -> {
            gameHandler.gravityPeriod = GameHandler.NORMAL_DROP_SPEED
        }
        KeyCode.SHIFT -> {
            isShiftPressed = false
        }
        else -> Any()
    }
}
