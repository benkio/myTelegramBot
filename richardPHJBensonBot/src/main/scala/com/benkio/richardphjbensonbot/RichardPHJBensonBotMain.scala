package com.benkio.richardphjbensonbot

import org.http4s.client.Client
import cats.effect._

object RichardPHJBensonBotMain extends IOApp {
  def runWebook(url: String, port: Int, httpClient: Resource[IO, Client[IO]]): cats.effect.IO[cats.effect.ExitCode] =
    RichardPHJBensonBot
      .buildBot(url, port, httpClient, (rb: RichardPHJBensonBot[IO]) => rb.start().use { _ => IO.never })

  def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] = IO.pure(ExitCode.Success)
}
