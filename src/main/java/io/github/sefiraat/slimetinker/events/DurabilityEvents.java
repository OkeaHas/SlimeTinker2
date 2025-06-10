package io.github.sefiraat.slimetinker.events;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.sefiraat.slimetinker.events.friend.EventFriend;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;

public final class DurabilityEvents {

    private DurabilityEvents() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static void headSmithium(EventFriend friend) {
        if (ThreadLocalRandom.current().nextInt(1, 3) == 1) {
            friend.setCancelEvent(true);
        }
    }

    public static void rodAluBronze(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 1);
    }

    public static void headSolder(@Nonnull EventFriend friend) {
        ItemStack tool = friend.getTool();
        
        // Periksa apakah tool adalah null
        if (tool == null) {
            System.out.println("Tool is null, cannot proceed.");
            return; // Atau lakukan penanganan lain yang sesuai
        }

        ItemMeta im = tool.getItemMeta();
        
        // Periksa apakah ItemMeta adalah null
        if (im == null) {
            System.out.println("ItemMeta is null, cannot proceed.");
            return; // Atau lakukan penanganan lain yang sesuai
        }

        Damageable damageable = (Damageable) im;
        damageable.setDamage(tool.getType().getMaxDurability() - 1);
        tool.setItemMeta(im);
        friend.setCancelEvent(true);
    }


    public static void headAluminum(@Nonnull EventFriend friend) {
        ItemStack tool = friend.getTool();
        if (tool == null) {
            return; // alat tidak ada, langsung keluar dari fungsi
        }
        if (ThreadLocalRandom.current().nextInt(1, 4) == 1) {
            ItemUtils.incrementRepair(tool, 1);
            friend.setCancelEvent(true);
        }
    }


    public static void rodAluminum(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 1);
    }

    public static void explosive(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 17);
    }

    public static void headSingInfinity(EventFriend friend) {
        friend.setDurabilityMod(0);
    }

    public static void headMythril(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 0.5);
    }

    public static void headInfinity(EventFriend friend) {
        friend.setDurabilityMod(0);
    }

    public static void headSingAluminum(@Nonnull EventFriend friend) {
        ItemStack tool = friend.getTool();
        if (tool == null) {
            return; // Hentikan jika tidak ada alat
        }
        if (ThreadLocalRandom.current().nextInt(1, 4) == 1) {
            ItemUtils.incrementRepair(tool, 2);
            friend.setCancelEvent(true);
        }
    }

    public static void rodSingAluminum(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 1);
    }

    public static void rodAdvancedAlloy(@Nonnull EventFriend friend) {
        ItemStack tool = friend.getTool();
        
        if (tool == null) {
            return;
        }

        ItemMeta im = tool.getItemMeta();
        
        if (im == null || !(im instanceof Damageable)) {
            return; 
        }

        Damageable d = (Damageable) im;

        if (d.getDamage() < 50) {
            return;
        }

        ItemStack repairItem = new ItemStack(Material.IRON_INGOT, 1);
        
        // Periksa apakah pemain memiliki item yang cukup
        if (friend.getPlayer().getInventory().containsAtLeast(repairItem, 1)) {
            ItemUtils.repairItem(tool, 50); // Perbaiki alat
            friend.getPlayer().getInventory().removeItem(repairItem); // Hapus item dari inventori
            friend.getPlayer().sendMessage(ThemeUtils.SUCCESS + "Your tool was repaired with some iron you had lying around!");
        }
    }

    public static void headScrap(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 3);
    }

    public static void plateBrass(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 2);
    }

    public static void linksBrass(EventFriend friend) {
        friend.setDurabilityMod(friend.getDurabilityMod() + 1);
    }

    public static void plateHardened(EventFriend friend) {
        friend.setCancelEvent(true);
    }
}
