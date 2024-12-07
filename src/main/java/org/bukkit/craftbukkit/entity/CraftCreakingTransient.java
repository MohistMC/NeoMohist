package org.bukkit.craftbukkit.entity;

import net.minecraft.world.entity.monster.creaking.CreakingTransient;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Creaking;

public class CraftCreakingTransient extends CraftCreaking implements Creaking {

    public CraftCreakingTransient(CraftServer server, CreakingTransient entity) {
        super(server, entity);
    }

    @Override
    public CreakingTransient getHandle() {
        return (CreakingTransient) this.entity;
    }

    @Override
    public String toString() {
        return "CraftCreakingTransient";
    }
}
