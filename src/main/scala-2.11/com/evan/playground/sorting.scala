package com.evan.playground

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, IndexedSeqView}
import util.Random
object sorting
{
	def main(args: Array[String]): Unit =
	{
		println("Hello World!")
		val arr = Array.fill(20)((50 + Random.nextInt(50)).toChar)
		println(arr.mkString(", "))
		println(mergeSort(arr.toList).mkString(", "))

		println(isSorted(bubbleSort(arr).toList))
	}

	def bubbleSort[T](in: Array[T])(implicit ord:Ordering[T]): Array[T] =
	{
		val arr = in
		for (i <- arr.indices)
		{
			var swapped = false
			for (j <- i until arr.length)
			{
				if (ord.gt(arr(i), arr(j)))
				{
					val tmp = arr(i)
					arr(i) = arr(j)
					arr(j) = tmp
					swapped = true
				}
			}
			/* If there is no swap, return */
			if (!swapped)
			{
				return arr
			}
		}
		arr
	}

	def mergeSort[T](in: List[T])(implicit ord:Ordering[T]): List[T] =
	{
		if (isSorted(in))
		{
			in
		}
		else
		{
			val n = in.length / 2
			val (lhs, rhs) = in.splitAt(n)
			merge(mergeSort(lhs), mergeSort(rhs))
		}
	}
	
	def merge[T](lhs: List[T], rhs: List[T])(implicit ord:Ordering[T]): List[T] =
	{
		(lhs, rhs) match
		{
			case (Nil, rhs) => rhs
			case (lhs, Nil) => lhs
			case (x :: lhs1, y :: rhs1) =>
				if (ord.lt(x,y)) x::merge(lhs1, rhs) else y::merge(lhs, rhs1)
		}
	}

	def isSorted[T](a: List[T])(implicit ord:Ordering[T]): Boolean = a.foldLeft((a.head, true))((acc, i) => (i, ord.gteq(i, acc._1) && acc._2))._2

}
