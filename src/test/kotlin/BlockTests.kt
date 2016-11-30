import org.badgrades.tetris.GameHandler
import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.badgrades.tetris.world.TetrisWorld
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import java.awt.Point

class BlockTests {

    lateinit var tetrisWorld: TetrisWorld
    lateinit var gameHandler: GameHandler

    @Before fun setUp() {
        tetrisWorld = TetrisWorld()
        gameHandler = GameHandler(tetrisWorld)
    }

    @Test fun `i block cell initialization`() {
        val iBlock = Block(BlockType.I, Point(0, 1))
        val iBlockMutableList = mutableListOf(Point(0,1), Point(0,2), Point(0,3), Point(0,4))
        assertThat(
                iBlock.cells,
                `is`(iBlockMutableList)
        )
    }

    @Test fun `i block cell rotation`() {
        // TODO
    }

    @Test fun `block should intersect`() {
        val iBlock = Block(BlockType.I, Point(4, 4))
        val zBlock = Block(BlockType.Z, Point(4, 4))

        assertThat(
                iBlock.intersectsWith(zBlock),
                `is`(true)
        )
    }

    @Test fun `block should not intersect`() {
        val iBlock = Block(BlockType.I, Point(0, 0))
        val zBlock = Block(BlockType.Z, Point(6, 6))

        assertThat(
                iBlock.intersectsWith(zBlock),
                `is`(false)
        )
    }

    @Test fun `block should rotate`() {

    }

    @Test fun `block should not rotate`() {

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