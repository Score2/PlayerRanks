package me.scoretwo.playerranks.bukkit

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
            "reload" -> {
                PlayerRanks.onReload()
                sender.sendMessage("§a配置文件重载完成")
            }
        }

        return true
    }

    fun onHelp(sender: CommandSender) {
        sender.sendMessage("")
        sender.sendMessage("§b/PR Reload - 重新载入配置文件")
        sender.sendMessage("")
    }

}