package crawler

import cats.data.Kleisli
import cats.effect._
import org.http4s._
import org.http4s.dsl._
import org.http4s.implicits._
import org.http4s.server._

import Helper._
import models._
import service.UrlsService


object Routes extends Http4sDsl[IO] {
  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case request @ POST -> Root / "titles" =>
      request.decode[Urls] { urls =>
        (for {
          titles <- UrlsService.urlsServiceImplementation.getTitles(urls)
          // Just for debug purposes
          _ <- IO.pure { println(titles.mkString("\n")) }
        } yield titles).flatMap(Ok(_))
      }
  }

  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router("/" -> routes).orNotFound
}