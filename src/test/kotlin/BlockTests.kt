import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import java.awt.Point

class BlockTests {

    @Test fun `i block cell initialization`() {
        val iBlock = Block(BlockType.I, Point(0, 1))
        val iBlockMutableList = mutableListOf(Point(0,1), Point(0,2), Point(0,3), Point(0,4))
        assertThat(
                iBlock.cells,
                `is`(iBlockMutableList)
        )
    }
    @Test fun `block should intersect`() {
        val iBlock = Block(BlockType.I, Point(4, 3))
        val zBlock = Block(BlockType.Z, Point(4, 3))

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
}