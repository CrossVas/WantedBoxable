package com.wantedboxable;

import ic2.core.IC2;
import ic2.core.platform.recipes.helpers.Boxables;
import ic2.core.utils.config.config.*;
import ic2.core.utils.config.impl.ReloadMode;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod(WantedBoxable.ID)
public class WantedBoxable {

    public static final String ID = "wantedboxable";

    public WantedBoxable() {
        WantedBoxableConfig.initConfig();
        Arrays.stream(WantedBoxableConfig.PREFIXES.get()).forEach(entry -> {
            if (!entry.isEmpty()) {
                Boxables.INSTANCE.registerPrefix(entry);
            }
        });

    }

    public static class WantedBoxableConfig {
        public static ConfigHandler HANDLER;
        public static ConfigEntry.ArrayValue PREFIXES;

        public static void initConfig() {
            Config CONFIG = new Config(WantedBoxable.ID);
            ConfigSection GENERAL = CONFIG.add("general");
            PREFIXES = GENERAL.addArray("boxable", "A list of wanted boxable registry names").set(new String[]{"mana_tablet", "lexicon"}).setRequiredReload(ReloadMode.GAME);
            HANDLER = IC2.FILE_WATCHER.createConfig(CONFIG, ConfigSettings.withFolder("ic2c"));
            HANDLER.register();
        }
    }
}
