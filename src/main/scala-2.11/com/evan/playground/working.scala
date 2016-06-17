package com.evan.playground
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import breeze.linalg._

object working
{
	/* Read in image */
	val lena = ImageIO.read(new File("lenaGrayScale.jpg"))

	/* Get raster data from image */
	val raster = lena.getData()

	/* Populates a matrix with the values from the raster obj */
	val matrix = DenseMatrix.tabulate(lena.getWidth, lena.getHeight){case (i,j) => raster.getSample(i, j, 0) / 255d}

}

