#!/bin/bash

current_dir=$(pwd)
ant compile

#set masterPath to $current_dir & "/run-master.sh"
#set slavePath to $current_dir & "/run-slave.sh"

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-master"'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave1\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave2\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave3\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave4\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave5\""'
