package me.scoretwo.playerranks.bukkit.listeners

import me.scoretwo.playerranks.bukkit.PlayerRanks
import me.scoretwo.playerranks.bukkit.core.PlayerRank
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onExecute(e : AsyncPlayerChatEvent) {
        val player = e.player

        PlayerRanks.onUpdate(player)
    }

    @EventHandler
    fun onExecute(e: PlayerQuitEvent) {
        PlayerRank.playerRank.remove(e.player)
    }

    /*@EventHandler
    fun onExecute(e : PlayerJoinEvent) {
        val player = e.player

        PlayerRanks.onUpdate(player)
    }*/

}