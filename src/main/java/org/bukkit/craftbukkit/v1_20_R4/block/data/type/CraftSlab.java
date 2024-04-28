package org.bukkit.craftbukkit.v1_20_R4.block.data.type;

import org.bukkit.block.data.type.Slab;
import org.bukkit.craftbukkit.v1_20_R4.block.data.CraftBlockData;

public abstract class CraftSlab extends CraftBlockData implements Slab {

    private static final net.minecraft.world.level.block.state.properties.EnumProperty<?> TYPE = getEnum("type");

    @Override
    public org.bukkit.block.data.type.Slab.Type getType() {
        return this.get(CraftSlab.TYPE, org.bukkit.block.data.type.Slab.Type.class);
    }

    @Override
    public void setType(org.bukkit.block.data.type.Slab.Type type) {
        this.set(CraftSlab.TYPE, type);
    }
}
