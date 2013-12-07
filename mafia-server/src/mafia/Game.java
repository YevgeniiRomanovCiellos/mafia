package mafia;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {
	public Game() throws InterruptedException {
		//STOP ACCEPT USERS
		//SEND USER ROLES
		//slepp 10sek
		//WAIT FOR  USER ACTIONS
		Main.isPlaying = true;
		setRolesToUsers();
		TimeUnit.SECONDS.sleep(10);
		
		sendRoles();
//		TimeUnit.MINUTES.sleep(1);
	}
	
	void setRolesToUsers(){
		
		int users = UserList.ready_users.size();
		String[] roles = new String[users]; 
		roles[0]= "mafia";
		roles[1] = "doctor";
		for (int i= 2; i <= users; i++ ){
			roles[i] = "civilian";
		}
		
		shuffle(roles);
		int i = 0;
		for(User value : UserList.ready_users.values()){
			value.role = roles[i];
			i++;
		}

	}
	
	void sendRoles(){
		//Map<String, String> roles = new HashMap<String, String>();
		List list = new List();	
//		for(User value : UserList.ready_users.values()){
//			value.role;
//		}
	}

	

	public static final Random gen = new Random();
	 
	// version for array of references
	public static void shuffle (Object[] array) {
	    int n = array.length;
	    while (n > 1) {
	        int k = gen.nextInt(n--); //decrements after using the value
	        Object temp = array[n];
	        array[n] = array[k];
	        array[k] = temp;
	    }
	}
}
