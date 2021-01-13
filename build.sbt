name := """tokuo-sample"""
organization := "tokuo.sand"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "org.mockito" % "mockito-scala_2.13" % "1.16.15" % "test",
  filters,
  ws,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.h2database" % "h2" % "1.4.199",
  "com.google.code.gson" % "gson" % "1.7.1"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "tokuo.sand.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "tokuo.sand.binders._"
