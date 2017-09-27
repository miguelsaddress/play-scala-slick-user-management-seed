package auth

import play.api.mvc._
import scala.concurrent.Future
import models.User

trait AuthorizationHandler {
  def unauthorized[A](request: RequestWithUser[A]): Future[Result]
  def userFromRequest[A](request: Request[A]): Future[Option[User]]
}
