package test

import com.memsql.spark.etl.api.UserTransformConfig
import com.memsql.spark.etl.utils.ByteUtils
import com.memsql.streamliner.starter.BasicTransformer
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{StructField, IntegerType, StructType}
import spray.json.JsString

class TransformersSpec extends UnitSpec with LocalSparkContext {
  val emptyConfig = UserTransformConfig(class_name = "Test", value = new JsString("empty"))
  val logger = new TestLogger("test")

  var sqlContext: SQLContext = _

  override def beforeEach(): Unit = {
    super.beforeEach()
    sqlContext = new SQLContext(sc)
  }

  "BasicTransformer" should "work" in {
    val transform = new BasicTransformer
    val rdd = sc.parallelize(List(1,2,3).map(ByteUtils.intToBytes))

    val df = transform.transform(sqlContext, rdd, emptyConfig, logger)
    assert(df.schema == StructType(Array(StructField("number", IntegerType, true))))
    assert(df.first == Row(1))
  }
}
