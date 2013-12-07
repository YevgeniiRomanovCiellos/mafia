package mafia;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.*;

public class User extends Thread {

	public PrintWriter out;

	private Socket socket;
	private BufferedReader in;
	private Yaml yaml = new Yaml();
	private int port;

	public User(Socket s) throws IOException {
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// Включение автосброса буферов:
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);

		// Если какой либо, указанный выше класс выбросит исключение
		// вызывающая процедура ответственна за закрытие сокета
		// В противном случае нить(поток) закроет его.

		start(); // Вызывает run()
		port = socket.getPort();
		UserList.add_user(port, this);
	}

	public void run() {
		try {
			boolean work = true;
			while (work) {
				String str = in.readLine();
				System.out.println("Excepted:");
				if (str == "END") {
					socket.close();
					UserList.user_list.remove(port);
					work = false;
					break;
				} else {

					System.out.println(str);
					Map<String, String> response;
					
					response = (HashMap<String, String>) yaml.load(str);
					Analizer.analize(response);
					
					
				//	UserList.send_message((String) yaml.dump(response));

				}
			}
		} catch (IOException e) {
			System.err.println("IO Exception");
		} catch(ClassCastException e){
			System.out.println("Cannot get response");
		}finally {
			try {
				socket.close();
				UserList.user_list.remove(port);
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}

}
