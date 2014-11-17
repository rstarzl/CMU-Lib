#!/bin/bash

current_dir=$(pwd)
ant compile

osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummyMaster; ant run-master -Dargs=\"8000\""'
