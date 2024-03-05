package math.test.scalar

import math.examples.scalar.{realNormedColumnVectorSpace}

class RealNormedColumnVectorSpaceSuite extends munit.FunSuite:

  import math.examples.scalar.{normedRealColumnVector}

  test("normedRealColumnVector.norm == 1.0"):
    assert(normedRealColumnVector.norm == 1.0, true)

  import realNormedColumnVectorSpace.{asLinearBasisVectorCombination}

  test(
    "normedRealColumnVector == asLinearBasisVectorCombination(normedRealColumnVector), true"
  ):
    assert(
      normedRealColumnVector == asLinearBasisVectorCombination(
        normedRealColumnVector
      ),
      true
    )
