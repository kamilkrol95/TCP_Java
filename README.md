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

## Running Client Script

When you are in the udp folder:

```bash
$ java -cp <Path to udp folder> udp.Client <Server IP address> <Server TCP port>
$ java -cp ~/Documents/AAU/ udp.Client 192.168.1.9 9876  (i.a.)
```
Good luck! 