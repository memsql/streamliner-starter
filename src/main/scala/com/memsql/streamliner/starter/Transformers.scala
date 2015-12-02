package com.memsql.streamliner.starter

import org.apache.spark.sql.{Row, DataFrame, SQLContext}
import org.apache.spark.sql.types._
import com.memsql.spark.etl.api.{Transformer, PhaseConfig}
import com.memsql.spark.etl.utils.PhaseLogger

// A helper object to extract the first column of a schema
object ExtractFirstStructField {
  def unapply(schema: StructType): Option[(String, DataType, Boolean, Metadata)] = schema.fields match {
    case Array(first: StructField, _*) => Some((first.name, first.dataType, first.nullable, first.metadata))
  }
}

// This transformer expects an input DataFrame and returns it
class BasicTransformer extends Transformer {
  def transform(sqlContext: SQLContext, df: DataFrame, config: PhaseConfig, logger: PhaseLogger): DataFrame = {
    logger.info("transforming the DataFrame")

    // check that the first column is of type IntegerType and return its name
    val column = df.schema match {
      case ExtractFirstStructField(name: String, dataType: IntegerType, _, _) => name
      case _ => throw new IllegalArgumentException("The first column of the input DataFrame should be IntegerType")
    }

    // filter the dataframe, returning only even numbers
    df.filter(s"$column % 2 = 0")
  }
}
