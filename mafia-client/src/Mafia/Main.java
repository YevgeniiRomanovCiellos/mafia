package Mafia;

import javax.swing.JOptionPane;



public class Main {

	static View v;
	static Model m;
	static Controller c;
	public static void main(String[] args) {


		m=new Model(JOptionPane.showInputDialog("Enter username"));
		String str;
		v=new View(m);
		c=new Controller(v,m);
		
		v.show();
	}

}
