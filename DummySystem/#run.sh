#!/bin/bash

current_dir=$(pwd)
ant compile

echo "cd $(pwd)" > run-master.sh
echo "ant run-master" >> run-master.sh
chmod +x run-master.sh

open -a Terminal.app run-master.sh

for i in {1..5}
do
    rm run-slave-$i.sh
    echo "#!/bin/bash" > run-slave-$i.sh
    echo "cd $(pwd)" >> run-slave-$i.sh
    echo "ant run-slave" >> run-slave-$i.sh
    chmod +x run-slave-$i.sh
    open -a Terminal.app run-slave-$i.sh
done



#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-master"'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"slave1\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"slave2\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"slave3\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/DummySystem; ant run-slave -Dargs=\"slave4\""'
#osascript -e 'tell app "Terminal" to do script "cd ~/Documents/wp/CMU-Lib/communication; ant run-slave -Dargs=\"slave5\""'
