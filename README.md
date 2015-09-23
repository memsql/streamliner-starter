MemSQL Streamliner Starter
==========================

This is a starter repository that you can use to build pipelines for [MemSQL Spark Streamliner](http://docs.memsql.com/latest/spark/).

MemSQL Spark Streamliner lets you build custom Spark pipelines to:
   1. extract from real-time data sources such as Kafka,
   2. transform data structures such as CSV, JSON, or Thrift in table rows,
   3. load your data into MemSQL.

Check out the [MemSQL Spark Streamliner Examples](https://github.com/memsql/streamliner-examples) repository for more example Extractors and Transformers.


Get Started with MemSQL Spark Streamliner
-----------------------------------------

1. Clone this repository

2. Modify `Extractors.scala` and `Transformers.scala`

3. Build the JAR with:

	```bash
	make build
	```

4. The JAR will be placed in `target/scala-<version>/`. Upload the JAR to MemSQL Ops and create a pipeline using your custom code.

Read more on how to [create custom Spark Interface JARs](http://docs.memsql.com/latest/spark/memsql-spark-interface/) in our docs.


Run Tests
---------

Run:

```bash
make test
```
