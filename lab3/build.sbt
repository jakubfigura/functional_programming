ThisBuild / scalaVersion := "3.7.2"


lazy val solutions3 = (project in file("solutions3"))
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask"  % "0.9.7",
      "com.lihaoyi" %% "ujson" % "4.0.2"
    ),
    fork := true
)