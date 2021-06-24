package com.benkio.main

import cats.effect._
import com.benkio.richardphjbensonbot.RichardPHJBensonBotMain
import com.benkio.calandrobot.CalandroBotMain
import com.benkio.abarberobot.ABarberoBotMain
import com.typesafe.config.ConfigFactory

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = for {
    url <- getUrl()
    port <- getPort()
    _ <- (RichardPHJBensonBotMain.runWebook(url, port) &>
      CalandroBotMain.runWebook(url, port) &>
      ABarberoBotMain.runWebook(url, port))
  } yield ExitCode.Success

  def getPort(): IO[Int] = IO(ConfigFactory.load().getInt("webhook.port"))
  def getUrl(): IO[String] = IO(ConfigFactory.load().getString("webhook.url"))

}
