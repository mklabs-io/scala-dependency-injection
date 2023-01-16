package io.mklabs.di

object TraitsCrashCourse extends App {

  object TraitsDiamondProblem {

    trait FileOps {
      def readFile: String
    }

    trait FileReader extends FileOps {
      override def readFile: String = "loaded"
    }

    trait OsFileLocker extends FileOps {
      override def readFile: String = "lock created & file loaded"
    }

//    class FileSystemConsumer extends FileReader with OsFileLocker

    class FileSystemConsumer extends OsFileLocker with FileReader
    println(new FileSystemConsumer().readFile)
  }

  object TraitsNarcissism {

    trait Greeting {
      def intro: String
    }

    trait Greeter {
      // mix-in Greeting trait
      this: Greeting =>
      def greet: String = s"$intro, I am Foo"
    }

    trait PortugueseGreeting extends Greeting {
      override def intro: String = "Olá"
    }

    // DI required
    val greeter = new Greeter with PortugueseGreeting
    println(greeter.greet)

  }


}
