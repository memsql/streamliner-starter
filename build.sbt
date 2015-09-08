resolvers += "LOCAL" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

name := "memsql-spark-pipeline-starter"
version := "0.0.1"
scalaVersion := "2.10.5"
libraryDependencies  ++= Seq(
    "org.apache.spark" %% "spark-core" % "1.4.1" % "provided",
    "org.apache.spark" %% "spark-sql" % "1.4.1"  % "provided",
    "org.apache.spark" %% "spark-streaming" % "1.4.1" % "provided",
    "com.memsql" %% "memsqletl" % "0.1.8" % "provided"
)
