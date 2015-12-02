package com.memsql.streamliner.starter

import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.sql.types._
import org.apache.spark.streaming.StreamingContext
import com.memsql.spark.etl.api.{Extractor, PhaseConfig}
import com.memsql.spark.etl.utils.PhaseLogger

// This extract just returns a static range of 5 integers each batch interval
class BasicExtractor extends Extractor {
  override def next(ssc: StreamingContext, time: Long, sqlContext: SQLContext, config: PhaseConfig, batchInterval: Long,
   logger: PhaseLogger): Option[DataFrame] = {
    logger.info("extracting a constant sequence DataFrame")

    val schema = StructType(StructField("number", IntegerType, false) :: Nil)

    val sampleData = List(1,2,3,4,5)
    val rowRDD = sqlContext.sparkContext.parallelize(sampleData).map(Row(_))

    val df = sqlContext.createDataFrame(rowRDD, schema)
    Some(df)
  }
}
