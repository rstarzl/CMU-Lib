#!/bin/bash

current_dir=$(pwd)
ant compile

#set masterPath to $current_dir & "/run-master.sh"
#set slavePath to $current_dir & "/run-slave.sh"

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-master -Dargs=\"8000\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"128.237.185.8 8000\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"128.237.185.8 8000\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"128.237.185.8 8000\""'
osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"128.237.185.8 8000\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/Git/CMU-Lib/communication; ant run-slave -Dargs=\"slave5\""'
