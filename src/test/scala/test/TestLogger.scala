package test

import com.memsql.spark.etl.utils.PhaseLogger
import org.apache.log4j.Logger

class TestLogger(override val name: String) extends PhaseLogger {
  override protected val logger: Logger = Logger.getRootLogger
}

