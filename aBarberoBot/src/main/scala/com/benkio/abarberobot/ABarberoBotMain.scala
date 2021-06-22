package com.benkio.abarberobot

import cats.effect._
import scala.concurrent.ExecutionContext.Implicits.global

object ABarberoBotMain extends IOApp {
  def run(args: List[String]): IO[cats.effect.ExitCode] =
    ABarberoBot
      .buildBot[IO, Unit](global, "server Url", 80, (ab: ABarberoBot[IO]) => ab.start().use { _ => IO.never })
      .as(ExitCode.Success)
}
