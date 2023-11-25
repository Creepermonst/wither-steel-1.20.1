package net.tcs.wither_steel.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.tcs.wither_steel.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.tcs.wither_steel.WitherSteel.MOD_ID;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useWithersteelSwordModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (stack.isOf(ModItems.WITHER_STEEL_SWORD) && renderMode != ModelTransformationMode.GUI) {
			return ((ItemRendererAccessor) this).withersteel$getModels().getModelManager().getModel(new ModelIdentifier(MOD_ID, "wither_steel_sword_no_gui", "inventory"));
		}
		return value;
	}
}
