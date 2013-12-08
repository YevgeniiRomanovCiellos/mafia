package Mafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
 
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.yaml.snakeyaml.*;

public class Model extends Thread {

	public Socket c;
	public int port;
	public String user_name;
	public String user_id;
	public String user_role;

	private BufferedReader in;
	public PrintWriter out;

	private Yaml yaml = new Yaml();

	Map<String, Object> response = new HashMap<String, Object>();

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
				str = in.readLine().replace(";;", "\n");
				System.out.println("Message Exepted ");
				System.out.println(str);
				response = (HashMap<String, Object>) yaml.load(str);
				if (response != null) {
					
					System.out.println("LOL ");
					if (response.get("action").equals("chat")) {
						View.memo.append(response.get("message").toString());
						View.memo.append("\n");

					}
					
					//
					if(response.get("action").equals("user-id")){
						user_id = response.get("userid").toString();						
					}
					
					if (response.get("action").equals("sendRoles"))
					{
						ArrayList<Map> roles = (ArrayList<Map>) response.get("roles");
						Map<String, String>	user = new HashMap<String, String>();
						System.out.print("roles recived!");
						System.out.print(roles.toString());
						for (int i = 0; i < roles.size(); i++) {
							user = roles.get(i);
					 
							if (user.get("userid").equals(user_id)){
								user_role = user.get("role");
								setRole();		
							}
							
							else {
							 View.addCard(user.get("username"));
							}
								
							
						
						}
						
					}
					sleep(10);
				}
				
				
				
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				// printStackTrace();
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}


	private void setRole() {
		// роль юзера тут -> user_role
		View.card.setText(user_name);
		ImageIcon icon = new ImageIcon("C:/Users/Xbox/Documents/GitHub/mafia/mafia-client/res/civilian.jpg");
		View.card.setIcon(icon);
	}

	public void sendMessage(Map<String, Object> m) {

		try {
			out.println(yaml.dump(m));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
