package org.hambubklim.politehnum.Item.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Mod3x3ShovelItem extends ShovelItem {

    public Mod3x3ShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine (ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && miner.isSneaking()) {
            stack.damage(1, miner, (p) -> p.sendToolBreakStatus(miner.getActiveHand()));
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos targetPos = pos.add(x, y, z);
                        BlockState targetState = world.getBlockState(targetPos);
                        if(targetState.getBlock()==state.getBlock()) {
                            float hardness = state.getHardness(world, targetPos);

                            if (hardness >= 0 && hardness <= 50 && isDirtLikeBlock(targetState)) { // Adjust the hardness range as needed
                                targetState.getBlock().onBreak(world, targetPos, targetState, (PlayerEntity) miner);
                                world.breakBlock(targetPos, true, miner);
                                stack.damage(1, miner, (p) -> p.sendToolBreakStatus(miner.getActiveHand()));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    private boolean isDirtLikeBlock(BlockState state) {
        return state.isOf(Blocks.GRASS_BLOCK) || state.isOf(Blocks.DIRT) || state.isOf(Blocks.DIRT_PATH)
                || state.isOf(Blocks.SAND) || state.isOf(Blocks.SOUL_SAND)|| state.isOf(Blocks.SOUL_SOIL)
                || state.isOf(Blocks.GRAVEL)|| state.isOf(Blocks.COARSE_DIRT)|| state.isOf(Blocks.PODZOL)
                || state.isOf(Blocks.MYCELIUM)|| state.isOf(Blocks.MUD)|| state.isOf(Blocks.CLAY)
                || state.isOf(Blocks.RED_SAND)|| state.isOf(Blocks.SNOW_BLOCK);
        // Add more stone-like blocks as needed
    }

}
