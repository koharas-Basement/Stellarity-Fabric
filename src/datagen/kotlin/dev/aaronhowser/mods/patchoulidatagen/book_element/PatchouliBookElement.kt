package dev.aaronhowser.mods.patchoulidatagen.book_element

import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup

interface PatchouliBookElement {

	fun getSaveName(): String
	fun toJson(registries: HolderLookup.Provider): JsonObject

}