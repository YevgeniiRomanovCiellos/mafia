package mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

public class Main {

	static final int PORT = 8080;
	public static boolean isPlaying = false;
	private static GUI _gui;
	public static ServerSocket s ; 
	
	static boolean stop=true;
	
	public static void main(String[] args) throws IOException {
	
		
		
		
		//GUI interests
		_gui=new GUI();
		_gui.show();
		_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_gui.start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					s= new ServerSocket(PORT);
					
					
					System.out.println("Server Started");
					new MessagesSender();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		_gui.stop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				for (User value : UserList.user_list.values()) {
					value.stop();
					stop=false;
					try {
						value.deactivate();
						if ((s!= null) && !(s.isClosed())) {
						s.close();
						
						}
						
						s = null;
						//System.exit(1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
				
			}});
		
		//
		
		
		try {
			
			
			while (stop) {
				// Останавливает выполнение, до нового соединения:
				if(s!=null)
				{
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
			}
		} finally {
			s.close();
		}
		
		
	}

}
