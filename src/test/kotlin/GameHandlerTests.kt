import org.badgrades.tetris.GameHandler
import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.badgrades.tetris.world.TetrisWorld
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.awt.Point

class GameHandlerTests {

    lateinit var tetrisWorld: TetrisWorld
    lateinit var gameHandler: GameHandler

    @Before fun setUp() {
        tetrisWorld = TetrisWorld()
        gameHandler = GameHandler(tetrisWorld)
    }

    @Test fun `tetris should exist`() {
        tetrisWorld.placedBlocks.addAll(listOf(
                Block(BlockType.O, Point(0, 1)),
                Block(BlockType.O, Point(2, 1)),
                Block(BlockType.O, Point(4, 1)),
                Block(BlockType.O, Point(6, 1)),
                Block(BlockType.O, Point(8, 1))
        ))

        assertThat(
                gameHandler.doesTetrisExistAtY(0),
                `is`(true)
        )
        assertThat(
                gameHandler.doesTetrisExistAtY(1),
                `is`(true)
        )
    }

    @Test fun `tetris should not exist`() {
        tetrisWorld.placedBlocks.addAll(listOf(
                Block(BlockType.O, Point(2, 1)),
                Block(BlockType.O, Point(4, 1)),
                Block(BlockType.O, Point(6, 1)),
                Block(BlockType.O, Point(8, 1))
        ))

        assertThat(
                gameHandler.doesTetrisExistAtY(0),
                `is`(false)
        )
        assertThat(
                gameHandler.doesTetrisExistAtY(1),
                `is`(false)
        )
    }

    @Test fun `can detect tetris`() {

        tetrisWorld.placedBlocks.addAll(listOf(
                // First and second row
                Block(BlockType.O, Point(0, 1)),
                Block(BlockType.O, Point(2, 1)),
                Block(BlockType.O, Point(4, 1)),
                Block(BlockType.O, Point(6, 1)),
                Block(BlockType.O, Point(8, 1)),

                // Partial third and fourth
                Block(BlockType.O, Point(0, 3)),
                Block(BlockType.O, Point(2, 3)),
                Block(BlockType.O, Point(4, 3)),
                Block(BlockType.O, Point(6, 3)),
                Block(BlockType.I, Point(8, 2))
        ))

        assertThat(
                gameHandler.getYValuesWithTetris().size,
                `is`(2)
        )

        tetrisWorld.placedBlocks.add(Block(BlockType.I, Point(9, 2)))

        assertThat(
                gameHandler.getYValuesWithTetris().size,
                `is`(4)
        )
    }
}