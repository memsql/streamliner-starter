package test

import org.scalatest._

abstract class UnitSpec
  extends FlatSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors
  with BeforeAndAfter
  with BeforeAndAfterEach
  with BeforeAndAfterAll
  with OneInstancePerTest {
  }
