package models

import cats.effect.IO
import io.circe.{Decoder, Encoder, HCursor, Json}
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}


class Title(val name: String) {
  override def toString: String = s"Title($name)"
}

object Title {
  // Just for explicit definition. Can be redefined as case class
  def apply(name: String): Title = new Title(name)

  def unapply(title: Title): Some[String] = Some(title.name)

  implicit val encoder: Encoder[Title] = new Encoder[Title] {
    override final def apply(title: Title): Json = Json.obj(
      ("Title", Json.fromString(title.name)))
  }

  implicit val decoder: Decoder[Title] = new Decoder[Title] {
    override final def apply(cursor: HCursor): Decoder.Result[Title] = {
      for {
        name <- cursor.downField("Title").as[String]
      } yield Title(name)
    }
  }

  implicit val titleEncoder: EntityDecoder[IO, Title] = jsonOf[IO, Title]
  implicit val titleDecoder: EntityEncoder[IO, Title] = jsonEncoderOf[IO, Title]
}