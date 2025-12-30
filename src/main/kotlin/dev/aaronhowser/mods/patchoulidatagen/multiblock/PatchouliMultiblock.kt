package dev.aaronhowser.mods.patchoulidatagen.multiblock

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.Property

class PatchouliMultiblock(
	private val pattern: Array<Array<String>>,
	private val mappings: Map<Char, String>,
	private val symmetrical: Boolean?,
	private val offset: Array<Int>?
) : Multiblock {

	override fun toJson(): JsonObject {
		val json = JsonObject()

		val mappingJson = JsonObject()
		for ((char, block) in mappings) {
			mappingJson.addProperty(
				char.toString(),
				block
			)
		}
		json.add("mapping", mappingJson)

		val allLayersArray = JsonArray()

		for (layer in pattern) {
			val layerArray = JsonArray()

			for (row in layer) {
				layerArray.add(row)
			}

			allLayersArray.add(layerArray)
		}

		json.add("pattern", allLayersArray)

		json.addIfNotNull("symmetrical", symmetrical)

		if (offset != null && offset.size == 3) {
			val offsetArray = JsonArray()
			for (value in offset) {
				offsetArray.add(value)
			}
			json.add("offset", offsetArray)
		}

		return json
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.create()
	}

	class Builder private constructor() : Multiblock.Builder {

		private val mappingCharacters: MutableSet<Char> = mutableSetOf()
		private val mappings: MutableMap<Char, String> = mutableMapOf()
		private val multiblock: ArrayList<Array<String>> = arrayListOf()

		private var symmetrical: Boolean? = null
		private var offset: Array<Int>? = null

		fun setSymmetrical(): Builder {
			this.symmetrical = true
			return this
		}

		fun setOffset(x: Int, y: Int, z: Int): Builder {
			this.offset = arrayOf(x, y, z)
			return this
		}

		fun pattern(vararg layerPattern: Array<String>): Builder {
			multiblock.addAll(layerPattern)
			return this
		}

		fun map(char: Char, block: Block): Builder {
			require(char !in mappingCharacters) { "Character '$char' is already mapped to a block." }

			mappingCharacters.add(char)
			val blockId = BuiltInRegistries.BLOCK.getKey(block)
			mappings[char] = blockId.toString()

			return this
		}

		fun <T : Comparable<T>> map(
			char: Char,
			block: Block,
			property: Property<T>,
			value: T
		): Builder {
			require(char !in mappingCharacters) { "Character '$char' is already mapped to a block." }

			mappingCharacters.add(char)
			val blockId = BuiltInRegistries.BLOCK.getKey(block)
			val blockStateString = "${blockId}[${property.name}=${property.getName(value)}]"
			mappings[char] = blockStateString

			return this
		}

		fun <T : Comparable<T>> map(
			char: Char,
			block: Block,
			properties: Map<Property<T>, T>
		): Builder {
			require(char !in mappingCharacters) { "Character '$char' is already mapped to a block." }

			mappingCharacters.add(char)
			val blockId = BuiltInRegistries.BLOCK.getKey(block)
			val propertiesString = properties.entries.joinToString(",") { (property, value) ->
				"${property.name}=${property.getName(value)}"
			}
			val blockStateString = "${blockId}[$propertiesString]"
			mappings[char] = blockStateString

			return this
		}

		fun map(char: Char, blockTag: TagKey<Block>): Builder {
			require(char !in mappingCharacters) { "Character '$char' is already mapped to a block." }

			mappingCharacters.add(char)
			mappings[char] = "#${blockTag.location}"

			return this
		}

		override fun build(): Multiblock {
			require(mappingCharacters.isNotEmpty()) { "At least one mapping must be defined." }
			require(multiblock.isNotEmpty()) { "At least one layer must be defined." }

			val amountZeroes = multiblock.sumOf { layer ->
				layer.sumOf { row ->
					row.count { char -> char == '0' }
				}
			}

			require(amountZeroes == 1) {
				"Exactly one '0' character must be present to represent the center. See https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/multiblocks#the-pattern"
			}

			return PatchouliMultiblock(
				pattern = multiblock.toTypedArray(),
				mappings = mappings,
				symmetrical = symmetrical,
				offset = offset
			)
		}

		companion object {
			fun create(): Builder = Builder()
		}
	}

}