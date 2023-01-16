package io.mklabs.di

import io.mklabs.di.Background.DI2.{BlueService, MyRepository}
import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class BackgroundTest extends AnyFlatSpec with Matchers with MockFactory {

  it should "test something BlueService when repository behaves a certain way" in {
    val mockRepository = mock[MyRepository]
    // service under test
    val sut = new BlueService(mockRepository)
    // mock
    (mockRepository.findOne _)
      .expects()
      .returning("two")
      .once()
    sut.get() shouldBe "two"

  }

}
