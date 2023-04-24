package com.wantedboxable;

import ic2.core.platform.recipes.helpers.Boxables;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("wantedboxable")
public class WantedBoxable {

    public WantedBoxable() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BoxableConfig.spec, "wantedboxable.toml");
        bus.addListener(this::commonLoad);
    }

    public void commonLoad(FMLCommonSetupEvent e) {
        for (String prefix : BoxableConfig.PREFIXES.get()) {
            if (!prefix.isEmpty())
                Boxables.INSTANCE.registerPrefix(prefix);
        }
    }
}
