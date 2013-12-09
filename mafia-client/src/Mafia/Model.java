package Mafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
 
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
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
					String action = (String)response.get("action");
					System.out.println("LOL ");
					if (action.equals("chat")) {
						View.memo.append(response.get("message").toString());
						View.memo.append("\n");

					}
					
					//
					if(action.equals("user-id")){
						user_id = response.get("userid").toString();						
					}
					
					if (action.equals("sendRoles"))
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
							 View.addCard(user);
							}
								
							
						
						}
						
					}
					
					if (action.equals("game-results")) {
						
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
		
		String path = "icon1.png";
		URL imgURL;
		try {
			imgURL = new URL(path);
			ImageIcon icon = new ImageIcon(imgURL);
			View.card.setIcon(icon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		View.card.setText(user_name);
		if(user_role.equals("civilian"))
		{
		ImageIcon icon1 = new ImageIcon("C:/Users/moskra/Documents/GitHub/mafia/mafia-client/src/Mafia/civilian.jpg");
		
		View.card.setIcon(icon1);
		}
		if(user_role.equals("mafia"))
		{
			ImageIcon icon1 = new ImageIcon("C:/Users/moskra/Documents/GitHub/mafia/mafia-client/src/Mafia/mafia.jpg");
			View.card.setIcon(icon1);
		}
		if(user_role.equals("doctor"))
		{
			ImageIcon icon1 = new ImageIcon("C:/Users/moskra/Documents/GitHub/mafia/mafia-client/src/Mafia/doctor.jpg");
			View.card.setIcon(icon1);
		}
			
		
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
