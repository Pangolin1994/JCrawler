package crawler

import java.util.concurrent._
import scala.concurrent._
import cats.effect.{IO, IOApp, ExitCode}
import org.http4s.blaze.server._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO]
      .withExecutionContext(ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2)))
      .bindHttp(8080, "localhost")
      .withHttpApp(Routes.httpApp)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}