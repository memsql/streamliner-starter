package com.memsql.streamliner.starter

import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream._
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.log4j._
import com.memsql.spark.context._
import com.memsql.spark.connector._
import com.memsql.spark.etl.api._
import com.memsql.spark.etl.api.configs._
import com.memsql.spark.etl.utils._

// A Transformer implements the transform method which turns an RDD into a DataFrame
class EvenNumbersOnlyExtractor extends SimpleByteArrayTransformer {
  override def transform(sqlContext: SQLContext, rdd: RDD[Array[Byte]], config: UserTransformConfig, logger: Logger): DataFrame = {
    logger.log(Level.INFO, "transforming the RDD")

    // transform the RDD into RDD[Row]
    val integerRDD = rdd.map(byteUtils.bytesToInt)
    val filteredRDD = integerRDD.filter(x => x % 2 == 0)
    val transformedRDD = filteredRDD.map(x => Row(x))

    // create a schema with a single non-nullable integer column named number
    val schema = StructType(Array(StructField("number", IntType, true))

    sqlContext.createDataFrame(transformedRDD, schema)
  }
}
