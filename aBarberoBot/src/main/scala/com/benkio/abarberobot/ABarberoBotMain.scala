package com.benkio.abarberobot

import org.http4s.client.Client
import cats.effect._

object ABarberoBotMain extends IOApp {
  def runWebook(url: String, port: Int, httpClient: Resource[IO, Client[IO]]): IO[Unit] =
    ABarberoBot
      .buildBot[IO, Unit](url, port, httpClient, (ab: ABarberoBot[IO]) => ab.start().use { _ => IO.never })

  def run(args: List[String]): IO[ExitCode] = IO(ExitCode.Success)
}
