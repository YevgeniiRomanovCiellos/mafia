package mafia;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.yaml.snakeyaml.Yaml;

public class Game {
	boolean isSleeping=true;
	public Game() throws InterruptedException {
		
		Main.isPlaying = true;
		setRolesToUsers();
		sendRoles();
		TimeUnit.SECONDS.sleep(10);
		stopSleeping();
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
		 Yaml yaml = new Yaml();
         Map<String, String> m = new HashMap<String, String> ();
         Map<String, Object> e = new HashMap<String, Object> ();
         ArrayList<Map> roles =  new ArrayList<Map>();
         for(Map.Entry<Integer,User> values: UserList.ready_users.entrySet()){
				Integer port = values.getKey();
				User user = values.getValue();
				m.put(port + "", user.role);
				roles.add(m);
			}
         e.put("action", "sendRoles");
         e.put("roles", roles);
	}

	public static final Random gen = new Random();
	public static void shuffle (Object[] array) {
	    int n = array.length;
	    while (n > 1) {
	        int k = gen.nextInt(n--); //decrements after using the value
	        Object temp = array[n];
	        array[n] = array[k];
	        array[k] = temp;
	    }
	}
	
	
	public void stopSleeping() {
		TimerTask isNotSleeping = new TimerTask() {
			public void run() {
			isSleeping=false;	
			}
		};
		Timer time = new Timer();
		time.schedule(isNotSleeping, 10000);
	}	
	
}
