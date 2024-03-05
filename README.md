# Basics of quantum information

## Introduction

This document illustrates the content of the 
[IBM's basics of quantum information course](https://learning.quantum.ibm.com/course/basics-of-quantum-information/)
with `Scala` code.

## Single systems

This section deals with *probabilistic information* and *quantum information*.

The main difference between probabilistic information and quantum information is that the former involves
*non-negative real numbers* and the latter involves *complex numbers*.

### `Real`

[Real.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/types/scalar/Real.scala)

 `Real` is a `type` alias for `Double`.

### `Complex`

[Complex.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/types/scalar/Complex.scala)

 `Complex` is a `case class`.

 - `re` is the *real* part of a complex number,
 - `im` is the *imaginary* part of a complex number.

 ### `Scalar`

[Scalar.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/specification/scalar/Scalar.scala)

`Scalar` is a `trait` that *specifies* a *type class* for *type parameter* `S` as `Scalar[S]`.

A type class specification *declares*, and *default defines* common *members* of its type parameter.

### `realScalar`

[realScalar.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/implementation/scalar/realScalar.scala)

`realScalar` is a `given` that *implements* type class `Scalar` by *substituting* *type argument* `Real` for type
parameter `S` as `Scalar[Real]`.

A type class implementation *defines* the declared members of its type class specification.

`isValidScalar` is `true` if and only if a real number is *positive* or *zero*.

`scalarNorm` does not use the absolute value function because the real number is positive or zero.

### `complexScalar`

[complexScalar.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/implementation/scalar/complexScalar.scala)

`complexScalar` defines two extra members, `r` resp. `i` to construct *real* resp *imaginary* complex numbers.

### `NormedVector`

[NormedVector.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/specification/scalar/NormedVector.scala)

`NormedVector` is a `trait` that specifies a *generic value class* with *parameter* `S`, required to be a `Scalar`, as
`NormedVector[S: Scalar]`.

A value class *declares*, and *default defines* common *members* for *values* that are *instances* of it.

When constructing such an instance, all declared members need to be defined.

Member `seq` the *sequence of scalars* of a *normed vector*.

`norm` is the *Manhattan, L1-norm* for vectors of *non-negative* real numbers resp. the *square* of the
*Euclidean, L2-norm* for vectors of complex numbers. Not using the square root function is justified by the fact that
the *norm* of a normed vector is required to be equal to `1.0`.

### `NormedColumnVector` and `NormedRowVector`

[NormedColumnVector.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/specification/scalar/NormedColumnVector.scala)

and

[NormedRowVector.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/specification/scalar/NormedRowVector.scala)

`NormedColumnVector` and `NormedRowVector` differ in the way they implement `toString`.

### `NormedColumnVectorSpace`

[NormedColumnVectorSpace.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/math/specification/scalar/NormedColumnVectorSpace.scala)

`NormedColumnVectorSpace` is a `trait` that specifies a generic value class with parameter `S`, required to be a
`Scalar`, as `NormedColumnVectorSpace[S: Scalar]`.

`dim`, the *dimension* of the *normed column vector space* is the only declared member, all other ones are defined.

`normedColumnVector` constructs *normed column vectors*. 

They are required to have *norm* equal to `1.0`.

`normedRowVector` constructs *normed row vectors*.

They are required to have *norm* equal to `1.0`.

`indices` and `δ` are auxiliary members that are used to define *basis (normed) vectors*.

`basisColumnVector` constructs *basis (normed) column vectors*.

`basisRowVector` constructs *basis (normed) row vectors*.

`basisColumnVectors` consists of all (normed) basis column vectors

`isBasisColumnVector` checks if a normed column vector is a basis (normed) column vector.

`linearCombination` constructs *linear combinations* of normed column vectors.

They are required to have *norm* equal to `1.0`.

`o` defines the *inner product* of a normed row vector and a normed column vector.

`asLinearBasisVectorCombination` defines a normed column vector `ncv` as a linear combination of basis column vectors
with *coefficients* `basisRowVector(i) o ncv` for all *indices* `i` in `0 to dim - 1`.

### `ProbabilisticStateVector`

[ProbabilisticStateVector.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/qc/types/scalar/ProbabilisticStateVector.scala)

`ProbabilisticStateVector` is a `type` alias for `NormedColumnVector[Real]`.

Note: in
[IBM's basics of quantum information course](https://learning.quantum.ibm.com/course/basics-of-quantum-information/)
*probabilistic state vectors* are called *probability vectors*.

### `QuantumStateVector`

[QuantumStateVector.scala](https://github.com/LucDuponcheelAtGitHub/qc/blob/main/src/main/scala/math/types/scalar/Real.scalamain/scala/qc/types/scalar/QuantumStateVector.scala.

`QuantumStateVector` is a `type` alias for `NormedColumnVector[Complex]`.


<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

In what follows, probabilistic information resp. quantum information will be *represented* as a value of type
`NormedVector[Real]` resp. `NormedVector[Complex]`, more precisely, a value of type `NormedColumnVector[Real]` resp.
`NormedColumnVector[Complex]`.

For example, a value `normedColumnVector(1.0/4.0, 3.0/4.0)` will represent probabilistic information which can be in one
of two *states*: in the first state with probability `1.0/4.0` and in the second state with probability `3.0/4.0`.

For example, a value `normedColumnVector((r(1.0) + i(2.0)) / r(3.0), r(-2.0) / r(3.0))` will represent quantum
information which will can be one of two *states*: in the first state with probability `5.0/9.0` and in the second state
with probability `4.0/9.0`.
