package test

import com.memsql.spark.etl.api.UserExtractConfig
import com.memsql.spark.etl.utils.ByteUtils
import spray.json.JsString
import com.memsql.streamliner.starter.BasicExtractor
import org.apache.spark.streaming._
import org.apache.spark.sql.SQLContext

class ExtractorsSpec extends UnitSpec with LocalSparkContext {
  val emptyConfig = UserExtractConfig(class_name = "Test", value = new JsString("empty"))
  val logger = new TestLogger("test")

  var ssc: StreamingContext = _
  var sqlContext: SQLContext = _

  override def beforeEach(): Unit = {
    super.beforeEach()
    ssc = new StreamingContext(sc, Seconds(1))
    sqlContext = new SQLContext(sc)
  }

  "BasicExtractor" should "emit a constant DataFrame" in {
    val extract = new BasicExtractor
    
    val maybeDf = extract.next(ssc, 1, sqlContext, emptyConfig, 1, logger)
    assert(maybeDf.isDefined)

    val total = maybeDf.get.select("number").rdd.map(r => r(0).asInstanceOf[Int]).sum()
    assert(total == 15)
  }

}
