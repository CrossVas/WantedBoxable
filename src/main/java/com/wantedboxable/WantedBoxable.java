package com.wantedboxable;

import ic2.core.platform.recipes.helpers.Boxables;
import ic2.core.utils.config.Config;
import ic2.core.utils.config.ConfigEntry;
import ic2.core.utils.config.ConfigHandler;
import ic2.core.utils.config.ConfigSection;
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
            PREFIXES = GENERAL.addArray("boxable", "A list of wanted boxable registry names").set(new String[] {"lexicon", "mana_tablet"}).setGameRestart();
            HANDLER = new ConfigHandler("ic2c", CONFIG, false);
            HANDLER.init();
        }
    }
}
