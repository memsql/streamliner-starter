package test

import com.memsql.spark.etl.api.UserTransformConfig
import com.memsql.spark.etl.utils.ByteUtils
import com.memsql.streamliner.starter.BasicTransformer
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types._
import spray.json.JsString

class TransformersSpec extends UnitSpec with LocalSparkContext {
  val emptyConfig = UserTransformConfig(class_name = "Test", value = new JsString("empty"))
  val logger = new TestLogger("test")

  var sqlContext: SQLContext = _

  override def beforeEach(): Unit = {
    super.beforeEach()
    sqlContext = new SQLContext(sc)
  }

  "BasicTransformer" should "only emit even numbers" in {
    val transform = new BasicTransformer

    val schema = StructType(StructField("number", IntegerType, false) :: Nil)
    val sampleData = List(1,2,3)
    val rowRDD = sqlContext.sparkContext.parallelize(sampleData).map(Row(_))
    val dfIn = sqlContext.createDataFrame(rowRDD, schema)

    val df = transform.transform(sqlContext, dfIn, emptyConfig, logger)
    assert(df.schema == schema)
    assert(df.first == Row(2))
    assert(df.count == 1)
  }

  "BasicTransformer" should "only accept IntegerType fields" in {
    val transform = new BasicTransformer

    val schema = StructType(StructField("column", StringType, false) :: Nil)
    val sampleData = List(1,2,3)
    val rowRDD = sqlContext.sparkContext.parallelize(sampleData).map(Row(_))
    val dfIn = sqlContext.createDataFrame(rowRDD, schema)

    val e = intercept[IllegalArgumentException] {
      transform.transform(sqlContext, dfIn, emptyConfig, logger)
    }
    assert(e.getMessage() == "The first column of the input DataFrame should be IntegerType")
  }

}
