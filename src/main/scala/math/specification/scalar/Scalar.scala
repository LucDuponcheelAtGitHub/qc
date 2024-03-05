package math.specification.scalar

import math.types.scalar.{Real}

trait Scalar[S]:

  val `0`: S

  extension (ls: S) def +(rs: S): S

  extension (ls: S) def -(rs: S): S

  val `1`: S

  extension (ls: S) def *(rs: S): S

  extension (ls: S) def /(rs: S): S

  val conjugate: S => S

  val isValidScalar: S => Boolean

  val scalarNorm: S => Real

  val asString: S => String
