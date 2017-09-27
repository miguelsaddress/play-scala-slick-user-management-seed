package auth

import play.api.mvc.{Request, WrappedRequest}
import models.User

case class RequestWithUser[A](val user: Option[User], request: Request[A]) extends WrappedRequest[A](request)


// class RequestWithUser[A](val user: User, request: Request[A]) extends WrappedRequest[A](request) {}

// object RequestWithUser {
//   def apply[A](user: User, request: Request[A]): RequestWithUser[A] = {
//     new RequestWithUser(user, request)
//   }
// }


// class RequestWithOptionalUser[A](val maybeUser: Option[User], request: Request[A]) extends WrappedRequest[A](request) {}

// object RequestWithOptionalUser {
//   def apply[A](maybeUser: Option[User], request: Request[A]): RequestWithOptionalUser[A] = {
//     new RequestWithOptionalUser(maybeUser, request)
//   }
// }

