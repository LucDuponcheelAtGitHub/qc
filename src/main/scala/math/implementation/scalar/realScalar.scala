package math.implementation.scalar

import math.specification.scalar.{Scalar}

import math.types.scalar.{Real}

given realScalar: Scalar[Real] with

  val `0`: Real = 0.0

  extension (lr: Real) def +(rr: Real) = lr.+(rr)

  extension (lr: Real) def -(rr: Real) = lr.-(rr)

  val `1`: Real = 1.0

  extension (lr: Real) def *(rr: Real): Real = lr.*(rr)

  extension (lr: Real) def /(rr: Real) = { require(rr != 0.0); lr./(rr) }

  val conjugate: Real => Real = r => r

  val isValidScalar: Real => Boolean = r => r >= 0.0

  val scalarNorm: Real => Real = r => r

  val asString: Real => String =
    r =>
      if (r >= 0.0) { s"+${r}" }
      else { s"${r}" }
