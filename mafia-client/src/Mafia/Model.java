package Mafia;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;



public class Model {

	public Socket c;
	public InetAddress adress;
	public int port;
	public Player p;
	
	
	
	
	public Model()
	{
		
		try{
		
		c=new Socket(adress,port);
		p=new Player(c);
		p.start();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	public void sendMessage(String text) {
		p.sendMessage(text);
		
	}




	public String getText() {
		String st=p.getMessage();
		return st;
	}

}
