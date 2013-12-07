package mafia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class UserList {
	public static Map<Integer, User> user_list = new HashMap<Integer, User>();
	protected static ArrayList<String> messages_list = new ArrayList<String>();
	public static Map<Integer, User> ready_users = new HashMap<Integer, User>();

	public static void add_user( int key, User user ) {
		user_list.put(key, user);
	}
	
	public static void send_message(String message){
		messages_list.add(message);
	}
	
	public static boolean isUsersReady(){
		boolean result;
		if (user_list.size() == ready_users.size()){
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
}
