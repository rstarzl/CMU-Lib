#!/bin/bash

current_dir=$(pwd)
ant compile

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-master -Dargs=\"8000\""'
