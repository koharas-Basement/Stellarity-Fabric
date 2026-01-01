package dev.aaronhowser.mods.patchoulidatagen.util

import com.google.gson.JsonObject
import com.mojang.serialization.Codec
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation


//? 1.21.1 {
/*import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.core.component.DataComponentPatch

import com.mojang.serialization.JsonOps
import net.minecraft.core.HolderLookup

import dev.aaronhowser.mods.aaron.AaronExtensions.cast
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.nbt.NbtOps
import net.minecraft.resources.RegistryOps

*///? }


object Util {

    @JvmStatic
    fun JsonObject.addProperty(key: String, value: ResourceLocation) {
        this.addProperty(key, value.toString())
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: String?) {
        if (value != null) {
            this.addProperty(key, value)
        }
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: Number?) {
        if (value != null) {
            this.addProperty(key, value)
        }
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: Boolean?) {
        if (value != null) {
            this.addProperty(key, value)
        }
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: ResourceLocation?) {
        if (value != null) {
            this.addProperty(key, value.toString())
        }
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: Component?) {
        if (value != null) {
            //? 1.20.1 {
            this.add(key, Component.Serializer.toJsonTree(value))
            //? } else {
            /*val element = ComponentSerialization.CODEC
                .encodeStart(JsonOps.INSTANCE, value)
                .result()
                .orElseThrow()
            this.add(key, element)
            *///? }
        }
    }

    @JvmStatic
    fun JsonObject.addIfNotNull(key: String, value: JsonObject?) {
        if (value != null) {
            this.add(key, value)
        }
    }

    //? 1.21.1 {
    /*fun getComponentPatchString(
        componentPatch: DataComponentPatch,
        registries: HolderLookup.Provider
    ): String {
        val sb = StringBuilder()

        sb.append("[")

        var first = true

        for ((componentType, componentValue) in componentPatch.entrySet()) {
            val componentTypeId = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(componentType) ?: continue
            val componentCodec = componentType.codec() ?: continue

            if (first) {
                first = false
            } else {
                sb.append(",")
            }

            if (componentValue.isPresent) {
                sb.append(componentTypeId).append("=")

                val v = if (componentCodec == Codec.BOOL) {
                    componentValue.get()
                } else {
                    val context = RegistryOps.create(NbtOps.INSTANCE, registries)
                    componentCodec.encodeStart(context, componentValue.get().cast()).getOrThrow()
                }

                sb.append(v)
            } else {
                sb.append("!").append(componentTypeId)
            }
        }

        sb.append("]")
        return sb.toString()
    }
    *///? }

}