package com.mohistmc.youer.bukkit.block;

import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import org.bukkit.craftbukkit.block.CraftBlock;

/**
 * @author Mgazul by MohistMC
 * @date 2023/7/25 19:54:50
 */
public class MohistBlockSnapshot extends CraftBlock {

    private final BlockState blockState;

    public MohistBlockSnapshot(BlockSnapshot blockSnapshot, boolean current) {
        super(blockSnapshot.getLevel(), blockSnapshot.getPos());
        this.blockState = current ? blockSnapshot.getCurrentState() : blockSnapshot.getState();
    }

    @Override
    public BlockState getNMS() {
        return blockState;
    }

    public static MohistBlockSnapshot fromBlockSnapshot(BlockSnapshot blockSnapshot, boolean current) {
        return new MohistBlockSnapshot(blockSnapshot, current);
    }
}
