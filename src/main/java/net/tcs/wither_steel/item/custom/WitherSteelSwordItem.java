package net.tcs.wither_steel.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.tcs.wither_steel.particle.ModParticles;

import java.util.List;
import java.util.Random;

import static net.minecraft.util.math.Direction.*;

public class WitherSteelSwordItem extends SwordItem {

	public WitherSteelSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}




	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	public int constCost = 8;
	int rangeWidth = 3;
	int rangeLength = 5;
	int witherChanceMax = 7;
	int witherChance;
	Random rand = new Random();

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack handStack = user.getStackInHand(hand);
		if (!(handStack.getNbt().getFloat("charge") <= 0)) {
			if (!world.isClient) {
				if (handStack.hasNbt()) {
					NbtCompound nbtData = handStack.getNbt();
					handStack.setNbt(nbtData);
					Box damageBox = switch (user.getHorizontalFacing()) {
						case WEST -> new Box(user.getX(), user.getY() + 2, user.getZ() - rangeWidth, user.getX() - rangeLength, user.getY(), user.getZ() + rangeWidth);
						case EAST -> new Box(user.getX(), user.getY() + 2, user.getZ() - rangeWidth, user.getX() + rangeLength, user.getY(), user.getZ() + rangeWidth);
						case NORTH -> new Box(user.getX() - rangeWidth, user.getY() + 2, user.getZ(), user.getX() + rangeWidth, user.getY(), user.getZ() - rangeLength);
						case SOUTH -> new Box(user.getX() - rangeWidth, user.getY() + 2, user.getZ(), user.getX() + rangeWidth, user.getY(), user.getZ() + rangeLength);
						default -> new Box(user.getX(), user.getY() + 2, user.getZ() - 2, user.getX() + 3, user.getY(), user.getZ() + 2);
					};
					List entityList = (world.getEntitiesByClass(Entity.class, damageBox, EntityPredicates.VALID_LIVING_ENTITY));
					entityList.remove(user);
					if (!entityList.isEmpty()) {
						for (int i = 0; i < entityList.toArray().length; i++) {
							if (!(handStack.getNbt().getFloat("charge") <= 0)) {
								Entity curEntity = (Entity) entityList.get(i);
								float curCharge = handStack.getNbt().getFloat("charge");
								if(!(constCost > curCharge)){
									nbtData.putFloat("charge", curCharge - constCost);
									curEntity.damage(curEntity.getDamageSources().playerAttack(user), 2 * constCost);
								}
								else {
									curEntity.damage(curEntity.getDamageSources().playerAttack(user), 2 * curCharge);
									nbtData.putFloat("charge", 0);
								}
							}
						}
					}
					if (handStack.getNbt().getFloat("charge") <= 0)
						nbtData.putFloat("charge", 0);
					ServerWorld serverWorld = (ServerWorld) world;
					spawnParticles(serverWorld, user.getBlockPos(), user.getHorizontalFacing());
				}
			}
		}
		return super.use(world, user, hand);
	}

	private void spawnParticles(ServerWorld serverWorld, BlockPos pos, Direction dir){
		double offsetSize = 0.25F;
		if (dir == EAST) {
			BlockPos newPos = pos.offset(Direction.Axis.X, 3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() -offsetSize,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() -offsetSize*2,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, +3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() -offsetSize,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, +1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() -offsetSize*2,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
		}
		if (dir == WEST) {
			BlockPos newPos = pos.offset(Direction.Axis.X, -2);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() +offsetSize,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() +offsetSize*2,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, +3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() +offsetSize,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.Z, +1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() +offsetSize*2,
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
		}
		if (dir == SOUTH) {
			BlockPos newPos = pos.offset(Direction.Axis.Z, 3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() ,
					newPos.getY() + 1.5, newPos.getZ() -offsetSize, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() - offsetSize*2, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, +3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() - offsetSize, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, +1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() - offsetSize*2, 1, 0, 0, 0, 0);
		}
		if (dir == NORTH) {
			BlockPos newPos = pos.offset(Direction.Axis.Z, -2);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ(), 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX() ,
					newPos.getY() + 1.5, newPos.getZ() + offsetSize, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, -1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() + offsetSize*2, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, +3);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() + offsetSize, 1, 0, 0, 0, 0);
			newPos = newPos.offset(Direction.Axis.X, +1);
			serverWorld.spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, newPos.getX(),
					newPos.getY() + 1.5, newPos.getZ() + offsetSize*2, 1, 0, 0, 0, 0);
		}
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		witherChance = rand.nextInt(witherChanceMax - 1);
		if(witherChance == 1) {
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 2), attacker);
		}
		return super.postHit(stack, target, attacker);
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return false;
	}
}
