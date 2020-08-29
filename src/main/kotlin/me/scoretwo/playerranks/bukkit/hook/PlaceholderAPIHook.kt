package me.scoretwo.playerranks.bukkit.hook

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.scoretwo.playerranks.bukkit.PlayerRanks
import me.scoretwo.playerranks.bukkit.core.PlayerRank
import org.bukkit.entity.Player

class PlaceholderAPIHook : PlaceholderExpansion() {

    init {
        instance = this
    }

    override fun persist(): Boolean {
        return true
    }

    override fun getVersion(): String {
        return PlayerRanks.instance.description.version
    }

    override fun getAuthor(): String {
        return PlayerRanks.instance.description.authors.toString()
    }

    override fun getIdentifier(): String {
        return PlayerRanks.instance.description.name
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {

        if (!PlayerRank.playerRank.containsKey(player)) {
            PlayerRank.playerRank[player] = PlayerRank(player)
        }

        val playerRank = PlayerRank.playerRank[player]!!

        for (rank in playerRank.ranks.keys) {
            if (rank.name == params) {

                return playerRank.ranks[rank] ?: return "&7NONE"
            }
        }

        return "&7NONE"
    }

    companion object {
        lateinit var instance: PlaceholderAPIHook
    }

}