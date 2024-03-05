package math.specification.scalar

import scala.collection.immutable.{Seq}

import math.types.scalar.{Real}

trait NormedVector[S: Scalar]:

  val summonedScalar = summon[Scalar[S]]

  import summonedScalar.{isValidScalar, scalarNorm}

  lazy val seq: Seq[S]

  lazy val norm: Real =
    val allScalarsAreValid = seq forall isValidScalar
    val sumOfAllScalarNorms = seq.foldRight(0.0) { (s, r) => scalarNorm(s) + r }
    require(allScalarsAreValid && sumOfAllScalarNorms == 1.0)
    sumOfAllScalarNorms

  // sub-optimal implementation
  override def equals(that: Any): Boolean =
    this.seq == that.asInstanceOf[NormedVector[S]].seq
