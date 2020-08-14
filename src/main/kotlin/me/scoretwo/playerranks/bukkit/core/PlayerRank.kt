package me.scoretwo.playerranks.bukkit.core

import me.clip.placeholderapi.PlaceholderAPI
import me.scoretwo.playerranks.bukkit.PlayerRanks
import org.apache.commons.lang3.StringUtils
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

class PlayerRank(val player: Player) {

    val ranks : MutableMap<Rank, String> = HashMap()

    init {
        for (rank in PlayerRanks.ranks.values) {
            onUpdate(rank)
        }
    }

    fun onUpdate(rank: Rank) {
        val score = if (StringUtils.isNumeric(rank.expr)) PlaceholderAPI.setPlaceholders(player, rank.expr).toInt() else return
        val keys = rank.nodes.keys.toMutableList()
        val values = rank.nodes.values.toMutableList()
        keys.sort()
//        println("尝试对 ${player.name} 进行数据更新: $score")
        for (i in 0 until keys.size) {
            if (i == keys.size - 1) {
                ranks[rank] = rank.nodes[i]!!
            }
//            println("   $i ${keys[i]} ${rank.nodes[i]}")
//            println("   key: ${keys[i]}, key+1: ${keys[i+1]}")
            if (score >= keys[i] && score < keys[i + 1]) {
//                println("情况1")
                ranks[rank] = rank.nodes[keys[i]]!!
                return
            }
        }
//        println("超出设置范围")
        ranks[rank] = rank.nodes[values.size]!!

    }

    companion object {
        val playerRank : MutableMap<Player, PlayerRank> = HashMap()

    }

}