package com.evan.playground
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
//import breeze.linalg._

object working
{
	def main(args: Array[String]): Unit =
	{
		val lines = List(List("i1", "i2", "i5"),
			List("i3", "i4"),
			List("i2", "i6", "i7"),
			List("i4", "i8"),
			List("i9", "i3"))

		val pairs =  lines.flatMap(x => x.dropRight(1).zip(x.drop(1)))

		val pSym = pairs.flatMap(x => List(x, x.swap))

		val vertices = lines.flatten.distinct

		val defMap = vertices.map(_ -> List[String]()).toMap

		val adjMap = pSym.foldLeft{defMap}(
			(acc, x) => acc + (x._1 -> (acc(x._1) ++ List(x._2)))
		)

		mapBFS(adjMap).foreach{println}
		def mapBFS(adjMap: Map[String, List[String]]): List[List[String]] =
		{
			val v = adjMap.keys
			var globalVisits = List[String]()
			def BFS_r(elems: List[String], visited: List[List[String]]): Option[List[String]] =
			{
				val newNeighbors = elems.flatMap(adjMap(_)).filterNot(visited.flatten.contains).distinct
				if (newNeighbors.isEmpty)
					Some(visited.flatten)
				else
					BFS_r(newNeighbors, newNeighbors :: visited)
			}
			v.flatMap(x =>{
				if (globalVisits.contains(x))
					None
				else
				{
					val vi: List[String] = BFS_r(List(x), List(List(x))).get
					globalVisits = globalVisits ++ vi
					Some(vi)
				}
			}).toList
		}

	}


	def runLena(): Unit =
	{
		/* Read in image */
		val lena = ImageIO.read(new File("lenaGrayScale.jpg"))

		/* Get raster data from image */
		val raster = lena.getData()

		/* Populates a matrix with the values from the raster obj */
//		val matrix = DenseMatrix.tabulate(lena.getWidth, lena.getHeight){case (i,j) => raster.getSample(i, j, 0) / 255d}
	}
}

