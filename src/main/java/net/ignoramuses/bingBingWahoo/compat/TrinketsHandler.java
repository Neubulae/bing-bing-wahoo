package net.ignoramuses.bingBingWahoo.compat;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.Optional;

import static net.ignoramuses.bingBingWahoo.BingBingWahoo.MYSTERIOUS_CAP;

public class TrinketsHandler {
	public static boolean capEquipped(PlayerEntity player) {
		Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player);
		return component.map(trinketComponent -> trinketComponent.isEquipped(MYSTERIOUS_CAP)).orElse(false);
	}
	
	@Nullable
	public static ItemStack getCapStack(LivingEntity entity) {
		Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
		if (component.isPresent()) {
			for (Pair<SlotReference, ItemStack> pair : component.get().getEquipped(MYSTERIOUS_CAP)) {
				return pair.getRight();
			}
		}
		return null;
	}
	
	public static void equipInHatSlot(LivingEntity entity, ItemStack stack) {
		Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
		if (component.isPresent()) {
			for (Pair<SlotReference, ItemStack> pair : component.get().getEquipped(item -> true)) {
				SlotReference slot = pair.getLeft();
				TrinketInventory inv = slot.inventory();
				if (inv.getSlotType().getName().equals("hat")) {
					inv.setStack(slot.index(), stack);
				}
			}
		}
	}
}