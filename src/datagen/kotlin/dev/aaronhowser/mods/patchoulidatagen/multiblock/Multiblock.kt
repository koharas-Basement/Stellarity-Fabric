package dev.aaronhowser.mods.patchoulidatagen.multiblock

import com.google.gson.JsonObject

interface Multiblock {

	fun toJson(): JsonObject

	interface Builder {
		fun build(): Multiblock
	}

}