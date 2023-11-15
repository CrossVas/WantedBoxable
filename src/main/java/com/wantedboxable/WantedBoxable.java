package com.wantedboxable;

import carbonconfiglib.CarbonConfig;
import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import carbonconfiglib.impl.ReloadMode;
import ic2.core.platform.recipes.helpers.Boxables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

@Mod(WantedBoxable.ID)
public class WantedBoxable {

    public static final String ID = "wantedboxable";

    public WantedBoxable() {
        IEventBus EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        EVENT_BUS.addListener(this::commonLoad);
        EVENT_BUS.addListener(this::loadComplete);
    }

    public void commonLoad(FMLCommonSetupEvent e) {
        WantedBoxableConfig.init();
    }

    public void loadComplete(FMLLoadCompleteEvent e) {
        // items
        Arrays.stream(WantedBoxableConfig.PREFIXES_ITEMS.getValue()).forEach(entry -> {
            if (!entry.isEmpty()) {
                Item boxable = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry));
                if (boxable != null) {
                    Boxables.INSTANCE.registerItem(boxable);
                }
            }
        });
        // blocks
        Arrays.stream(WantedBoxableConfig.PREFIXES_BLOCKS.getValue()).forEach(entry -> {
            if (!entry.isEmpty()) {
                Block boxable = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entry));
                if (boxable != null) {
                    Boxables.INSTANCE.registerItem(boxable.asItem());
                }
            }
        });
    }

    public static class WantedBoxableConfig {

        public static ConfigEntry.ArrayConfigEntry<String> PREFIXES_ITEMS;
        public static ConfigEntry.ArrayConfigEntry<String> PREFIXES_BLOCKS;
        public static ConfigHandler HANDLER;

        public static void init()  {
            Config CONFIG = new Config("ic2c/" + WantedBoxable.ID);
            ConfigSection GENERAL = CONFIG.add("general");
            GENERAL.setComment("Wanted Boxable Items/Blocks. Requires full registry name, comma separated. Format: <mod>:<registry_name>");
            PREFIXES_ITEMS = GENERAL.addArray("boxable_items").set(new String[]{"minecraft:lever"}).setRequiredReload(ReloadMode.GAME);
            PREFIXES_BLOCKS = GENERAL.addArray("boxable_blocks").set(new String[]{"minecraft:crafting_table"}).setRequiredReload(ReloadMode.GAME);
            HANDLER = CarbonConfig.CONFIGS.createConfig(CONFIG);
            HANDLER.register();
        }
    }
}
