package qc.scalar

import math.implementation.scalar.{complexScalar}

import qc.types.scalar.{QuantumStateVectorSpace}

import complexScalar.{r, i}

given quantumStateVectorSpace: QuantumStateVectorSpace with

  lazy val dim = 2

import quantumStateVectorSpace.{normedColumnVector}

val quantumStateVector = normedColumnVector((r(1.0) + i(2.0)) / r(3.0), r(-2.0) / r(3.0))
