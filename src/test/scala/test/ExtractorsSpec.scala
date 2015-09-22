package test

import com.memsql.spark.etl.api.UserExtractConfig
import com.memsql.spark.etl.utils.ByteUtils
import spray.json.JsString
import com.memsql.streamliner.starter.BasicExtractor

class ExtractorsSpec extends UnitSpec with LocalSparkContext {
  val emptyConfig = UserExtractConfig(class_name = "Test", value = new JsString("empty"))
  val logger = new TestLogger("test")

  "BasicExtractor" should "work" in {
    val extract = new BasicExtractor
    val maybeRDD = extract.nextRDD(sc, emptyConfig, 1, logger)

    assert(maybeRDD.isDefined)

    val total = maybeRDD.get.map(ByteUtils.bytesToInt).sum()
    assert(total == 15)
  }
}
