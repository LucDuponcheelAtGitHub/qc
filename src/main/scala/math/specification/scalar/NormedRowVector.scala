package math.specification.scalar

trait NormedRowVector[S: Scalar] extends NormedVector[S]:

  override def toString(): String =
    import summonedScalar.{asString}

    (seq map { s => s"${asString(s)}\t"}).foldRight("")(_ + _)
