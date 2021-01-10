package me.scoretwo.playerranks.bukkit

import me.scoretwo.playerranks.bukkit.core.PlayerRank
import me.scoretwo.playerranks.bukkit.core.Rank
import me.scoretwo.playerranks.bukkit.core.playersRank
import me.scoretwo.playerranks.bukkit.hook.PlaceholderAPIHook
import me.scoretwo.playerranks.bukkit.listeners.OtherListeners
import me.scoretwo.playerranks.bukkit.listeners.PlayerListeners
import me.scoretwo.utils.bukkit.configuration.yaml.file.YamlConfiguration
import me.scoretwo.utils.bukkit.configuration.yaml.patchs.loadConfiguration
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

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
            PlaceholderAPIHook().register()
        getCommandMap().register("PlayerRanks",Commands())
        Bukkit.getPluginManager().registerEvents(OtherListeners(), this)
        Bukkit.getPluginManager().registerEvents(PlayerListeners(), this)
    }
}

lateinit var instance : PlayerRanks
lateinit var config: YamlConfiguration

val allRanks = mutableMapOf<String, Rank>()

fun onUpdate(player: Player) {
    if (!playersRank.containsKey(player)) {
        playersRank[player] = PlayerRank(player)
        return
    }

    for (rank in allRanks.values) {
        playersRank[player]!!.onUpdate(rank)
    }

}

fun reload() {
    instance.saveDefaultConfig()
    allRanks.clear()
    val file = File(instance.dataFolder, "config.yml")
    config = file.loadConfiguration()

    for (rankName in config.getKeys(false)) {
        if (rankName.toLowerCase() == "settings") {
            continue
        }
        val rankSection = config.getConfigurationSection(rankName)
        allRanks[rankName] = Rank(rankSection, rankName)
    }

    for (player in Bukkit.getOnlinePlayers()) {
        if (player == null) continue

        onUpdate(player)
    }
}

fun getCommandMap() : CommandMap {
    return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
}