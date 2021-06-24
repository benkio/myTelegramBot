package com.benkio.calandrobot

import cats.effect._
import scala.concurrent.ExecutionContext.Implicits.global

object CalandroBotMain extends IOApp {
  def runWebook(url: String, port: Int): cats.effect.IO[cats.effect.ExitCode] =
    CalandroBot
      .buildBot(global, url, port, (cb: CalandroBot[IO]) => cb.start().use { _ => IO.never })

    def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] = IO(ExitCode.Success)
}
