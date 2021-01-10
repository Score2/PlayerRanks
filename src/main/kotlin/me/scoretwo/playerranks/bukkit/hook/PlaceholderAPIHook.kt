package me.scoretwo.playerranks.bukkit.hook

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.scoretwo.playerranks.bukkit.config
import me.scoretwo.playerranks.bukkit.core.PlayerRank
import me.scoretwo.playerranks.bukkit.core.playersRank
import me.scoretwo.playerranks.bukkit.instance
import me.scoretwo.utils.bukkit.configuration.yaml.patchs.*
import org.bukkit.entity.Player

class PlaceholderAPIHook : PlaceholderExpansion() {

    init {
        papiHook = this
    }

    override fun persist(): Boolean {
        return true
    }

    override fun getVersion(): String {
        return instance.description.version
    }

    override fun getAuthor(): String {
        return instance.description.authors.toString()
    }

    override fun getIdentifier(): String {
        return instance.description.name
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {

        if (!playersRank.containsKey(player)) {
            playersRank[player] = PlayerRank(player)
        }

        val playerRank = playersRank[player]!!

        for (rank in playerRank.ranks.keys) {
            if (rank.name == params) {

                return playerRank.ranks[rank] ?: return config.getString(config.getLowerCaseNode("settings.unknown-instead"))
            }
        }

        return config.getString(config.getLowerCaseNode("settings.unknown-instead"))
    }

}
lateinit var papiHook: PlaceholderAPIHook