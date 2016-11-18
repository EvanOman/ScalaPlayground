val lines = scala.io.Source.fromFile("f.txt").getLines.toList

val pairs =  lines.map(_.split(", ")).flatMap(x => x.dropRight(1).zip(x.drop(1)))

val pSym = pairs.flatMap(x => List(x, x.swap))

val vertices = lines.flatMap(_.split(", ")).distinct

val defMap = vertices.map(_ -> List[String]()).toMap

val adjMap = pairs.foldLeft{defMap}(
	(acc, x) => acc + (x._1 -> (acc(x._1) ++ List(x._2)))
)

def mapBFS(adjMap: Map[String, List[String]]): List[List[String]] =
{
	val v = adjMap.keys
	var globalVisits = List[List[String]]()

	def BFS_r(elems: List[String], visited: List[List[String]]): Option[List[List[String]]] =
	{
		val newNeighbors = elems.flatMap(adjMap(_)).filterNot(visited.flatten.contains).distinct
		if (newNeighbors.isEmpty)
			// only returm, update glabal visits
			Some(visited)
		else
			BFS_r(newNeighbors, newNeighbors :: visited)
	}
	v.flatMap(x =>{
		if (globalVisits.flatten.contains(x))
			None
		else
		{
			val vi: List[List[String]] = BFS_r(List(x), List(List(x))).get
			globalVisits = globalVisits :: vi
			vi
		}
	})
}