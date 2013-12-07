package mafia;

import java.io.*;
import java.net.*;

public class Main {

	static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started");
		new MessagesSender();
		try {
			
			while (true) {
				// Останавливает выполнение, до нового соединения:
				Socket socket = s.accept();
				try {
					new User(socket);
					System.out.println("Usrer excepted");
				} catch (IOException e) {
					// Если неудача - закрываем сокет, в противном случае нить
					// закроет его:
					socket.close();
				}
			}
		} finally {
			s.close();
		}
	}

}
