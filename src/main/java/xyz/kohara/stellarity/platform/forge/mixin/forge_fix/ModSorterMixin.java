//? if forge {
package xyz.kohara.stellarity.platform.forge.mixin.forge_fix;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraftforge.fml.loading.ModSorter;
import net.minecraftforge.fml.loading.UniqueModListBuilder;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ModSorter.class)
public class ModSorterMixin {
    @ModifyExpressionValue(
        method = "buildUniqueList",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/fml/loading/UniqueModListBuilder;buildUniqueList()Lnet/minecraftforge/fml/loading/UniqueModListBuilder$UniqueModListData;"
        )
    )
    private UniqueModListBuilder.UniqueModListData minecraftModAlwaysPresent(UniqueModListBuilder.UniqueModListData original) {
        Map<String, List<ModFile>> map = new HashMap<>(original.modFilesByFirstId());
        for (ModFile modFile : original.modFiles()) initial: {
            if (!modFile.getProvider().name().equals("minecraft")) continue;
            map.computeIfAbsent("minecraft", k -> new ArrayList<>());
            List<ModFile> modFiles = map.get("minecraft");
            for (ModFile file : modFiles) {
                if (file.getProvider().name().equals("minecraft")) break initial;
            }
            modFiles.add(modFile);
            map.put("minecraft", modFiles);
        }
        return original;
    }
}
//? }