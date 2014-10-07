#!/bin/bash

current_dir=$(pwd)
ant compile

#set masterPath to $current_dir & "/run-master.sh"
#set slavePath to $current_dir & "/run-slave.sh"

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ./run-master.sh"'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ./run-slave.sh"'
