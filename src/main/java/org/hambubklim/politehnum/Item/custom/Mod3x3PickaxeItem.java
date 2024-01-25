package org.hambubklim.politehnum.Item.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.hambubklim.politehnum.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Mod3x3PickaxeItem extends PickaxeItem  {
    public Mod3x3PickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine (ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && miner.isSneaking()&&isStoneLikeBlock(state)) {
            stack.damage(1, miner, (p) -> p.sendToolBreakStatus(miner.getActiveHand()));
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos targetPos = pos.add(x, y, z);
                        BlockState targetState = world.getBlockState(targetPos);
                        if(targetState.getBlock()==state.getBlock()) {
                            float hardness = state.getHardness(world, targetPos);

                            if (hardness >= 0 && hardness <= 50 && isStoneLikeBlock(targetState)) { // Adjust the hardness range as needed
                                targetState.getBlock().onBreak(world, targetPos, targetState, (PlayerEntity) miner);
                                world.breakBlock(targetPos, true, miner);
                                stack.damage(1, miner, (p) -> p.sendToolBreakStatus(miner.getActiveHand()));
                            }
                        }
                    }
                }
            }
        }
        else if(!world.isClient && miner.isSneaking()&&isOreLikeBlock(state)) {
            BlockDestroy(stack, world, state, pos, miner);
        }
        return true;
    }
    private boolean isStoneLikeBlock(BlockState state) {
        return state.isOf(Blocks.STONE) || state.isOf(Blocks.COBBLESTONE) || state.isOf(Blocks.ANDESITE)
                || state.isOf(Blocks.DIORITE) || state.isOf(Blocks.GRANITE)
                || state.isOf(Blocks.DEEPSLATE)|| state.isOf(Blocks.TUFF)
                || state.isOf(Blocks.COBBLED_DEEPSLATE)|| state.isOf(Blocks.NETHERRACK)
                || state.isOf(Blocks.BLACKSTONE)|| state.isOf(Blocks.END_STONE);
        // Add more stone-like blocks as needed
    }
    private boolean isOreLikeBlock(BlockState state) {
        return state.isOf(Blocks.COAL_ORE) || state.isOf(Blocks.COPPER_ORE) || state.isOf(Blocks.DEEPSLATE_COPPER_ORE)
                || state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_COAL_ORE)
                || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)|| state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)
                || state.isOf(Blocks.DEEPSLATE_GOLD_ORE)|| state.isOf(Blocks.GOLD_ORE)
                || state.isOf(Blocks.EMERALD_ORE)|| state.isOf(Blocks.DEEPSLATE_IRON_ORE)
                || state.isOf(Blocks.IRON_ORE)|| state.isOf(Blocks.LAPIS_ORE)
                || state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)|| state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)
                || state.isOf(Blocks.REDSTONE_ORE)|| state.isOf(Blocks.NETHER_GOLD_ORE)
                || state.isOf(Blocks.NETHER_QUARTZ_ORE)|| state.isOf(ModBlocks.POLITEHNUM_ORE)
                || state.isOf(ModBlocks.DEEPSLATE_POLITEHNUM_ORE)|| state.isOf(ModBlocks.NETHER_POLITEHNUM_ORE)
                || state.isOf(ModBlocks.END_STONE_POLITEHNUM_ORE);
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

                        if (hardness >= 0 && hardness <= 50 && isOreLikeBlock(targetState)) { // Adjust the hardness range as needed
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
