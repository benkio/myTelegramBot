package com.benkio.richardphjbensonbot

import cats.effect._
import scala.concurrent.ExecutionContext.Implicits.global

object RichardPHJBensonBotMain extends IOApp {
  def runWebook(url: String, port: Int): cats.effect.IO[cats.effect.ExitCode] =
    RichardPHJBensonBot
      .buildBot(global, url, port, (rb: RichardPHJBensonBot[IO]) => rb.start().use { _ => IO.never })

  def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] = IO.pure(ExitCode.Success)
}
