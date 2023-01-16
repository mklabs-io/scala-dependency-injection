package io.mklabs.di

object CakePattern extends App {

  case class User(id: Long, name: String)

  trait UserRepositoryComponent {

    val userRepo: UserRepository

    trait UserRepository {
      def findOne(): User
    }

  }

  trait CassandraUserRepositoryComponent extends UserRepositoryComponent {
    val userRepo = new CassandraUserRepository

    class CassandraUserRepository extends UserRepository {
      def findOne(): User = User(1L, "john")
    }
  }

  trait UserService {
    def findUser: User
  }

  trait UserServiceComponent {
    this: UserRepositoryComponent =>

    val userService: UserService = new MyUserService

    class MyUserService extends UserService {
      def findUser: User = userRepo.findOne()
    }

  }


  // Now we can glue it all together:

  val userServiceComponent = new UserServiceComponent with CassandraUserRepositoryComponent
  println(userServiceComponent.userService.findUser)


}
