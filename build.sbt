resolvers += "memsql" at "http://maven.memsql.com"

lazy val root = (project in file(".")).
  settings(
    name := "memsql-spark-pipeline-starter",
    version := "0.0.1",
    scalaVersion := "2.10.5",
    parallelExecution in Test := false,
    libraryDependencies  ++= Seq(
        "org.apache.spark" %% "spark-core" % "1.4.1" % "provided",
        "org.apache.spark" %% "spark-sql" % "1.4.1"  % "provided",
        "org.apache.spark" %% "spark-streaming" % "1.4.1" % "provided",
        "org.scalatest" %% "scalatest" % "2.2.5" % "test",
        "com.memsql" %% "memsqletl" % "0.2.3"
    )
)
