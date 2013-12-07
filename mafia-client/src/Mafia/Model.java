package Mafia;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;



public class Model {

	public Socket c;
	public InetAddress adress;
	public int port;
	private Yaml yaml = new Yaml();
	
	public Model()
	{
		Map<String, String> response;
		response = (HashMap<String, String>) yaml.load(str);
		
		try {
			c=new Socket(adress,port);
			
			//Chat(c);
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
