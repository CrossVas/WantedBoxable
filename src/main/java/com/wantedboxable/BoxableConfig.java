package com.wantedboxable;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.Objects;

public class BoxableConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec spec;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> PREFIXES;

    static {
        BUILDER.push("wantedboxable");
        PREFIXES = BUILDER.comment("""
                [Minecraft Restart Required!]\s
                A list of wanted boxable prefixes/registry names.\s
                Using prefixes: lexicon, mana - for Botania's Lexicon Book and Mana Tablet. \s
                Using registry names: crafting_table, enchanting_table - for vanilla crafting and enchanting tables... why?""")
                .defineList("boxable_prefixes", List.of("lexicon", "mana"), Objects::nonNull);
        BUILDER.pop();
        spec = BUILDER.build();
    }
}
