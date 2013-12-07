package Mafia;

import java.net.Socket;

public class Chat extends Thread {

	/**
	 * @param args
	 */
	public Socket player;
	
	public Chat(Socket s)
	{
		player=s;
	}

}
