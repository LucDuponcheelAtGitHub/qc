package math.specification.scalar

trait NormedColumnVector[S: Scalar] extends NormedVector[S]:

  override def toString(): String =
    import summonedScalar.{asString}

    (seq map { s => s"${asString(s)}\n" }).foldRight("")(_ + _)
