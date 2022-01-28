package service

import org.jsoup._
import cats.effect.IO

import models._


trait UrlsService {
  def getTitles(urls: Urls): IO[Map[String, Title]]
}

object UrlsService {
  val urlsServiceImplementation: UrlsService = new UrlsService {
    override def getTitles(urls: Urls): IO[Map[String, Title]] = {
      IO.pure {
        urls.uris.map { uri =>
          val title = Jsoup.connect(uri.toString()).get()
            .getElementsByTag("title")
            .first()
            .text()
          (uri.toString(), title)
        }.map(x => (x._1, Title(x._2))).toMap
      }
    }
  }
}