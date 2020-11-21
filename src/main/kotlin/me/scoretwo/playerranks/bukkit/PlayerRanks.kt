package me.scoretwo.playerranks.bukkit

import me.scoretwo.playerranks.bukkit.core.PlayerRank
import me.scoretwo.playerranks.bukkit.core.Rank
import me.scoretwo.playerranks.bukkit.hook.PlaceholderAPIHook
import me.scoretwo.playerranks.bukkit.listeners.OtherListeners
import me.scoretwo.playerranks.bukkit.listeners.PlayerListeners
import me.scoretwo.utils.configuration.file.YamlConfiguration
import me.scoretwo.utils.configuration.patchs.loadConfiguration
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class PlayerRanks : JavaPlugin() {

    override fun onEnable() {
        instance = this

        reload()

        PlaceholderAPIHook().register()
        getCommandMap().register("PlayerRanks",Commands())
        Bukkit.getPluginManager().registerEvents(OtherListeners(), this)
        Bukkit.getPluginManager().registerEvents(PlayerListeners(), this)
    }

    companion object {
        lateinit var instance : PlayerRanks
        lateinit var file: File
        lateinit var config: YamlConfiguration

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

        fun reload() {
            instance.saveDefaultConfig()
            ranks.clear()
            file = File(instance.dataFolder, "config.yml")
            config = file.loadConfiguration()

            for (rankName in config.getKeys(false)) {
                if (rankName.toLowerCase() == "settings") {
                    continue
                }
                val rank = config.getConfigurationSection(rankName)
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