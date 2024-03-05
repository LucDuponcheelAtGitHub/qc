package qc.scalar

import math.implementation.scalar.{realScalar}

import qc.types.scalar.{ProbabilisticStateVectorSpace}

given probabilisticStateVectorSpace: ProbabilisticStateVectorSpace with

  lazy val dim = 2

import probabilisticStateVectorSpace.{normedColumnVector}

val probabilisticStateVector = normedColumnVector(1.0 / 4.0, 3.0 / 4.0)

