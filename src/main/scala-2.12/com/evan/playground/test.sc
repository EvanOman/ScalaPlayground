import scala.collection.TraversableLike
import scala.collection.generic.CanBuildFrom


def dropR[E, D[E] <: Traversable[E]]
(xs: D[E])
(implicit cbf: CanBuildFrom[D[E], E, D[E]]): D[E] =
{
    if (xs.size > 1) dropR[E,D](xs.drop(1).to[D])  else xs.to[D]
}


