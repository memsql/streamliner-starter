package com.memsql.streamliner.starter

import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream._
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.log4j.Logger
import com.memsql.spark.context._
import com.memsql.spark.connector._
import com.memsql.spark.etl.api._
import com.memsql.spark.etl.api.configs._
import com.memsql.spark.etl.utils._

class NikitaExtractor extends SimpleExtractor {
  override def nextRDD(sparkContext: SparkContext, config: PhaseConfig, batchInterval: Long, logger: Logger): Option[RDD[Array[Byte]]] = {
    Some(sparkContext.parallelize(List(0).map(byteUtils.intToBytes)))
  }
}

class CarlExtractor extends SimpleExtractor {
  var i: Int = Int.MinValue

  override def initialize(sparkContext: SparkContext, config: PhaseConfig, batchInterval: Long, logger: Logger): Unit = {
    i = 0
  }

  override def nextRDD(sparkContext: SparkContext, config: PhaseConfig, batchInterval: Long, logger: Logger): Option[RDD[Array[Byte]]] = {
    i += 1
    Some(sparkContext.parallelize(List(i).map(byteUtils.intToBytes)))
  }
}

class JoYoExtractor extends ByteArrayExtractor {
  override def extract(ssc: StreamingContext, extractConfig: PhaseConfig, batchInterval: Long, logger: Logger): InputDStream[Array[Byte]] = {
    new InputDStream[Array[Byte]](ssc) {
      override def start(): Unit = {}
      override def stop(): Unit = {}
      override def compute(validTime: Time): Option[RDD[Array[Byte]]] = None
    }
  }
}
