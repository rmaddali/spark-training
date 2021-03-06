package com.xyzcorp

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkDiscretizedStreaming extends App {

  //run with nc -lk 10150

  val conf: SparkConf = new SparkConf()
    .setAppName("streaming_1")
    .setMaster("local[*]")

  val streamingContext: StreamingContext =
    new StreamingContext(conf, Seconds(1))

  streamingContext.sparkContext.setLogLevel("INFO")
  streamingContext.sparkContext.setCheckpointDir("checkpoints")

  val lines: ReceiverInputDStream[String] = streamingContext
    .socketTextStream("127.0.0.1", 10150)

  val words: DStream[String] =
    lines.flatMap(_.split(" "))
  val pairs: DStream[(String, Int)] =
    words.map(word => (word, 1))
  val wordCounts: DStream[(String, Int)] =
    pairs.reduceByKey(_ + _)

  def updateFunc(values: Seq[Int], state: Option[Int]): Option[Int] = {
    val currentCount = values.sum
    val previousCount = state.getOrElse(0)
    Some(currentCount + previousCount)
  }

  private val result: DStream[(String, Int)] = wordCounts.updateStateByKey(updateFunc)

  result.print()

  streamingContext.start()
  streamingContext.awaitTermination()
}
