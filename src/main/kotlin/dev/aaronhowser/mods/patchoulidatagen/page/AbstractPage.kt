package dev.aaronhowser.mods.patchoulidatagen.page

import com.google.gson.JsonObject
import dev.aaronhowser.mods.patchoulidatagen.util.Util.addIfNotNull
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import xyz.kohara.stellarity.Stellarity

abstract class AbstractPage(
    protected val advancement: ResourceLocation?,
    protected val flag: String?,
    protected val anchor: String?
) {

    abstract fun getPageType(): String

    open fun addToJson(json: JsonObject, registries: HolderLookup.Provider) {
        json.apply {
            addIfNotNull("advancement", advancement)
            addIfNotNull("flag", flag)
            addIfNotNull("anchor", anchor)
        }
    }

    /**
     * Overwrite this if you have a custom page type
     */
    open fun getPageTypeLocation(): ResourceLocation {
        return Stellarity.id("patchouli", getPageType())
    }

    open fun getPageTypeId(): String = getPageTypeLocation().toString()

    @Suppress("UNCHECKED_CAST")
    abstract class Builder<T : AbstractPage, S : Builder<T, S>>
    protected constructor() {
        protected var advancement: ResourceLocation? = null
        protected var flag: String? = null
        protected var anchor: String? = null

        fun advancement(advancement: ResourceLocation): S {
            this.advancement = advancement
            return this as S
        }

        fun flag(flag: String): S {
            this.flag = flag
            return this as S
        }

        fun anchor(anchor: String): S {
            this.anchor = anchor
            return this as S
        }

        abstract fun build(): T
    }

}