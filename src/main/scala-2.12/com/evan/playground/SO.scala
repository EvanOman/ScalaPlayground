package com.evan.playground

object SO
{
	def main(args: Array[String]): Unit =
	{
		def f(x: (Int, Int, Int)): Int = x._1 + x._2 + x._3
		val out = f(1,2,3)
		println(out)
	}
}