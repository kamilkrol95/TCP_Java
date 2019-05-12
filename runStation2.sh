f_path=$(pwd)
cd ..
parent_path=$(pwd)
cd $f_path

java -cp $parent_path udp.Client localhost 9876 2 $1