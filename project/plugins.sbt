logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.9")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.0.1")
