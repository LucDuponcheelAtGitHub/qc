package math.implementation.scalar

import scala.math.{sqrt}

import math.specification.scalar.{Scalar}

import math.types.scalar.{Real, Complex}

given complexScalar: Scalar[Complex] with

  val r: Real => Complex = r => Complex(r, 0.0)

  val i: Real => Complex = r => Complex(0.0, r)

  val `0`: Complex = r(0)

  extension (lc: Complex)
    def +(rc: Complex): Complex = Complex(lc.re + rc.re, lc.im + rc.im)

  extension (lc: Complex)
    def -(rc: Complex): Complex = Complex(lc.re - rc.re, lc.im - rc.im)

  val `1`: Complex = r(1)

  extension (lc: Complex)
    def *(rc: Complex): Complex =
      Complex(lc.re * rc.re - lc.im * rc.im, lc.im * rc.re + lc.re * rc.im)

  extension (lc: Complex)
    def /(rc: Complex): Complex =
      require(rc.re != 0 || rc.im != 0)
      Complex(
        (lc.re * rc.re + lc.im * rc.im) / (rc.re * rc.re + rc.im * rc.im),
        (lc.im * rc.re - lc.re * rc.im) / (rc.re * rc.re + rc.im * rc.im)
      )

  val conjugate: Complex => Complex = c => r(c.re) - i(c.im)

  val isValidScalar: Complex => Boolean = _ => true

  val scalarNorm: Complex => Real = c => c.re * c.re + c.im * c.im

  val asString: Complex => String =
    case Complex(re, im) =>
      if (im == 0.0) {
        if (re >= 0.0) {
          s"+${re}r"
        } else {
          s"${re}r"
        }
      } else {
        if (re == 0.0) {
          if (im >= 0.0) {
            s"+${im}i"
          } else {
            s"-${-im}i"
          }
        } else {
          if (re >= 0) {
            if (im >= 0.0) {
              s"+${re}r +${im}i"
            } else {
              s"+${re}r -${-im}i"
            }
          } else {
            if (im >= 0.0) {
              s"${re}r +${im}i"
            } else {
              s"${re}r -${-im}i"
            }
          }
        }
      }

