package mafia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class UserList {
	public static Map<Integer, User> user_list = new HashMap<Integer, User>();
	protected static ArrayList<String> messages_list = new ArrayList<String>();

	public static void add_user( int key, User user ) {
		user_list.put(key, user);
	}
	
	public static void send_message(String message){
		messages_list.add(message);
	}
}
