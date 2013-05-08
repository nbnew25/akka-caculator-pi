akka-caculator-pi
=================

akka-caculator-pi

http://doc.akka.io/docs/akka/2.0.1/intro/getting-started-first-java.html

Introduction
Welcome to the first tutorial on how to get started with Akka and Java. We assume that you already know what Akka and Java are and will now focus on the steps necessary to start your first project.

There are two variations of this first tutorial:

creating a standalone project and run it from the command line
creating a Maven project and running it from within Maven
Since they are so similar we will present them both.

The sample application that we will create is using actors to calculate the value of Pi. Calculating Pi is a CPU intensive operation and we will utilize Akka Actors to write a concurrent solution that scales out to multi-core processors. This sample will be extended in future tutorials to use Akka Remote Actors to scale out on multiple machines in a cluster.

We will be using an algorithm that is called “embarrassingly parallel” which just means that each job is completely isolated and not coupled with any other job. Since this algorithm is so parallelizable it suits the actor model very well.

Here is the formula for the algorithm we will use:

![ScreenShot] (http://doc.akka.io/docs/akka/2.0.1/_images/pi-formula.png)

In this particular algorithm the master splits the series into chunks which are sent out to each worker actor to be processed. When each worker has processed its chunk it sends a result back to the master which aggregates the total result.

**SETUP**
On Linux/Unix/Mac systems:

$ export JAVA_HOME=..root of Java distribution..
$ export PATH=$PATH:$JAVA_HOME/bin

Download akka-2.1.2.zip
$ cd /usr/local/akka-2.1.2
$ export AKKA_HOME=/usr/local/akka-2.1.2
$ echo $AKKA_HOME
/usr/local/akka-2.1.2
