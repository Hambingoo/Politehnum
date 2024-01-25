
package org.hambubklim.politehnum.Item.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Mod3x3AxeItem extends AxeItem {

    public Mod3x3AxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine (ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if(!world.isClient && miner.isSneaking()&&isWoodLikeBlock(state)) {
            BlockDestroy(stack, world, state, pos, miner);
        }
        return true;
    }

    private boolean isWoodLikeBlock(BlockState state) {
        return state.isOf(Blocks.ACACIA_LOG) || state.isOf(Blocks.BIRCH_LOG) || state.isOf(Blocks.CHERRY_LOG)
                || state.isOf(Blocks.JUNGLE_LOG) || state.isOf(Blocks.MANGROVE_LOG)|| state.isOf(Blocks.DARK_OAK_LOG)
                || state.isOf(Blocks.OAK_LOG)|| state.isOf(Blocks.SPRUCE_LOG);
        // Add more stone-like blocks as needed
    }
    private boolean BlockDestroy(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner)
    {

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos targetPos = pos.add(x, y, z);
                    BlockState targetState = world.getBlockState(targetPos);
                    if(targetState.getBlock()==state.getBlock())
                    {
                        float hardness = state.getHardness(world, targetPos);

                        if (hardness >= 0 && hardness <= 50 && isWoodLikeBlock(targetState)) { // Adjust the hardness range as needed
                            targetState.getBlock().onBreak(world, targetPos, targetState, (PlayerEntity) miner);
                            world.breakBlock(targetPos, true, miner);
                            stack.damage(1, miner, (p) -> p.sendToolBreakStatus(miner.getActiveHand()));
                        }
                        BlockDestroy(stack, world, targetState, targetPos, miner);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.politehnum.3x3tools.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
