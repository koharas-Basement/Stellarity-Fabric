package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

/**
 * This is an empty page with no text
 *
 * See [Page Types - Image Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#image-pages)
 */
class ImagePage private constructor(
	private val images: Array<ResourceLocation>,
	private val title: String?,
	private val border: String?,
	private val text: String?,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "image"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		json.apply {

			val imageArray = JsonArray()
			for (image in images) {
				imageArray.add(image.toString())
			}
			add("images", imageArray)

			addIfNotNull("title", title)
			addIfNotNull("border", border)
			addIfNotNull("text", text)
		}
	}

	companion object {
		@JvmStatic
		fun builder() = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<ImagePage, Builder>() {
		private val images = mutableListOf<ResourceLocation>()
		private var title: String? = null
		private var border: String? = null
		private var text: String? = null

		fun addImage(vararg images: ResourceLocation): Builder {
			this.images.addAll(images)
			return this
		}

		fun title(title: String): Builder {
			this.title = title
			return this
		}

		fun title(title: Component): Builder {
			this.title = title.string
			return this
		}

		fun text(text: String): Builder {
			this.text = text
			return this
		}

		fun text(text: Component): Builder {
			this.text = text.string
			return this
		}

		override fun build(): ImagePage {
			require(images.size in 1..2) { "There must be exactly 1 or 2 images" }

			return ImagePage(
				images = images.toTypedArray(),
				title = title,
				border = border,
				text = text,
				advancement = advancement,
				flag = flag,
				anchor = anchor
			)
		}

		companion object {
			@JvmStatic
			fun setup() = Builder()
		}
	}

}