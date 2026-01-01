package dev.aaronhowser.mods.patchoulidatagen.book_element

import com.google.gson.JsonObject
import dev.aaronhowser.mods.aaron.AaronExtensions.isNotTrue
import dev.aaronhowser.mods.aaron.AaronExtensions.isTrue
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import java.util.function.Consumer

class PatchouliBook private constructor(
	private val bookModId: String,
	private val name: String,
	private val landingText: String,
	private val bookTexture: ResourceLocation?,
	private val fillerTexture: String?,
	private val craftingTexture: String?,
	private val textColor: Int?,
	private val headerColor: Int?,
	private val nameplateColor: Int?,
	private val linkColor: Int?,
	private val linkHoverColor: Int?,
	private val progressBarColor: Int?,
	private val progressBarBackground: Int?,
	private val openSound: ResourceLocation?,
	private val flipSound: ResourceLocation?,
	private val showProgress: Boolean?,
	private val version: String?,
	private val subtitle: String?,
	private val creativeTab: String?,
	private val advancementTab: String?,
	private val doNotGenerateBook: Boolean?,
	private val customBookItem: Item?,
	private val showToast: Boolean?,
	private val useBlockyFont: Boolean?,
	private val i18n: Boolean?,
	private val pauseGame: Boolean?,
	private val icon: ResourceLocation?
) : PatchouliBookElement {

	override fun getSaveName(): String = "book"

	fun getBookModId(): String = bookModId
	fun isTranslatable(): Boolean = i18n.isTrue()

	override fun toJson(registries: HolderLookup.Provider): JsonObject {
		val json = JsonObject()

		json.apply {
			addProperty("name", name)
			addProperty("landing_text", landingText)

			addIfNotNull("book_texture", bookTexture)
			addIfNotNull("filler_texture", fillerTexture)
			addIfNotNull("crafting_texture", craftingTexture)
			addIfNotNull("text_color", textColor)
			addIfNotNull("header_color", headerColor)
			addIfNotNull("nameplate_color", nameplateColor)
			addIfNotNull("link_color", linkColor)
			addIfNotNull("link_hover_color", linkHoverColor)
			addIfNotNull("progress_bar_color", progressBarColor)
			addIfNotNull("progress_bar_background", progressBarBackground)
			addIfNotNull("open_sound", openSound)
			addIfNotNull("flip_sound", flipSound)
			addIfNotNull("show_progress", showProgress)
			addIfNotNull("version", version)
			addIfNotNull("subtitle", subtitle)
			addIfNotNull("creative_tab", creativeTab)
			addIfNotNull("advancement_tab", advancementTab)
			addIfNotNull("do_not_generate_book", doNotGenerateBook)
			addIfNotNull("custom_book_item", customBookItem?.toString())
			addIfNotNull("show_toast", showToast)
			addIfNotNull("use_blocky_font", useBlockyFont)
			addIfNotNull("i18n", i18n)
			addIfNotNull("pause_game", pauseGame)
			addIfNotNull("icon", icon)

			addProperty("use_resource_pack", true)
		}

		return json
	}

	companion object {
		@JvmStatic
		fun builder() = Builder.header()
	}

	class Builder private constructor() {

		private var nameComponent: Component? = null
		private var landingTextComponent: Component? = null
		private var nameText: String? = null
		private var landingTextText: String? = null

		private var bookTexture: ResourceLocation? = null
		private var fillerTexture: String? = null
		private var craftingTexture: String? = null
		private var textColor: Int? = null
		private var headerColor: Int? = null
		private var nameplateColor: Int? = null
		private var linkColor: Int? = null
		private var linkHoverColor: Int? = null
		private var progressBarColor: Int? = null
		private var progressBarBackground: Int? = null
		private var openSound: ResourceLocation? = null
		private var flipSound: ResourceLocation? = null
		private var showProgress: Boolean? = null
		private var version: String? = null
		private var subtitle: String? = null
		private var creativeTab: String? = null
		private var advancementTab: String? = null
		private var doNotGenerateBook: Boolean? = null
		private var customBookItem: Item? = null
		private var showToast: Boolean? = null
		private var useBlockyFont: Boolean? = null
		private var i18n: Boolean? = null
		private var pauseGame: Boolean? = null
		private var icon: ResourceLocation? = null
		private var bookModId: String? = null

		/** Use if it's translatable */
		fun setBookComponent(
			bookId: String,
			name: Component,
			landingText: Component
		): Builder {
			if (i18n.isNotTrue()) {
				error("Don't use setBookComponent when i18n is false!")
			}

			this.nameComponent = name
			this.landingTextComponent = landingText
			this.bookModId = bookId

			return this
		}

		/** Use if it's not translatable */
		fun setBookText(
			bookModId: String,
			name: String,
			landingText: String
		): Builder {
			if (i18n.isTrue()) {
				error("Don't use setBookText when i18n is true!")
			}

			this.nameText = name
			this.landingTextText = landingText
			this.bookModId = bookModId

			return this
		}

		fun bookTexture(bookTexture: ResourceLocation): Builder {
			this.bookTexture = bookTexture
			return this
		}

		fun fillerTexture(fillerTexture: String): Builder {
			this.fillerTexture = fillerTexture
			return this
		}

		fun craftingTexture(craftingTexture: String): Builder {
			this.craftingTexture = craftingTexture
			return this
		}

		fun textColor(textColor: Int): Builder {
			this.textColor = textColor
			return this
		}

		fun headerColor(headerColor: Int): Builder {
			this.headerColor = headerColor
			return this
		}

		fun nameplateColor(nameplateColor: Int): Builder {
			this.nameplateColor = nameplateColor
			return this
		}

		fun linkColor(linkColor: Int, linkHoverColor: Int): Builder {
			this.linkColor = linkColor
			this.linkHoverColor = linkHoverColor
			return this
		}

		fun progressBarColor(progressBarColor: Int): Builder {
			this.progressBarColor = progressBarColor
			return this
		}

		fun progressBarBackground(progressBarBackground: Int): Builder {
			this.progressBarBackground = progressBarBackground
			return this
		}

		fun openSound(openSound: ResourceLocation): Builder {
			this.openSound = openSound
			return this
		}

		fun flipSound(flipSound: ResourceLocation): Builder {
			this.flipSound = flipSound
			return this
		}

		fun hideProgress(): Builder {
			this.showProgress = false
			return this
		}

		fun version(version: String): Builder {
			this.version = version
			return this
		}

		fun subtitle(subtitle: String): Builder {
			this.subtitle = subtitle
			return this
		}

		fun creativeTab(creativeTab: String): Builder {
			this.creativeTab = creativeTab
			return this
		}

		fun advancementTab(advancementTab: String): Builder {
			this.advancementTab = advancementTab
			return this
		}

		fun disableBook(): Builder {
			this.doNotGenerateBook = true
			return this
		}

		fun disableToast(): Builder {
			this.showToast = false
			return this
		}

		fun useMinecraftFont(): Builder {
			this.useBlockyFont = true
			return this
		}

		fun enableI18n(): Builder {
			this.i18n = true
			return this
		}

		fun pauseGameWhenOpened(): Builder {
			this.pauseGame = true
			return this
		}

		fun save(consumer: Consumer<PatchouliBookElement>): PatchouliBook {
			val header = build()
			consumer.accept(header)
			return header
		}

		fun build(): PatchouliBook {
			val finalName: String
			val finalLandingText: String

			if (i18n.isTrue()) {
				finalName = nameComponent?.string
					?: error("Name component must be set when i18n is enabled!")
				finalLandingText = landingTextComponent?.string
					?: error("Landing text component must be set when i18n is enabled!")
			} else {
				finalName = nameText ?: error("Name must be set when i18n is disabled!")
				finalLandingText = landingTextText ?: error("Landing text must be set when i18n is disabled!")
			}

			requireNotNull(this.bookModId) { "Book ID must be set!" }

			val header = PatchouliBook(
				bookModId = this.bookModId!!,
				name = finalName,
				landingText = finalLandingText,
				bookTexture = this.bookTexture,
				fillerTexture = this.fillerTexture,
				craftingTexture = this.craftingTexture,
				textColor = this.textColor,
				headerColor = this.headerColor,
				nameplateColor = this.nameplateColor,
				linkColor = this.linkColor,
				linkHoverColor = this.linkHoverColor,
				progressBarColor = this.progressBarColor,
				progressBarBackground = this.progressBarBackground,
				openSound = this.openSound,
				flipSound = this.flipSound,
				showProgress = this.showProgress,
				version = this.version,
				subtitle = this.subtitle,
				creativeTab = this.creativeTab,
				advancementTab = this.advancementTab,
				doNotGenerateBook = this.doNotGenerateBook,
				customBookItem = this.customBookItem,
				showToast = this.showToast,
				useBlockyFont = this.useBlockyFont,
				i18n = this.i18n,
				pauseGame = this.pauseGame,
				icon = this.icon
			)

			return header
		}


		companion object {
			fun header() = Builder()
		}

	}

}