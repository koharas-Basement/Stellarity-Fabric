


//? <= 1.21.1 {
package xyz.kohara.stellarity.datagen.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBook;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookCategory;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookElement;
import dev.aaronhowser.mods.patchoulidatagen.book_element.PatchouliBookEntry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import xyz.kohara.stellarity.Stellarity;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class PatchouliBookProvider implements DataProvider {
    protected final DataGenerator generator;
    protected final HolderLookup.Provider registries;
    protected final String bookName;
    protected final String modId = Stellarity.MOD_ID;
    protected final PackOutput packOutput;

    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PatchouliBookProvider(DataGenerator generator, HolderLookup.Provider registries, String bookName, PackOutput packOutput) {
        this.generator = generator;
        this.registries = registries;
        this.bookName = bookName;
        this.packOutput = packOutput;
    }

    @Override
    public String getName() {
        return "Patchouli Book: " + bookName;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        // Attempt to obtain PackOutput from generator. Adjust if your DataGenerator accessor differs.


        Set<String> bookLocations = new HashSet<>();
        String bookDefaultPath = "assets/" + modId + "/patchouli_books/" + bookName + "/en_us";

        List<CompletableFuture<?>> futures = new ArrayList<>();

        Consumer<PatchouliBookElement> elementConsumer = element -> {
            boolean addedSuccessfully = bookLocations.add(element.getSaveName());
            if (!addedSuccessfully) {
                ResourceLocation rl = Stellarity.id(element.getSaveName());
                throw new IllegalStateException("Duplicate book element [" + rl + "]");
            }

            if (element instanceof PatchouliBookEntry) {
                Path entryFolder = resolvePath(packOutput, bookDefaultPath + "/entries/" + element.getSaveName() + ".json");
                saveData(futures, gson, output, (PatchouliBookEntry) element, entryFolder);
            } else if (element instanceof PatchouliBookCategory) {
                Path categoryFolder = resolvePath(packOutput, bookDefaultPath + "/categories/" + element.getSaveName() + ".json");
                saveData(futures, gson, output, (PatchouliBookCategory) element, categoryFolder);
            } else if (element instanceof PatchouliBook) {
                Path headerFolder = resolvePath(packOutput, "data/" + modId + "/patchouli_books/" + bookName + "/" + element.getSaveName() + ".json");
                saveData(futures, gson, output, (PatchouliBook) element, headerFolder);
            }
        };

        buildPages(elementConsumer);

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    private <T extends PatchouliBookElement> void saveData(
        List<CompletableFuture<?>> futures,
        Gson gson,
        CachedOutput cache,
        T bookElement,
        Path bookElementPath
    ) {
        CompletableFuture<?> future = DataProvider.saveStable(cache, bookElement.toJson(registries), bookElementPath);
        futures.add(future);
    }

    private Path resolvePath(PackOutput path, String pathOther) {
        return path.getOutputFolder().resolve(pathOther);
    }

    public abstract void buildPages(Consumer<PatchouliBookElement> consumer);

    public static final String RESET = "$()";
    public static final String BR = "$(br)";
    public static final String BR2 = "$(br2)";
    public static final String LI = "$(li)";
    public static final String LI2 = "$(li2)";
    public static final String LI3 = "$(li3)";

    public static final String OBFUSCATED = "$(k)";
    public static final String BOLD = "$(l)";
    public static final String STRIKETHROUGH = "$(m)";
    public static final String UNDERLINE = "$(n)";
    public static final String ITALIC = "$(o)";

    public enum TextColor {
        BLACK("$(0)"),
        DARK_BLUE("$(1)"),
        DARK_GREEN("$(2)"),
        DARK_AQUA("$(3)"),
        DARK_RED("$(4)"),
        DARK_PURPLE("$(5)"),
        GOLD("$(6)"),
        GRAY("$(7)"),
        DARK_GRAY("$(8)"),
        BLUE("$(9)"),
        GREEN("$(a)"),
        AQUA("$(b)"),
        RED("$(c)"),
        LIGHT_PURPLE("$(d)"),
        YELLOW("$(e)"),
        WHITE("$(f)");

        private final String code;

        TextColor(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public static String colored(TextColor textColor, String text) {
        return textColor.code + text + RESET;
    }

    public static String internalLink(String linkTo, String text) {
        return "$(l:" + linkTo + ")" + text + "$(/l)";
    }

    public static String internalLink(String linkTo, String anchor, String text) {
        return "$(l:" + linkTo + "#" + anchor + ")" + text + "$(/l)";
    }

    public static String keybind(String keybind) {
        return "$(k:" + keybind + ")";
    }

    public static String tooltip(String tooltip, String text) {
        return "$(t:" + tooltip + ")" + text + "$(/t)";
    }

    public static String command(String command, String text) {
        return "$(c:/" + command + ")" + text + "$(/c)";
    }

    public static String lines(String... lines) {
        return String.join(BR, lines);
    }

    public static String doubleSpacedLines(String... lines) {
        return String.join(BR2, lines);
    }
}

//?}