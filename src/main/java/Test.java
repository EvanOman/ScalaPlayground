abstract class Test
{
	public String name;
	public String data = "Default Data";

	public Test(String name, String data)
	{
		this.name = name;
		this.data = data;
	}

	public Test(Test oldInst, String newData)
	{
		this.name = oldInst.name;
		this.data = newData;
	}
}

class Test2 extends Test
{

}