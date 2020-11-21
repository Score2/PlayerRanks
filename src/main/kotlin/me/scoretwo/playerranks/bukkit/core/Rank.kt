package me.scoretwo.playerranks.bukkit.core

import me.scoretwo.utils.configuration.ConfigurationSection
import org.jetbrains.annotations.NotNull

class Rank {

    var name : String
    var expr : String
    val nodes : MutableMap<Int, String>

    constructor(section : ConfigurationSection?, name: String) {
//        println("[PlayerRanks | Debug] 尝试导入 ${name}!")
        this.nodes = HashMap()
        this.name = name
        expr = section!!.getString("score")!!

        for (node in section.getStringList("nodes")) {
//            println("1")
            if (!node.contains(": ")) continue
//            println("2")
            val array = node.split(": ").toTypedArray()
            val n = array[0].toInt()
            val s = array[1]

            this.nodes[n] = s
//            println("[PlayerRanks | Debug] 导入到 Map: $n $s")
        }
    }

    constructor(@NotNull expr : String, @NotNull nodes : MutableMap<Int, String>, @NotNull name: String) {
        this.name = name
        this.expr = expr
        this.nodes = nodes
    }


}