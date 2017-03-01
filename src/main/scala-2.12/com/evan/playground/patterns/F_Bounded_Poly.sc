import scala.reflect.ClassTag

abstract class Super[T <: Super[T] : ClassTag](val name: String, val x: String)
{
	def mkT(name: String, x: String): T

	def identify: Unit

	def updateX(newX: String): T = mkT(this.name, newX)
}

class Sub_1(name: String, x: String) extends Super[Sub_1](name, x)
{
	def mkT(name: String, x: String) : Sub_1 = new Sub_1(name, x)

	def identify = println("This is a Sub_1 with properties" +
			s"\n\tName: ${this.name}\n\tData: ${this.x}")
}


class Sub_2(name: String, x: String) extends Super[Sub_2](name, x)
{
	def mkT(name: String, x: String) : Sub_2 = new Sub_2(name, x)

	def identify = println("This is a Sub_2 with properties" +
			s"\n\tName: ${this.name}\n\tData: ${this.x}")
}

val s1 = new Sub_1("sub1", "original data")
s1.identify

/*
This is a Sub_1 with properties
    Name: sub1
    Data: original data
*/

val s2: Sub_1 = s1.updateX("new data")
s2.identify

/*
This is a Sub_1 with properties
    Name: sub1
    Data: new data
*/