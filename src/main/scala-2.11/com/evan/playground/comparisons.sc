def lt(x: Seq[Int], y: Seq[Int]): Boolean = x.zip(y).collectFirst{
		case (xi, yi) if xi != yi => xi < yi
	}.getOrElse(x.length < y.length)

def gt(x: Seq[Int], y: Seq[Int]): Boolean = lt(x.map(-_), y.map(-_))

def toInts(x: String): Seq[Int] = x.map(_.toInt)

def strLt(x: String, y: String): Boolean = lt(toInts(x), toInts(y))

def randStr: String = {
	val len = scala.util.Random.nextInt(500)
	scala.util.Random.alphanumeric.take(len).mkString
}

val a = (0 to 10000).par.map(x => {
	val r1 = randStr
	val r2 = randStr
	if (!strLt(r1, r2) == (r1 < r2))
		(r1, r2)
	else
		("", "")
}).seq.filter(_ != ("", ""))

a.foreach(println)
