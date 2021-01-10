package me.scoretwo.playerranks.bukkit.core

import me.clip.placeholderapi.PlaceholderAPI
import me.scoretwo.playerranks.bukkit.allRanks
import org.bukkit.entity.Player
import kotlin.collections.HashMap

class PlayerRank(val player: Player) {

    val ranks : MutableMap<Rank, String> = HashMap()

    init {
        for (rank in allRanks.values) {
            onUpdate(rank)
        }
    }

    fun onUpdate(rank: Rank) {
        val score = PlaceholderAPI.setPlaceholders(player, rank.expr).toInt()
        val sortedNode = rank.nodes.entries.sortedBy { it.key }.associateBy({ it.key }, { it.value })
        val keys = sortedNode.keys.toList()
        val values = sortedNode.values.toList()

        if (score < keys[0]) {
            ranks[rank] = values[0]
        }

//        println("尝试对 ${player.name} 进行数据更新: $score")
        for (i in keys.indices) {
            if (i == keys.size - 1) ranks[rank] = values[keys.size - 1]
//            println("   $i ${keys[i]} ${rank.nodes[i]}")
//            println("   key: ${keys[i]}, key+1: ${keys[i+1]}")
            if (score >= keys[i] && score < keys[i + 1]) {
//                println("情况1")
                ranks[rank] = rank.nodes[keys[i]]!!
                return
            }
        }
//        println("超出设置范围")
        ranks[rank] = rank.nodes[keys[values.size]]!!
        return
    }

}
val playersRank = mutableMapOf<Player, PlayerRank>()