import java.lang.Comparable

import scala.math.Numeric

object Rational
{
	def apply(n: BigInt, d: BigInt): Rational =
	{
		val neg_mod = if (d < BigInt(0)) BigInt(-1) else BigInt(1)
		val (n_mod, d_mod) = (neg_mod * n, neg_mod * d)
		val gcd_val = gcd(n_mod, d_mod)
		new Rational(n_mod / gcd_val, d_mod / gcd_val)
	}
	def gcd(a: BigInt, b: BigInt): BigInt = if (b == BigInt(0)) a else gcd(b, a % b)
}
class Rational(val n: BigInt, val d: BigInt)
{
	override def toString: String = if (n == BigInt(0)) "0" else if (d == BigInt(1)) s"$n" else s"$n/$d"

	def toDouble: Double = n.toDouble / d.toDouble

	def *(that: Rational): Rational = Rational(n * that.n, d * that.d)

	def /(that: Rational): Rational = Rational(n * that.d, d * that.n)

	def +(that: Rational): Rational = Rational(n * that.d + that.n * d, d * that.d)

	def -(that: Rational): Rational = this + (-that)

	def unary_- = Rational(-n, d)
}

val a: Int = 1

a

def myPrint(a: Int): Unit = println(a)