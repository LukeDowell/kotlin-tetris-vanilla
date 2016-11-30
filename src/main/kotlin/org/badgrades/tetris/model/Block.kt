package org.badgrades.tetris.model

import java.awt.Point
import java.awt.geom.AffineTransform
import java.util.*

/**
 * A block is a collection of filled points generated from a list of "offest points"
 * found in BlockType
 */
class Block(val blockType: BlockType, startingPosition: Point) : Cloneable {

    val cells: MutableList<Point>

    companion object {
        fun getRandom(startingBlockPosition: Point) : Block {
            val randomBlockType = BlockType.values()[Random().nextInt(BlockType.values().size)]
            return Block(randomBlockType, startingBlockPosition.clone() as Point)
        }
    }

    init {
        cells = mutableListOf(startingPosition)
        for(offset in blockType.offsets) {
            val startingClone = startingPosition.clone() as Point
            startingClone.translate(offset.x, offset.y)
            cells.add(startingClone)
        }
    }

    fun move(dx: Int, dy: Int) = cells.forEach { it.translate(dx, dy) }

    fun intersectsWith(block : Block) : Boolean = block.cells.filter { cells.contains(it) }.isNotEmpty()

    /**
     *
     * Future consideration: How do we handle wall kicks?
     * http://tetris.wikia.com/wiki/Wall_kick
     *
     * Rotation source:
     * http://stackoverflow.com/questions/9985473/java-rotate-point-around-another-by-specified-degree-value
     */
    fun rotate(clockwise : Boolean) {
        val center = cells[1]
        val radians: Double

        if(clockwise) // I think this might be backwards
            radians = Math.toRadians(90.0)
        else
            radians = Math.toRadians(-90.0)

        val rotationInstance = AffineTransform.getRotateInstance(
                radians,
                center.x.toDouble(),
                center.y.toDouble()
        )

        for(cell in cells)
            rotationInstance.transform(cell, cell)
    }

    public override fun clone(): Block {
        val clone = Block(blockType, Point(0,0))
        clone.cells.clear()
        this.cells.forEach { clone.cells.add(it.clone() as Point) }
        return clone
    }
}
