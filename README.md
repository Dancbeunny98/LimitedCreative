# LimitedCreative

A plugin meant for allowing the server owner/developer to give out creative mode with restrictions.
Check out the video for this plugin!
https://youtu.be/Z21t_WqM8ho

Features:
Custom blacklisted blocks - refer to the config.yml
Custom blacklisted interactables - refer to the config.yml

Permissions:
  "limitedcreative" - the base permission allowing one to run the /limitedcreative or /lc command. Also allows them to give LC to other players.
  "limitedcreative.admin" - users with this are given permission to bypass the blacklist for blocks and interactables.

The "master" branch contains the code for this project.

Known Bugs:
  - Players are able to remove LC armor (The leather) if they shift-click on it.
      Progress - Unsure how to fix
  - Some blocks (i.e. minecarts and its variants) are still allowed to be placed even when put in blacklisted blocks.
      Progress - I know the issue, but not the fix
