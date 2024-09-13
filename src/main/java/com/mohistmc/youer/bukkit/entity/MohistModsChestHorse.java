package com.mohistmc.youer.bukkit.entity;

import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftChestedHorse;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.NotNull;

public class MohistModsChestHorse extends CraftChestedHorse {

    public MohistModsChestHorse(CraftServer server, AbstractChestedHorse entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "MohistModsChestHorse{" + getType() + '}';
    }

    @Override
    public AbstractChestedHorse getHandle() {
        return (AbstractChestedHorse) entity;
    }

    @Override
    public @NotNull Horse.Variant getVariant() {
        return Horse.Variant.FORGE_MOD_CHEST_HORSE;
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return EntityCategory.NONE;
    }
}
