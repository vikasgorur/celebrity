import org.scalacheck.Properties
import org.scalacheck.{Gen, Arbitrary, Prop}

object CelebritySpec extends Properties("Celebrity") {
  def matrix(size: Int): Gen[Array[Array[Boolean]]] = {
    Gen.containerOfN[Array, Array[Boolean]](size,
      Gen.containerOfN[Array, Boolean](size, Arbitrary.arbitrary[Boolean]))
  }

  def follows(matrix: Array[Array[Boolean]])(i: Int, j: Int) = matrix(i)(j)

  def makeCelebrity(matrix: Array[Array[Boolean]], c: Int): Array[Array[Boolean]] = {
    val size = matrix.size
    0.until(size).foreach { i =>
      matrix(i)(c) = true
      matrix(c)(i) = false
    }
    matrix
  }

  def testCase: Gen[(Int, Int, (Int, Int) => Boolean)] = {
    for {
      n <- Gen.choose(1, 100)
      c <- Gen.choose(0, n-1)      
      m <- matrix(n)
    } yield (n, c, follows(makeCelebrity(m, c)))
  }

  property("finds celebrity") = Prop.forAll(testCase) { example =>
    example match {
      case (n, c, f) => Celebrity.find(n, f) == c
    }
  }
}