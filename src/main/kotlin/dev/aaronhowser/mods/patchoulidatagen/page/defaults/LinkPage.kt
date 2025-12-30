package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation

/**
 * This is an empty page with no text
 *
 * See [Page Types - Link Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#link-pages)
 */
class LinkPage(
	private val text: String?,
	private val title: String?,
	private val url: String,
	private val linkText: String,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "link"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		json.apply {
			addIfNotNull("text", text)
			addIfNotNull("title", title)
			addProperty("url", url)
			addProperty("link_text", linkText)
		}
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<LinkPage, Builder>() {
		private var url: String? = null
		private var linkText: String? = null
		private var text: String? = null
		private var title: String? = null

		fun text(text: String): Builder {
			this.text = text
			return this
		}

		fun title(title: String): Builder {
			this.title = title
			return this
		}

		fun button(
			url: String,
			linkText: String
		): Builder {
			this.url = url
			this.linkText = linkText
			return this
		}

		override fun build(): LinkPage {
			requireNotNull(url) { "URL must be set" }
			requireNotNull(linkText) { "Link text must be set" }

			return LinkPage(
				text = text,
				title = title,
				url = url!!,
				linkText = linkText!!,
				advancement = advancement,
				flag = flag,
				anchor = anchor
			)
		}

		companion object {
			@JvmStatic
			fun setup(): Builder {
				return Builder()
			}
		}
	}
}