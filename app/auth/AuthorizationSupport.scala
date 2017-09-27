package auth

import javax.inject._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

import models.User

import play.Logger

class AuthenticatedAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {

    Logger.debug(s"invokeBlock")
    block(request)
  }
}

class UserRequestTransformer @Inject() (parser: BodyParsers.Default, authorizationHandler: AuthorizationHandler)(implicit ec: ExecutionContext) 
extends ActionBuilderImpl(parser) with ActionTransformer[Request, RequestWithUser]
{
  //def transform[A](request: Request[A]) = Future.successful {
  def transform[A](request: Request[A]) = {

    Logger.debug("transform")

    authorizationHandler.userFromRequest(request).map { maybeUser =>
      maybeUser.map { user =>
        RequestWithUser(Some(user), request)
      }.getOrElse {
        RequestWithUser(None, request)
      }
    }
  }
}
