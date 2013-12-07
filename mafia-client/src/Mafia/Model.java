package Mafia;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;



public class Model extends Thread{

	public Socket c;
	public int port;
	public Player p;
	
	public Model()
	{
		
	start();	

	}
	@Override
	public void run() {
		try{
			
			c=new Socket("127.0.0.1",8080);
			System.out.println("connected/");
			p=new Player(c);
							
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}




	public void sendMessage(String text) {
		p.sendMessage(text);
		
	}




	

}
