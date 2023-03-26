# FeatherTips

FeatherTips is a paper plugin for minecraft servers.  
This plugin allows administrators to conveniently make information available to their players.  

Administrators write out a short format and a long format tips for each topic of their choice.  
The short format tips are randomly auto-broadcast to everyone at a set interval in config.yml.  
The long format tips are available via `/tip <topic>` with the correct permission node.

This plugin adds `/servertime` which displays the time in a set timezone set in config.yml.

This plugin allows you to create tip-groups who will be sent a random tip from a specific list everytime they log on.   
Suggested use: specify your staff ranks and add staff role specific tips.


### Permission Nodes:
    feather.tips.tip                 -    /tip <topic>             -    View a tip.
    feather.tips.tips                -    /tips                    -    Displays all topics as hoverable labels.
    feather.tips.tip.others          -    /tip <topic> <player>    -    Send another player a tip.
    feather.tips.broadcast           -    /broadcast <topic>       -    Broadcast a short format tip to everyone.
    feather.tips.reload              -    /tips reload             -    Reload the plugin.
    feather.tips.servertime          -    /servertime              -    Displays the time in the timezone set in config.yml.
    feather.tips.login.<tip-group>   -                             -    Recieve group specific tip when logging on.

