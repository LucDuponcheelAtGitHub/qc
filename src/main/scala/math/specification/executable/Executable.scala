package math.specification.executable

trait Executable[E[_]]:

  def value[Z]: Z => E[Z]

  extension [Z, Y](ez: E[Z]) def >=(zτey: Z => E[Y]): E[Y]

  type Environment

  extension [Z](ez: E[Z]) def execute(e: Environment): Z
