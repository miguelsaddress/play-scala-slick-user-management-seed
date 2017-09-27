package controllers

import javax.inject._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._
import org.webjars.play.WebJarsUtil

import scala.concurrent.{ExecutionContext}

import business.UserManagement
import auth._

import play.Logger

class DashboardController @Inject()(
  users: UserManagement,
  parse: PlayBodyParsers,
  userRequestTransformer: UserRequestTransformer,
  authenticatedAction: AuthenticatedAction,
  cc: ControllerComponents
)(
  implicit
  webJarsUtil: WebJarsUtil,
  assets: AssetsFinder,
  ec: ExecutionContext
) extends AbstractController(cc) with I18nSupport {

  def index = (authenticatedAction andThen userRequestTransformer)(parse.default).async { implicit request => 

    Logger.debug(s"request path ${request.path}")
    Logger.debug(s"User ${request.user}")
    //it will be an endpoint for logged in users only
    users.fullList.map { users => 
        Ok(views.html.dashboard.index(users))
    }
  }
}