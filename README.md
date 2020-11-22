### PlayerRanks *-* 玩家头衔
![](https://img.shields.io/github/license/Score2/PlayerRanks?color=blue&style=for-the-badge)
![](https://img.shields.io/github/downloads/Score2/PlayerRanks/total?color=green&style=for-the-badge)
![](https://img.shields.io/github/v/release/Score2/PlayerRanks?color=purple&style=for-the-badge)
![](https://img.shields.io/github/issues/Score2/PlayerRanks?style=for-the-badge)
![](https://img.shields.io/github/issues-pr/Score2/PlayerRanks?style=for-the-badge)

***
> Starting from v0.12, there is no need to install [KotlinAPI](https://www.mcbbs.net/thread-1080136-1-1.html).
>
> 自 v0.12 版本开始, 无需再安装 [KotlinAPI](https://www.mcbbs.net/thread-1080136-1-1.html).
##### Features | 特性:
* According to the numerical judgment, change the corresponding title of the player to realize the title.
* Support PlaceholderAPI as a data variable to promote title changes.
* The plugin has been running on the 1.16 server for 3 months without any performance problems.
* Configuration file nodes can ignore case.
* The plugin is open source on GitHub under the GPLv3.0 agreement, please make sure to use the plugin in compliance with the agreement.
* 根据数值判断改玩家对应称号, 实现头衔.
* 支持 PlaceholderAPI 作为数据变量推动头衔变动.
* 该插件在已有 1.16 服务器运行 3 个月之长, 无任何性能问题.
* 配置文件节点均可忽略大小写.
* 插件在 GitHub 以 GPLv3.0 协议开源, 请确保在遵守协议的情况下使用该插件.

***

##### Commands | 命令:
* */pr help* - List all help information | 显示帮助列表
* */pr update* - Update the title of the specified player | 对指定玩家更新头衔
* */pr reload* - Reload configuration file | 重新载入配置文件

***

##### Images | 图片:
![](http://mc3.roselle.vip:602/plugins/playerranks/images/1.jpg)


##### Config | 配置文件:
```
settings:
  # Use the title instead for the title of the player outside the numerical range.
  # 对超出数值范围玩家的称号使用该称号代替.
  unknown-instead: '&7Unknown'

head_rank:
  # count
  # 数值
  score: '%player_level%'
  # Format: count: tag
  # 格式: 数值: 对应称号
  nodes:
    - "0: &2新手"
    - "50: &a猎手"
    - "100: &d巫师"
    - "200: &7&l守卫"
    - "500: &3骑士"
    - "1000: &b勇士"
    - "1000: &e天使"
    - "5000: &f&l天子"
    - "10000: &b&l冠军"
```

***
##### Releases | 发布:
[Click to enter | 点击进入](https://github.com/Score2/PlayerRanks/releases)

***
##### Links | 链接:
[<img src="http://mc3.roselle.vip:602/icons/github.svg" width="64" height="64"/>](https://github.com/Score2/PlayerRanks) 　
[<img src="http://mc3.roselle.vip:602/icons/wiki.svg" width="64" height="64"/>](https://github.com/Score2/PlayerRanks/wiki) 　

> If you have any questions or feature suggestions, please go to [GitHub Issue](https://github.com/Score2/PlayerRanks/issues) for feedback.
>
> 如果有任何问题或者功能建议, 欢迎前往 [GitHub Issue](https://github.com/Score2/PlayerRanks/issues) 反馈

*本插件所用所有代码均为原创,不存在借用/抄袭等行为*