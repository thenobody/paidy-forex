package forex.interfaces.api

import akka.event.Logging
import akka.http.scaladsl._
import forex.config._
import forex.interfaces.api.utils._
import org.zalando.grafter.macros._

@readerOf[ApplicationConfig]
case class Routes(
    ratesRoutes: rates.Routes
) {
  import server.Directives._

  lazy val route: server.Route =
    logRequestResult("Rates Service", Logging.InfoLevel) {
      handleExceptions(ApiExceptionHandler()) {
        handleRejections(ApiRejectionHandler()) {
          ratesRoutes.route
        }
      }
    }
}
