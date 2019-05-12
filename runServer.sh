source ~/.bashrc

f_path=$(pwd)
cd ..
parent_path=$(pwd)
cd $f_path

java -cp $parent_path:${CLASSPATH} udp.Server
