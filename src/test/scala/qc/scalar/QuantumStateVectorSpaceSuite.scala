package qc.scalar

class QuantumStateVectorSpaceSuite extends munit.FunSuite:

  import quantumStateVectorSpace.{asLinearBasisVectorCombination}

  val linearBasisVectorCombination = asLinearBasisVectorCombination(quantumStateVector)

  test("norm == 1.0"):

    import quantumStateVector.{norm}

    assert(norm == 1.0, true)

  test("quantumStateVector == linearBasisVectorCombination"):

    assert(quantumStateVector == linearBasisVectorCombination, true)
