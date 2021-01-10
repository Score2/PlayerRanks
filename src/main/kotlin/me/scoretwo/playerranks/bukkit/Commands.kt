package me.scoretwo.playerranks.bukkit

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class Commands : Command("playerRanks","","/playerRanks", listOf("pranks","pr")) {

    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("playerRanks.admin")) {
            sender.sendMessage("§c权限不足.")
            return true
        }
        if (args.isEmpty()) {
            onHelp(sender)
            return true
        }

        when(args[0]) {
            "update" -> {
                if (args.size != 1) {
                    onHelp(sender)
                    return true
                }

                val player = Bukkit.getPlayer(args[1])

                if (player == null) {
                    sender.sendMessage("§c目标玩家不在线")
                    return true
                }

                onUpdate(player)
                sender.sendMessage("§a操作成功")

            }
            "reload" -> {
                reload()
                sender.sendMessage("§a配置文件重载完成")
            }
        }

        return true
    }

    fun onHelp(sender: CommandSender) {
        sender.sendMessage("")
        sender.sendMessage("§b/PR Update <player> - 更新一个玩家的头衔")
        sender.sendMessage("§b/PR Reload - 重新载入配置文件")
        sender.sendMessage("")
    }

}