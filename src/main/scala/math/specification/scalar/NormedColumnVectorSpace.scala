package math.specification.scalar

import scala.collection.immutable.{Seq}

trait NormedColumnVectorSpace[S: Scalar]:

  val summonedScalar = summon[Scalar[S]]

  import summonedScalar.{isValidScalar, scalarNorm}

  lazy val dim: Int

  def normedColumnVector(ss: S*): NormedColumnVector[S] =
    new:
      lazy val seq: Seq[S] = ss

      require(norm == 1.0)

  def normedRowVector(ss: S*): NormedRowVector[S] =
    new:
      lazy val seq: Seq[S] = ss

      require(norm == 1.0)

  lazy val indices: Seq[Int] = 0 to dim - 1

  extension (li: Int)
    def δ(ri: Int): S =
      import summonedScalar.{`0` => `0s`, `1` => `1s`}
      if (li == ri) `1s` else `0s`

  val basisColumnVector: Int => NormedColumnVector[S] = li =>
    normedColumnVector((indices map { ri => li δ ri }): _*)

  val basisRowVector: Int => NormedRowVector[S] = ri =>
    normedRowVector((indices map { li => li δ ri }): _*)

  val basisColumnVectors = indices map { i => basisColumnVector(i) }

  val isBasisColumnVector: NormedColumnVector[S] => Boolean = ncv =>
    basisColumnVectors exists (bcv => ncv.seq == bcv.seq)

  val linearCombination: Seq[NormedColumnVector[S]] => Seq[S] => NormedColumnVector[S] =
    ncvs =>
      ss =>
        import summonedScalar.{`*` => `*ss`, + => `+ss`, `0` => `0s`, `1`}

        extension (ncv: NormedColumnVector[S])
          def *(ls: S): NormedColumnVector[S] =
            new:
              lazy val seq: Seq[S] = ncv.seq map { rs => ls `*ss` rs }

        extension (lncv: NormedColumnVector[S])
          def +(rncv: NormedColumnVector[S]): NormedColumnVector[S] =
            new:
              lazy val seq: Seq[S] = (lncv.seq zip rncv.seq) map (_ `+ss` _)

        val `0`: NormedColumnVector[S] =
          new:
            lazy val seq: Seq[S] = indices map { _ => `0s` }

        val sum: Seq[NormedColumnVector[S]] => NormedColumnVector[S] = ncvs =>
          ncvs.foldRight(`0`)(_ + _)

        val products: Seq[NormedColumnVector[S]] = (ncvs zip ss) map (_ * _)

        val columnVector: NormedColumnVector[S] = sum(products)

        require(columnVector.norm == 1.0)

        columnVector

  extension (nrv: NormedRowVector[S])
    def o(ncv: NormedColumnVector[S]) =
      import summonedScalar.{`0` => `0s`, `*` => `*ss`, + => `+ss`, conjugate}
      (nrv.seq zip ncv.seq)
        .foldRight(`0s`) { case ((ls, rs), s) =>
          (conjugate(ls) `*ss` rs) `+ss` s
        }

  val asLinearBasisVectorCombination: NormedColumnVector[S] => NormedColumnVector[S] =
    ncv =>
      val coefficients = indices map { i => basisRowVector(i) o ncv }
      linearCombination(basisColumnVectors)(coefficients)
