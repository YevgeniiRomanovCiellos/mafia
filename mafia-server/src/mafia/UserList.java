package mafia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;



public class UserList {
	public static Map<Integer, User> user_list = new HashMap<Integer, User>();
	protected static ArrayList<String> messages_list = new ArrayList<String>();
	public static Map<Integer, User> ready_users = new HashMap<Integer, User>();

	
	static Map<String, String> response = new HashMap<String, String>() ;
	static Yaml yaml = new Yaml();
	public static void add_user( int key, User user ) {
		user_list.put(key, user);
		
		
		

		response.put("action", "user-id");
		response.put("userid", key+"");
		
		user.out.println(yaml.dump(response));
		GUI.memo.append(key+"");
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
