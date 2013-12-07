package mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Main {

	static final int PORT = 8080;
	private static GUI _gui;
	public static void main(String[] args) throws IOException {
		
		ServerSocket s ; 
		s= new ServerSocket(PORT);
		
		//GUI interests
		_gui=new GUI();
		_gui.show();
		
		//
		
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
