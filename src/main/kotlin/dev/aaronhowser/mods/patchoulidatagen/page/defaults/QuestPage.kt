package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

/**
 * This is an empty page with no text
 *
 * See [Page Types - Quest Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#quest-pages)
 */
class QuestPage private constructor(
	private val trigger: String?,
	private val title: String?,
	private val text: String?,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "quest"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		json.apply {
			addIfNotNull("trigger", trigger)
			addIfNotNull("title", title)
			addIfNotNull("text", text)
		}
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<QuestPage, Builder>() {
		private var trigger: String? = null
		private var title: String? = null
		private var text: String? = null

		fun trigger(trigger: String): Builder {
			this.trigger = trigger
			return this
		}

		fun trigger(trigger: Component): Builder {
			this.trigger = trigger.string
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

		override fun build(): QuestPage {
			return QuestPage(
				trigger = trigger,
				title = title,
				text = text,
				advancement = advancement,
				flag = flag,
				anchor = anchor
			)
		}

		companion object {
			fun setup(): Builder = Builder()
		}
	}
}