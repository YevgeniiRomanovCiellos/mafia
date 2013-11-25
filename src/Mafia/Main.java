package Mafia;



public class Main {

	static View v;
	static Model m;
	static Controller c;
	public static void main(String[] args) {


		m=new Model();
		v=new View(m);
		c=new Controller(v,m);
		

	}

}
