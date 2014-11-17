#!/bin/bash

current_dir=$(pwd)
ant compile

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"128.237.185.8 8000\""'
