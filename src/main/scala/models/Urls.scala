package models

import cats.effect.IO
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.syntax._
import org.http4s.{EntityDecoder, EntityEncoder, Uri}
import org.http4s.circe.{jsonEncoderOf, jsonOf}


class Urls(val uris: Seq[Uri]) {
  override def toString: String = s"Urls(${uris.mkString(", ")})"
}

object Urls {
  // Just for explicit definition. Can be redefined as case class
  def apply(uris: Seq[Uri]): Urls = new Urls(uris)

  def unapply(urls: Urls): Some[Seq[Uri]] = Some(urls.uris)

  implicit val uriEncoder: Encoder[Uri] = new Encoder[Uri] {
    override def apply(uri: Uri): Json = Json.obj(
      ("Uri", Json.fromString(uri.toString()))
    )
  }

  implicit val uriDecoder: Decoder[Uri] = new Decoder[Uri] {
    override def apply(c: HCursor): Decoder.Result[Uri] = {
      for {
        uri <- c.downField("Uri").as[String]
      } yield Uri.unsafeFromString(uri)
    }
  }

  implicit val encoder: Encoder[Urls] = new Encoder[Urls] {
    override final def apply(urls: Urls): Json = Json.obj(
      ("Urls", urls.uris.asJson)
    )
  }

  implicit val decoder: Decoder[Urls] = new Decoder[Urls] {
    override final def apply(cursor: HCursor): Decoder.Result[Urls] = {
      for {
        uris <- cursor.downField("Urls").as[Seq[Uri]]
      } yield Urls(uris)
    }
  }

  implicit val urlsEncoder: EntityDecoder[IO, Urls] = jsonOf[IO, Urls]
  implicit val urlsDecoder: EntityEncoder[IO, Urls] = jsonEncoderOf[IO, Urls]
}