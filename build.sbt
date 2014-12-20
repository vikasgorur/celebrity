scalaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.1" % "test"