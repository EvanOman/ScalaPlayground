/*
	SRC: http://stackoverflow.com/a/41350143/2661491
*/

/*
	One nice thing about functional programming is you can take advantage of laziness to separate
	the traversal of your data structure from the searching part. This makes for very reusable,
	single responsibility code:
*/

import scala.collection.immutable.Queue

def breadth_first_traverse[Node](node: Node, f: Node => Seq[Node]): Stream[Node] = {
	def recurse(q: Queue[Node]): Stream[Node] = {
		if (q.isEmpty) {
			Stream.Empty
		} else {
			val (node, tail) = q.dequeue
			node #:: recurse(tail ++ f(node))
		}
	}

	node #:: recurse(Queue.empty ++ f(node))
}

case class Node(value: Int, children: Seq[Node])

/*
	Make the tree:
	        4 - 5
	      /
	    3 - 6
	  /
	2 - 7
	  \
	    1
*/
val one   = Node(1, Seq.empty[Node])
val five  = Node(5, Seq.empty[Node])
val six   = Node(6, Seq.empty[Node])
val seven = Node(7, Seq.empty[Node])
val four  = Node(4, Seq(five))
val three = Node(3, Seq(six, four))
val two   = Node(2, Seq(one, three, seven))

breadth_first_traverse(two, (n: Node) => n.children).map(_.value).toList

/*
	Output: List(2, 1, 3, 7, 6, 4, 5) as desired
*/

breadth_first_traverse(two, (n: Node) => n.children) find (_.value > 6)

/*
	res1: Option[Node] = Some(Node(7,List()))
*/
*/