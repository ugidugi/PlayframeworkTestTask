name := "TestTask"
 
version := "1.0" 
      
lazy val `testtask` = (project in file(".")).enablePlugins(PlayScala,PlayJava,PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

      