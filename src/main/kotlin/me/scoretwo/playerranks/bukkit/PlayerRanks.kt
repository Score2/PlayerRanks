package me.scoretwo.playerranks.bukkit

import me.scoretwo.playerranks.bukkit.core.PlayerRank
import me.scoretwo.playerranks.bukkit.core.Rank
import me.scoretwo.playerranks.bukkit.hook.PlaceholderAPIHook
import me.scoretwo.playerranks.bukkit.listeners.OtherListeners
import me.scoretwo.playerranks.bukkit.listeners.PlayerListeners
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class PlayerRanks : JavaPlugin() {

    override fun onEnable() {
        instance = this

        onReload()

        PlaceholderAPIHook().register()
        getCommandMap().register("PlayerRanks",Commands())
        Bukkit.getPluginManager().registerEvents(OtherListeners(), this)
        Bukkit.getPluginManager().registerEvents(PlayerListeners(), this)
    }

    companion object {
        lateinit var instance : PlayerRanks

        val ranks : MutableMap<String, Rank> = HashMap()

        fun onUpdate(player: Player) {
            if (!PlayerRank.playerRank.containsKey(player)) {
                PlayerRank.playerRank[player] = PlayerRank(player)
                return
            }

            for (rank in ranks.values) {
                PlayerRank.playerRank[player]!!.onUpdate(rank)
            }
        }

        fun onReload() {
            instance.saveDefaultConfig()
            ranks.clear()
            instance.reloadConfig()

            for (rankName in instance.config.getKeys(false)) {
                val rank = instance.config.getConfigurationSection(rankName)
                ranks[rankName] = Rank(rank, rankName)
            }

            for (player in Bukkit.getOnlinePlayers()) {
                if (player == null) continue

                onUpdate(player)
            }
        }

        fun getCommandMap() : CommandMap {
            return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
        }
    }

}