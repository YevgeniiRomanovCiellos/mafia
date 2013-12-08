package Mafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;

public class Model extends Thread {

	public Socket c;
	public int port;
	public String user_name;
	public String user_id;

	private BufferedReader in;
	public PrintWriter out;

	private Yaml yaml = new Yaml();

	Map<String, String> response = new HashMap<String, String>();

	public Model(String showInputDialog) {
		{
			user_name = showInputDialog;

			try {
				c = new Socket("127.0.0.1", 8080);

				System.out.println("connected/");

				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(c.getOutputStream())), true);
				in = new BufferedReader(new InputStreamReader(
						c.getInputStream()));
				response.put("action", "conection");
				response.put("username", user_name);
				sendMessage(response);
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			start();

		}

	}

	public void run() {

		String str;
		while (true) {
			try {
				System.out.println("Server litsening... ");
				str = in.readLine();
				System.out.println("Message Exepted ");
				System.out.println(str);
				if (!str.isEmpty()) {
					response = (HashMap<String, String>) yaml.load(str);
					System.out.println("LOL ");
					if (response.get("action").equals("chat")) {
						View.memo.append(response.get("message"));
						View.memo.append("\n");

					}
					
					if(response.get("action").equals("user-id")){
						user_id = response.get("userid");
					}
					
					sleep(10);
				}
				
				
				
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				// printStackTrace();
				System.out.println("Beda");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("sleep Beda");
			}
		}

	}

	public void sendMessage(Map<String, String> m) {

		try {
			out.println(yaml.dump(m));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
