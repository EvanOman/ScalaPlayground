class Super(val name: String, val x: String)
{
	def identify = println("This is a Test_1 with properties" +
			s"\n\tName: ${this.name}\n\tData: ${this.x}")

	def updateX(newX: String): Super = new Super(name, newX)
}

class Sub_1(name: String, x: String) extends Super(name, x)
{
	override def identify = println("This is a Test_1 with properties" +
			s"\n\tName: ${this.name}\n\tData: ${this.x}")
}

class Sub_2(name: String, x: String) extends Super(name, x)
{
	override def identify = println("This is a Test_2 with properties" +
			s"\n\tName: ${this.name}\n\tData: ${this.x}")
}

val s1 = new Sub_1("sub1", "original data")
s1.identify
def updateSubX[T <: Super](orig: T, newX: String): T = new T(orig.name, newX)
val s2 = updateSubX(s1, "new data")