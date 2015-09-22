MemSQL Streamliner Starter
==========================

This is a starter repository that you can use to build pipelines for [MemSQL Spark Streamliner](http://docs.memsql.com/latest/spark/).

MemSQL Spark Streamliner lets you build custom Spark pipelines to:
   1. extract from real-time data sources such as Kafka,
   1. transform data structures such as CSV, JSON, or Thrift in table rows,
   1. load your data into MemSQL.

Check out the [MemSQL Spark Streamliner Examples](https://github.com/memsql/streamliner-examples) repository for example code of Extractors and Transformers.


Get Started with MemSQL Spark Streamliner
-----------------------------------------

1. Clone this repository

1. Modify `Extractors.scala` and `Transformers.scala`

1. Build the JAR with:

	```bash
	make build
	```

	or:

	```bash
	sbt clean
	sbt assembly
	```

1. The JAR will be localed within `target/scala-<version>/`. Upload the JAR into MemSQL Ops and start your custom pipeline.

Read more on how to [create custom Spark Interface JARs](http://docs.memsql.com/latest/spark/memsql-spark-interface/) in our docs.
