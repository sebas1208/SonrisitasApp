name := """sonrisitas-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked")

scalaVersion := "2.11.7"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
	javaJdbc,
	"org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
	cache,
	javaWs,
	"com.typesafe.play" %% "play-json" % "2.4.3",
	"com.fasterxml.jackson.core" % "jackson-annotations" % "2.6.0",
	"org.apache.commons" % "commons-lang3" % "3.4",
	"javax.mail" % "javax.mail-api" % "1.4.7",
	"javax.activation" % "activation" % "1.1.1",
	"com.sun.mail" % "javax.mail" % "1.5.4"

)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true