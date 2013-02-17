import sbt._
import Keys._

object Build extends Build {

  val buildVersion = "1.4-SNAPSHOT"

  val delvingReleases = "Delving Releases Repository" at "http://development.delving.org:8081/nexus/content/repositories/releases"
  val delvingSnapshots = "Delving Snapshot Repository" at "http://development.delving.org:8081/nexus/content/repositories/snapshots"
  val delvingRepository = if(buildVersion.endsWith("SNAPSHOT")) delvingSnapshots else delvingReleases

  val dependencies = Seq(
    "play"                 %%    "play"                        % "2.1.0" % "provided",
    "eu.delving"           %%    "groovy-templates-plugin"     % "1.6-SNAPSHOT",
    "com.novus"            %%    "salat"                       % "1.9.2-SNAPSHOT",
    "org.mongodb"          %%    "casbah"                      % "2.5.0",
    "net.liftweb"          %%    "lift-json"                   % "2.5-M4",
    "org.joda"             %     "joda-convert"                % "1.2",
    "commons-collections"  %     "commons-collections"         % "3.2.1",
    "commons-httpclient"   %     "commons-httpclient"          % "3.1",
    "commons-io"           %     "commons-io"                  % "2.1",
    "org.apache.commons"   %     "commons-email"               % "1.2",
    "commons-lang"         %     "commons-lang"                % "2.6"  )

  val main = Project(
    id = "play2-extensions",
    base = file(".")).settings(
      organization := "eu.delving",

      version := buildVersion,

      scalaVersion := "2.10.0",

      resolvers += "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      resolvers += Resolver.file("local-ivy-repo", file(Path.userHome + "/.ivy2/local"))(Resolver.ivyStylePatterns),
      resolvers += delvingReleases,
      resolvers += delvingSnapshots,

      libraryDependencies ++= dependencies,

      publishTo := Some(delvingRepository),

      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),

      publishMavenStyle := true
    )

}
