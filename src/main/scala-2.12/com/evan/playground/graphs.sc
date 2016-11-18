/* Input, can be read from file easily by splitting on ", " */
val lines = List(List("i1", "i2", "i5"),
	List("i3", "i4"),
	List("i2", "i6", "i7"),
	List("i4", "i8"),
	List("i9", "i3"))
/* finds all sequential pairs */
val pairs =  lines.flatMap(x => x.dropRight(1).zip(x.drop(1)))

/* create an empty adjacency map: id -> (List of adjacent edges) */
val defMap = Map[String, Set[String]]().withDefaultValue(Set[String]())

/* populate the default map with the actual (symmetric) adjacencies */
val adjMap = pairs.foldLeft{defMap}(
	(acc, x) => acc + (x._1 -> (acc(x._1) + x._2)) + (x._2 -> (acc(x._2) + x._1)))

/* BFS algo on map representation of graph */
def mapBFS(adjMap: Map[String, Set[String]]): List[List[String]] =
{
	val v = adjMap.keys
	var globalVisits = List[String]()
	def BFS_r(elems: List[String], visited: List[List[String]]): List[String] =
	{
		val newNeighbors = elems.flatMap(adjMap(_)).filterNot(visited.flatten.contains).distinct
		if (newNeighbors.isEmpty)
			visited.flatten
		else
			BFS_r(newNeighbors, newNeighbors :: visited)
	}
	v.flatMap(x =>{
		if (globalVisits.contains(x))
			None
		else
		{
			val vi: List[String] = BFS_r(List(x), List(List(x)))
			globalVisits = globalVisits ++ vi
			Some(vi)
		}
	}).toList
}
mapBFS(adjMap).foreach{println}