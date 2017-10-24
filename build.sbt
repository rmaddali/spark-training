
name := "spark-training"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

resolvers += "Conjars" at "http://conjars.org/repo"

val sparkVersion = "2.2.0" //Danno

libraryDependencies ++= Seq(

  //Testing
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",

  //Spark Core
  "org.apache.spark" %% "spark-core" % sparkVersion,

  //Spark SQL
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  //Spark Streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,

  //Hadoop AWS
  "org.apache.hadoop" % "hadoop-aws" % "2.8.1",

  //Hadoop Client for reading hdfs
  "org.apache.hadoop" % "hadoop-client" % "2.8.1",


  //Cassandra Connector
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.3",

  //Elastic Search
  "org.elasticsearch" % "elasticsearch-spark-20_2.11" % "5.6.3",

  //Dumb Dependency for Elastic Search
  "commons-httpclient" % "commons-httpclient" % "3.1",

  //Kafka
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion
)

