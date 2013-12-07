package mafia;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.*;



public class GUI extends JFrame {

	int port;
	JButton start,stop;
	public static Container c;
	public JTextArea memo;
	public JScrollPane scrollBar;
	
	public GUI() {
		// TODO Auto-generated method stub
		this.port=port;
		
		//setting sizes
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) dim.getWidth();
		int screenHeight = (int) dim.getHeight();
		setSize(screenWidth,screenHeight);
		
		 //setting elements
        c=this.getContentPane();
		c.setLayout(new GridLayout(2,2));
		
		memo=new JTextArea(20,60);
		scrollBar = new JScrollPane(memo);
		
		start=new JButton("Start server");
		stop=new JButton("Stop server");
		
		JPanel chatPanel=new JPanel();
		JPanel actionPanel=new JPanel();
		
		chatPanel.add(scrollBar);
		actionPanel.add(start);
		actionPanel.add(stop);
		
		c.add(chatPanel);
		
		c.add(actionPanel);
	}
	
	

}
