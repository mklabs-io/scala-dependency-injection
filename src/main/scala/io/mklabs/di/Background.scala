package io.mklabs.di

object Background extends App {

  object Wrong {
    class MyRepository

    class BlueService {
      val a = new MyRepository
    }
  }

  object DI1 {
    class MyRepository
    class BlueService(a: MyRepository)
  }

  object DI2 {
    class MyRepository {
      def findOne(): String = "one"
    }
    class BlueService(a: MyRepository) {
      def get(): String = a.findOne()
    }
  }
  object DI3 {
    trait Repository
    class MyRepository extends Repository

    class BlueService(a: Repository) {}
  }

  object ManualDI {

    trait Repository {
      def findOne(): String
    }

    class MyRepository extends Repository {
      override def findOne(): String = "one"
    }

    class BlueService(repository: Repository) {
      def get: String = repository.findOne()
    }

    class GreenService(repository: Repository)

    // instantiation
    val myRepo = new MyRepository()
    val blueService = new BlueService(myRepo)
    val greenService = new GreenService(myRepo)
    println(blueService.get)
  }

}
