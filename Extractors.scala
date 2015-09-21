package com.memsql.streamliner.starter

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import com.memsql.spark.etl.api.{ UserExtractConfig, SimpleByteArrayExtractor }
import com.memsql.spark.etl.utils.PhaseLogger

// This extract just returns a static range of 5 integers each batch interval
class BasicExtractor extends SimpleByteArrayExtractor {
  override def nextRDD(sparkContext: SparkContext, config: UserExtractConfig, batchInterval: Long, logger: PhaseLogger): Option[RDD[Array[Byte]]] = {
    val sampleData = List(1,2,3,4,5).map(byteUtils.intToBytes)
    Some(sparkContext.parallelize(sampleData))
  }
}
