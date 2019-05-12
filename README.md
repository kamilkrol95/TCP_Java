# TCP_Java

This allows us to connect some crazy build production lines!

## Java Instalation

Use [this](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04) tutorial, it works for me!

```bash
$ sudo apt-get update
$ sudo apt-get install default-jre
$ sudo apt-get install default-jdk
```

Setting JAVA_HOME environment variable:

Here you got path to your java installation place:

```bash
$ sudo update-alternatives --config java
```

## Cloning repository and compiling

In any place use this code:

```bash
$ git clone -b master https://github.com/kamilkrol95/TCP_Java.git udp
$ cd udp
$ javac *.java
```
## Connection with databse

To ensure connection with dataase we need to use JConnector library. 
We need to downlad it from [here](https://dev.mysql.com/downloads/file/?id=485757), and follow [this install instruction](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-binary-installation.html).

The most important part is adding our library to CLASSPATH:
```bash
$ export CLASSPATH=/<path>/mysql-connector-java-<ver>.jar:${CLASSPATH}
$ export CLASSPATH=/usr/share/java/mysql-connector-java-8.0.16.jar:${CLASSPATH} (i.a.)
```

## Running Client Script

When you are in the udp folder:

```bash
$ java -cp <Path to udp folder> udp.Client <Server IP address> <Server TCP port>
$ java -cp ~/IdeaProjects/JavaCommunication/out/production/JavaCommunication udp.Client 192.168.1.9 9876  (i.a.)
```

Following the same instruction as when adding mysql-connector to CLASSPATH, we can do the same thing with path of our project. In my case it would be:

```bash
$ export CLASSPATH=~/IdeaProjects/JavaCommunication/out/production/JavaCommunication:${CLASSPATH}
```

Then when launching the java file we can simply use:

```bash
$ java udp.Client <Server IP address> <Server TCP port>
```

## Plug & play 

You can use preset bash scripts to run both server and all possible client stations. 
All you need to do is run: 

```bash
$ . runServer.sh
``` 

to start server. 
Similar for each client is as following:

```bash
$ . runStation1.sh 2
```

where: 
- in the name of script is index of station that you are starting
- after the name, there is one orgument for order ID 

If you like, you can change the index of station by changing the following number in script:

```bash
$ java -cp $parent_path udp.Client localhost 9876 <stattion_ID> $1
```

FYI
1) Running server script should source all necessary java libraries to make it possible to run application (if all of previous dependencies are installed already), then it start the server console application and server is running until it is shut down (Ctrl + C)
2) Running each of station scripts with orderID paramteter simulates arrival of product at specific station, you need to run client script for every station each time you want to simualte barcode scanning 

Good luck! 