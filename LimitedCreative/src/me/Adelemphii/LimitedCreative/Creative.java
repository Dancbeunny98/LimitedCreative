package me.Adelemphii.LimitedCreative;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class Creative implements CommandExecutor
{
	Main main;
	
	public Creative(Main main) {
		this.main = main;
	}
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    		// This is a mess, but it works.
            if (label.equalsIgnoreCase("LimitedCreative") || label.equalsIgnoreCase("lc")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Players only m'dude.");
                    return true;
                }
                if (sender.hasPermission("limitedcreative")) {
                    Player player = (Player)sender;
                    if (args.length >= 2) {
                        sender.sendMessage(ChatColor.RED + "Usage: /limitedcreative or /lc");
                        return true;
                    }
                    if (args.length == 0) {
                        if (player.getGameMode() == GameMode.CREATIVE) {
                            player.getInventory().clear();
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.RED + player.getDisplayName() + ChatColor.GOLD + "'s gamemode has been set to" + ChatColor.RED + " Survival.");
                            main.lc.remove(player.getPlayer(), player.getUniqueId());
                            main.restoreInventory(player.getPlayer());
                        }
                        else if (player.getGameMode() != GameMode.CREATIVE) {
                            main.saveInventory(player.getPlayer());
                            creativeArmor(player);
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(ChatColor.RED + player.getDisplayName() + ChatColor.GOLD + "'s gamemode has been set to" + ChatColor.RED + " Creative.");
                            main.lc.put(player.getPlayer(), player.getUniqueId());
                        }
                    }
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage(ChatColor.RED + "That is not a valid player!");
                            return true;
                        }
                        if (target.getGameMode() == GameMode.CREATIVE) {
                            target.getInventory().clear();
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.GOLD + "'s gamemode has been set to" + ChatColor.RED + " Survival.");
                            main.lc.remove(target.getPlayer(), target.getUniqueId());
                            main.restoreInventory(target.getPlayer());
                        }
                        else if (target.getGameMode() != GameMode.CREATIVE) {
                            main.saveInventory(target.getPlayer());
                            creativeArmor(target);
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.GOLD + "'s gamemode has been set to" + ChatColor.RED + " Creative.");
                            main.lc.put(target.getPlayer(), target.getUniqueId());
                        }
                    }
                }
                else if (!sender.hasPermission("limitedcreative")) {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to do that!");
                    return true;
                }
            }
            return false;
    } // end of onCommand
    
    // Give the player in LC colored armor.
    public void creativeArmor(Player player) {
        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        ItemStack[] armor = player.getEquipment().getArmorContents();
        armor = changeColor(armor, Color.fromRGB(242, 2, 2));
        player.getEquipment().setArmorContents(armor);
    }
    
    public ItemStack[] changeColor(ItemStack[] a, Color color) {
        for (ItemStack item : a) {
            try {
                if (item.getType() == Material.LEATHER_BOOTS || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_LEGGINGS) {
                    LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
                    meta.setColor(color);
                    item.setItemMeta((ItemMeta)meta);
                }
            }
            catch (Exception ex) {}
        }
        return a;
    }
}