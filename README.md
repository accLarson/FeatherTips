# FeatherTips

FeatherTips is a paper plugin for minecraft servers.  
This plugin allows administrators to conveniently make information available to their players.  

Administrators write out a short format and a long format tips for each topic of their choice.  
The short format tips are randomly auto-broadcast to everyone at a set interval in config.yml.  
The long format tips are available via `/tip <topic>` with the correct permission node.

This plugin adds `/servertime` which displays the time in a set timezone, currently hardcoded to "Canada/Eastern"

This plugin adds the ability to send a random message from a list to players within a specific group everytime they log on.  
In practice, this is used for staff roles, passing staff specific tips on each login.

*This plugin was designed for the server feather64.net; I plan to modify it in the future to be more generally compatible*


### Permission Nodes:
    feather.tips.tip          -    /tip <topic>             -    View a tip.
    feather.tips.tips         -    /tips                    -    Displays all topics as hoverable labels.
    feather.tips.staff        -    /tip <topic> <player>    -    Send another player a tip.
                                   /broadcast <topic>       -    Broadcast a short format tip to everyone.
    feather.tips.reload       -    /tips reload             -    Reload the plugin.
    feather.tips.servertime   -    /servertime              -    Displays the time in the timezone set in config.yml.

