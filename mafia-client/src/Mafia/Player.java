package Mafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

	public Player(Socket c) {

		this.c = c;
		start();
	}

	public void run() {

		Map<String, String> response;

		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					c.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		} catch (IOException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
		
		
		
		
		while (true) {
			try {
				System.out.println("Server litsening... ");
				String str = in.readLine();
				System.out.println("Message Exepted ");
				System.out.println(str);
				if (!str.isEmpty()) {
					response = (HashMap<String, String>) yaml.load(str);
					System.out.println("LOL ");
					if (response.get("action").equals("chat")) {
						View.memo.append(response.get("message"));
						View.memo.append("\n");
				
					}
				}
				sleep(10);
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



	public void sendMessage(String str) {
		String st = str;
		Map<String, String> m = new HashMap<String, String>();
		m.put("action", "chat");
		m.put("message", "\"" + st + "\"");

		try {
			out.println(yaml.dump(m));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
