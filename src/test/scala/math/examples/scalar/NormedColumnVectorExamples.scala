package math.examples.scalar

import math.specification.scalar.{NormedColumnVector, NormedColumnVectorSpace}

import math.types.scalar.{Real, Complex}

import math.implementation.scalar.{realScalar}

import math.implementation.scalar.{complexScalar}

import complexScalar.{r, i}

given realNormedColumnVectorSpace: NormedColumnVectorSpace[Real] with

  lazy val dim = 2

given complexNormedColumnVectorSpace: NormedColumnVectorSpace[Complex] with

  lazy val dim = 2

val normedRealColumnVector =
  realNormedColumnVectorSpace.normedColumnVector(1.0 / 4.0, 3.0 / 4.0)

val normedComplexColumnVector =
  complexNormedColumnVectorSpace.normedColumnVector(
    (r(1.0) + i(2.0)) / r(3.0),
    r(-2.0) / r(3.0)
  )
