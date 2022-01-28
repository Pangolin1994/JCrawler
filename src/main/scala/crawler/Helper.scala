package crawler

import cats.effect.IO
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

import models._


object Helper {
  implicit val mapEncoder: Encoder[Map[String, Title]] = new Encoder[Map[String, Title]] {
    override def apply(a: Map[String, Title]): Json = Json.fromFields(
      a.map {
        case (url, t) => (url, Json.fromString(t.name))
      }
    )
  }

  implicit val jsonMapEncoder: EntityEncoder[IO, Map[String, Title]] = jsonEncoderOf[IO, Map[String, Title]]
}