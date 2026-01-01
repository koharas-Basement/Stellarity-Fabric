package dev.aaronhowser.mods.patchoulidatagen.page.defaults

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.page.AbstractPage
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addProperty
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike

/**
 * This page is used to display a crafting recipe.
 *
 * See [Default Page Types - Crafting Recipe Pages](https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#crafting-recipe-pages)
 */
class CraftingRecipePage private constructor(
	private val recipeOne: ResourceLocation,
	private val recipeTwo: ResourceLocation?,
	private val title: String?,
	private val text: String?,
	advancement: ResourceLocation?,
	flag: String?,
	anchor: String?
) : AbstractPage(advancement, flag, anchor) {

	override fun getPageType(): String = "crafting"

	override fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
		super.addToJson(json, registries)

		json.apply {
			addProperty("recipe", recipeOne)
			addIfNotNull("recipe2", recipeTwo)
			addIfNotNull("title", title)
			addIfNotNull("text", text)
		}
	}

	companion object {
		@JvmStatic
		fun builder(): Builder = Builder.setup()
	}

	class Builder private constructor() : AbstractPage.Builder<CraftingRecipePage, Builder>() {
		private var recipeOne: ResourceLocation? = null
		private var recipeTwo: ResourceLocation? = null
		private var title: String? = null
		private var text: String? = null

		fun mainRecipe(recipeOutput: ItemLike): Builder {
			val itemName = recipeOutput.asItem().toString()
			this.recipeOne = ResourceLocation.tryParse(itemName)
			return this
		}

		fun mainRecipe(recipeId: ResourceLocation): Builder {
			this.recipeOne = recipeId
			return this
		}

		fun mainRecipe(recipeId: String): Builder {
			this.recipeOne = ResourceLocation.tryParse(recipeId)
			return this
		}

		fun secondaryRecipe(recipeOutput: ItemLike): Builder {
			val itemName = recipeOutput.asItem().toString()
			this.recipeTwo = ResourceLocation.tryParse(itemName)
			return this
		}

		fun secondaryRecipe(recipeId: ResourceLocation): Builder {
			this.recipeTwo = recipeId
			return this
		}

		fun secondaryRecipe(recipeId: String): Builder {
			this.recipeTwo = ResourceLocation.tryParse(recipeId)
			return this
		}

		fun title(title: String): Builder {
			this.title = title
			return this
		}

		fun text(text: String): Builder {
			this.text = text
			return this
		}

		fun text(translatable: Component): Builder {
			this.text = translatable.string
			return this
		}

		override fun build(): CraftingRecipePage {
			require(recipeOne != null) { "Main recipe must be set!" }

			return CraftingRecipePage(
				recipeOne = recipeOne!!,
				recipeTwo = recipeTwo,
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