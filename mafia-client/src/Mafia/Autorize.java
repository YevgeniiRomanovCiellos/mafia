package Mafia;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class Autorize extends JFrame {

	JLabel port,adress,name;
	JTextField _port,_adress,_name;
	JButton action;
	
	Vector <String> v=new Vector<String>();
	
	public Autorize()
		{
		this.setSize(100,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c=this.getContentPane();
			c.setLayout(new GridLayout(2,1));
			port=new JLabel("Port:");
			adress=new JLabel("Adress:");
			name=new JLabel("Name:");
			
			_port=new JTextField("8080");
			_adress=new JTextField("127.0.0.1");
			_name=new JTextField("Name");
			
			action=new JButton("Ok");
			JPanel p=new JPanel();
			p.setLayout(new GridLayout(3,3));
			p.add(port);
			p.add(_port);
			p.add(adress);
			p.add(_adress);
			p.add(name);
			p.add(_name);
			c.add(p);
			c.add(action);
			
			
		}
	
	public Vector getInfo()
	{
		
		return v;
	}
	
	public void setInfo()
	{
		v.add(_port.getText().trim());
		v.add(_adress.getText().trim());
		v.add(_name.getText().trim());
	}
	}


