package com.memsql.streamliner.starter

import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
import org.apache.spark.sql.{Row, DataFrame, SQLContext}
import org.apache.spark.rdd.RDD
import com.memsql.spark.etl.api.{ UserTransformConfig, SimpleByteArrayTransformer }
import com.memsql.spark.etl.utils.PhaseLogger

// This transformer expects an input RDD containing ints, and returns a
// dataframe with one row per int
class BasicTransformer extends SimpleByteArrayTransformer {
  override def transform(sqlContext: SQLContext, rdd: RDD[Array[Byte]], config: UserTransformConfig, logger: PhaseLogger): DataFrame = {
    logger.info("transforming the RDD")

    // transform the RDD into RDD[Row]
    val integerRDD = rdd.map(byteUtils.bytesToInt).map(Row(_))

    // create a schema with a single non-nullable integer column named number
    val schema = StructType(Array(StructField("number", IntegerType, true)))

    sqlContext.createDataFrame(integerRDD, schema)
  }
}
