package auth

import javax.inject._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import business.UserManagement
import models.User

class ApplicationAuthorizationHandler @Inject()(users: UserManagement)(implicit ec: ExecutionContext) extends AuthorizationHandler {
  override def unauthorized[A](request: RequestWithUser[A]): Future[Result] = {
    Future { NotFound }
  }

  override def userFromRequest[A](request: Request[A]): Future[Option[User]] = {
    users.findByUsername(request.session.get("username"))
  }
}
