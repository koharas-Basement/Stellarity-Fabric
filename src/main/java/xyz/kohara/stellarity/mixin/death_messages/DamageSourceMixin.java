package xyz.kohara.stellarity.mixin.death_messages;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
	@Unique
	private final RandomSource random = RandomSource.create();

	@Shadow
	public abstract String getMsgId();

	@WrapOperation(method = "getLocalizedDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"))
	private MutableComponent specialStellarityDeathMessages(String string, Object[] objects, Operation<MutableComponent> original) {
		String id = getMsgId();
		if (id.equals("stellarity.tamaris_execute")) {
			string += "." + random.nextInt(1, 4);
		}

		return original.call(string, objects);
	}

}
