/* Input, can be read from file easily by splitting on ", " */
val lines = List(List("i1", "i2", "i5"),
	List("i3", "i4"),
	List("i2", "i6", "i7"),
	List("i4", "i8"),
	List("i9", "i3"))
/* finds all sequential pairs */
val pairs =  lines.flatMap(x => x.dropRight(1).zip(x.drop(1)))

/* each pair should be symmetric (we are in an undirected graph) */
val pSym = pairs.flatMap(x => List(x, x.swap))

/* create an empty adjacency map: id -> (List of adjacent edges) */
val vertices = lines.flatten.distinct
val defMap = vertices.map(_ -> List[String]()).toMap

/* populate the default map with the actual adjacencies */
val adjMap = pSym.foldLeft{defMap}(
	(acc, x) => acc + (x._1 -> (acc(x._1) :+ x._2))
)

/* BFS algo on map representation of graph */
def mapBFS(adjMap: Map[String, List[String]]): List[List[String]] =
{
	val v = adjMap.keys
	var globalVisits = List[String]()
	def BFS_r(elems: List[String], visited: List[List[String]]): Option[List[List[String]]] =
	{
		val newNeighbors = elems.flatMap(adjMap(_)).filterNot(visited.flatten.contains).distinct
		if (newNeighbors.isEmpty)
			Some(visited)
		else
			BFS_r(newNeighbors, newNeighbors :: visited)
	}
	v.flatMap(x =>{
		if (globalVisits.contains(x))
			None
		else
		{
			val vi: List[String] = BFS_r(List(x), List(List(x))).get.flatten
			globalVisits = globalVisits ++ vi
			Some(vi)
		}
	}).toList
}

mapBFS(adjMap).foreach{println}

