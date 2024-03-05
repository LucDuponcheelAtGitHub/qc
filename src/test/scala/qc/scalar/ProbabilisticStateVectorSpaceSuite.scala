package qc.scalar

class ProbabilisticStateVectorSpaceSuite extends munit.FunSuite:

  import probabilisticStateVectorSpace.{asLinearBasisVectorCombination}

  val linearBasisVectorCombination = asLinearBasisVectorCombination(
    probabilisticStateVector
  )

  test("norm == 1.0"):

    import probabilisticStateVector.{norm}

    assert(norm == 1.0, true)

  test("probabilisticStateVector == linearBasisVectorCombination"):

    assert(probabilisticStateVector == linearBasisVectorCombination, true)
