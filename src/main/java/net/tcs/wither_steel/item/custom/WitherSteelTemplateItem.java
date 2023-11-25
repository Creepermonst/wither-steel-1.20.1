package net.tcs.wither_steel.item.custom;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class WitherSteelTemplateItem extends SmithingTemplateItem {

	private static final Formatting TITLE_FORMATTING;
	private static final Formatting DESCRIPTION_FORMATTING;
	private static final String TRANSLATION_KEY;
	private static final Text INGREDIENTS_TEXT;
	private static final Text APPLIES_TO_TEXT;
	private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE;
	private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE;
	private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE;
	private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE;
	private static final Identifier EMPTY_SLOT_SWORD_TEXTURE;
	private static final Identifier EMPTY_SLOT_INGOT_TEXTURE;
	private final Text baseSlotDescriptionText;
	private final Text additionsSlotDescriptionText;
	private final List<Identifier> emptyBaseSlotTextures;
	private final List<Identifier> emptyAdditionsSlotTextures;

	private static final Text WITHER_STEEL_UPGRADE_TEXT;
	private static final Text WITHER_STEEL_UPGRADE_APPLIES_TO_TEXT;
	private static final Text WITHER_STEEL_UPGRADE_INGREDIENTS_TEXT;
	private static final Text WITHER_STEEL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT;
	private static final Text WITHER_STEEL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT;

	public WitherSteelTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
		super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
		this.baseSlotDescriptionText = baseSlotDescriptionText;
		this.additionsSlotDescriptionText = additionsSlotDescriptionText;
		this.emptyBaseSlotTextures = emptyBaseSlotTextures;
		this.emptyAdditionsSlotTextures = emptyAdditionsSlotTextures;
	}

	public static WitherSteelTemplateItem createWitherSteelUpgrade() {
		return new WitherSteelTemplateItem(WITHER_STEEL_UPGRADE_APPLIES_TO_TEXT, WITHER_STEEL_UPGRADE_INGREDIENTS_TEXT, WITHER_STEEL_UPGRADE_TEXT, WITHER_STEEL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, WITHER_STEEL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, getWitherSteelUpgradeEmptyBaseSlotTextures(), getWitherSteelUpgradeEmptyAdditionsSlotTextures());
	}

	private static List<Identifier> getWitherSteelUpgradeEmptyBaseSlotTextures() {
		return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE);
	}

	private static List<Identifier> getWitherSteelUpgradeEmptyAdditionsSlotTextures() {
		return List.of(EMPTY_SLOT_INGOT_TEXTURE);
	}

	public Text getBaseSlotDescription() {
		return this.baseSlotDescriptionText;
	}

	public Text getAdditionsSlotDescription() {
		return this.additionsSlotDescriptionText;
	}

	public List<Identifier> getEmptyBaseSlotTextures() {
		return this.emptyBaseSlotTextures;
	}

	public List<Identifier> getEmptyAdditionsSlotTextures() {
		return this.emptyAdditionsSlotTextures;
	}

	public String getTranslationKey() {
		return TRANSLATION_KEY;
	}

	static {
		TITLE_FORMATTING = Formatting.GRAY;
		DESCRIPTION_FORMATTING = Formatting.BLUE;
		TRANSLATION_KEY = Util.createTranslationKey("item", new Identifier("smithing_template"));
		INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.ingredients"))).formatted(TITLE_FORMATTING);
		APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.applies_to"))).formatted(TITLE_FORMATTING);
		EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
		EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
		EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
		EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");
		EMPTY_SLOT_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
		EMPTY_SLOT_INGOT_TEXTURE = new Identifier("item/empty_slot_ingot");

		WITHER_STEEL_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", new Identifier("wither_steel_upgrade"))).formatted(TITLE_FORMATTING);
		WITHER_STEEL_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.wither_steel.applies_to"))).formatted(DESCRIPTION_FORMATTING);
		WITHER_STEEL_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.wither_steel.ingredients"))).formatted(DESCRIPTION_FORMATTING);
		WITHER_STEEL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.wither_steel.base_slot_description")));
		WITHER_STEEL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.wither_steel.additions_slot_description")));
	}
}
