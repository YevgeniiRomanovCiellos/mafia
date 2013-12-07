package Mafia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;

public class Player extends Thread {

	/**
	 * @param args
	 */
	public Socket c;
	private BufferedReader in;
	public PrintWriter out;
	private Yaml yaml = new Yaml();
	String st;
	
	public Player(Socket c)
	{
		
		
		this.c=c;
	
		Map<String, String> response;
		
		
			
			try {
				in = new BufferedReader(new InputStreamReader(c.getInputStream()));
				while(true)
				{
					String str = in.readLine();
					response = (HashMap<String, String>) yaml.load(str);
				
					if(response.get("action").equals("chat"))
					{
						//if(response.get("mafia"))
						//{
							
						//}
						st=response.get("message")+"\n";
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void run()
	{
		
		
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return st;
	}
	
	public void sendMessage(String str)
	{
		String st=str;
		Map<String,String> m=new HashMap<String,String>();
		m.put("action", "chat");
		m.put("message", "st");
		
		try {
			(new PrintStream(c.getOutputStream())).println(yaml.dump(m));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
