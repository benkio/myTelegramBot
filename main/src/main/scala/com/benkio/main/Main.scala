package com.benkio.main

import cats.effect._
import org.http4s.client.blaze._
import com.benkio.richardphjbensonbot.RichardPHJBensonBotMain
import com.benkio.calandrobot.CalandroBotMain
import com.benkio.abarberobot.ABarberoBotMain
import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.ConfigFactory

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = for {
    url  <- getUrl()
    port <- getPort()
    _ <- BlazeClientBuilder[IO](global).resource.use { httpClient =>
      (RichardPHJBensonBotMain.runWebook(url, port, httpClient) &>
        CalandroBotMain.runWebook(url, port, httpClient) &>
        ABarberoBotMain.runWebook(url, port, httpClient))
    }
  } yield ExitCode.Success

  def getPort(): IO[Int]   = IO(ConfigFactory.load().getInt("webhook.port"))
  def getUrl(): IO[String] = IO(ConfigFactory.load().getString("webhook.url"))

}
