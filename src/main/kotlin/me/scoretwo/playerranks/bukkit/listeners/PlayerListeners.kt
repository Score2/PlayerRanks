package me.scoretwo.playerranks.bukkit.listeners

import me.scoretwo.playerranks.bukkit.core.PlayerRank
import me.scoretwo.playerranks.bukkit.core.playersRank
import me.scoretwo.playerranks.bukkit.onUpdate
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onExecute(e : AsyncPlayerChatEvent) {
        val player = e.player

        onUpdate(player)
    }

    @EventHandler
    fun onExecute(e: PlayerQuitEvent) {
        playersRank.remove(e.player)
    }

    /*@EventHandler
    fun onExecute(e : PlayerJoinEvent) {
        val player = e.player

        PlayerRanks.onUpdate(player)
    }*/

}