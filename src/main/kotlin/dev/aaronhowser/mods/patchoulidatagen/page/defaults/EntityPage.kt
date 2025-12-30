package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType

/**
 * This is an empty page with no text
 *
 * See [Page Types - Entity Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#entity-pages)
 */
class EntityPage private constructor(
	private val entity: String,
	private val scale: Float?,
	private val offset: Float?,
	private val rotate: Boolean?,
	private val defaultRotation: Float?,
	private val name: String?,
	private val text: String?,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "entity"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		json.apply {
			addProperty("entity", entity)
			addIfNotNull("scale", scale)
			addIfNotNull("offset", offset)
			addIfNotNull("rotate", rotate)
			addIfNotNull("default_rotation", defaultRotation)
			addIfNotNull("name", name)
			addIfNotNull("text", text)
		}
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<EntityPage, Builder>() {

		private var entity: String? = null
		private var scale: Float? = null
		private var offset: Float? = null
		private var rotate: Boolean? = null
		private var defaultRotation: Float? = null
		private var name: String? = null
		private var text: String? = null

		fun entity(entityType: EntityType<*>): Builder {
			this.entity = entityType.toString()
			return this
		}

		fun scale(scale: Float): Builder {
			this.scale = scale
			return this
		}

		fun offset(offset: Float): Builder {
			this.offset = offset
			return this
		}

		fun nonRotatable(): Builder {
			this.rotate = false
			return this
		}

		fun defaultRotation(defaultRotation: Float): Builder {
			this.defaultRotation = defaultRotation
			return this
		}

		fun name(name: String): Builder {
			this.name = name
			return this
		}

		fun name(name: Component): Builder {
			this.name = name.string
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

		override fun build(): EntityPage {
			require(entity != null) { "Entity must be set" }
			return EntityPage(
				entity = entity!!,
				scale = scale,
				offset = offset,
				rotate = rotate,
				defaultRotation = defaultRotation,
				name = name,
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