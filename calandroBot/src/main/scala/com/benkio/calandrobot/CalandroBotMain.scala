package com.benkio.calandrobot

import org.http4s.client.Client
import cats.effect._

object CalandroBotMain extends IOApp {
  def runWebook(url: String, port: Int, httpClient: Resource[IO, Client[IO]]): cats.effect.IO[cats.effect.ExitCode] =
    CalandroBot
      .buildBot(url, port, httpClient, (cb: CalandroBot[IO]) => cb.start().use { _ => IO.never })

  def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] = IO(ExitCode.Success)
}
