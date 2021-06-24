package com.benkio.abarberobot

import cats.effect._
import scala.concurrent.ExecutionContext.Implicits.global

object ABarberoBotMain extends IOApp {
  def runWebook(url: String, port: Int): IO[Unit] =
    ABarberoBot
      .buildBot[IO, Unit](global, url, port, (ab: ABarberoBot[IO]) => ab.start().use { _ => IO.never })

    def run(args: List[String]): IO[ExitCode] = IO(ExitCode.Success)
}
