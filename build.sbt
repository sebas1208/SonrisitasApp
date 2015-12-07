name := """sonrisitas-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"
)

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.3"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"



// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true