package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation

/**
 * This is an empty page with no text
 *
 * See [Page Types - Empty Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#empty-pages)
 */
class EmptyPage private constructor(
	private val drawFiller: Boolean,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "empty"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		if (drawFiller) {
			json.addProperty("draw_filler", true)
		}
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<EmptyPage, Builder>() {
		private var drawFiller: Boolean = false

		fun drawFiller(drawFiller: Boolean): Builder {
			this.drawFiller = drawFiller
			return this
		}

		override fun build(): EmptyPage {
			return EmptyPage(
				drawFiller = drawFiller,
				advancement = advancement,
				flag = flag,
				anchor = anchor
			)
		}

		companion object {
			@JvmStatic
			fun setup(): Builder = Builder()
		}
	}

}