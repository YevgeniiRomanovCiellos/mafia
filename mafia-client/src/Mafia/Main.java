package Mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class Main {

	static View v;
	static Model m;
	static Controller c;
	
	
	public static void main(String[] args) {

		final Autorize a_rize=new Autorize();
		a_rize.show();
		a_rize.action.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				
				a_rize.setInfo();
				m=new Model(a_rize.getInfo().get(2).toString(),a_rize.getInfo().get(0).toString(),a_rize.getInfo().get(1).toString());
				v=new View(m);
				c=new Controller(v,m);
				
				v.show();
				a_rize.setVisible(false);
			}});
		
		
		
		//
	}

}
