
//? <= 1.21.1 {
package xyz.kohara.stellarity.datagen;

import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBook;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookCategory;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookElement;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.datagen.provider.PatchouliBookProvider;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.function.Consumer;

public class EndonomiconBookProvider extends PatchouliBookProvider {

    public EndonomiconBookProvider(FabricDataOutput fabricDataOutput, DataGenerator generator, HolderLookup.Provider registries, String bookName) {
        super(generator, registries, bookName, fabricDataOutput);
    }


    @Override
    public void buildPages(@NotNull Consumer<PatchouliBookElement> consumer) {
        PatchouliBook book = PatchouliBook.builder()
            .setBookText(
                Stellarity.MOD_ID,
                "Generated via Java!",
                "This book was generated using the Patchouli DataGen library in Java."
            )
            .disableBook()
            .save(consumer);


        //TODO: update with actual chorus armor item
        PatchouliBookCategory armors = PatchouliBookCategory.builder().book(book)
            .setDisplay("Armors", "All Stellarity Armors", StellarityItems.CHORUS_PLATING)
            .save(consumer, "armors");
    }

}
//?}