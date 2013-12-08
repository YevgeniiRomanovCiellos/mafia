package mafia;

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
	public Map<String, String> night_actions = new HashMap<String, String>();// actoin : user_id
	public Map<String, String> cards = new HashMap<String, String>(); //  role: userid
	private Yaml yaml = new Yaml();
	public Game() throws InterruptedException {
		GUI.println("Start create game");
		Main.isPlaying = true;
		setRolesToUsers();
		GUI.println("roles seted");
		sendRoles();
		GUI.println("roles sended");
		TimeUnit.SECONDS.sleep(10);
		stopSleeping();
//		sendgameresult
//		listen to votes
//		print votes
		
	}
	void setRolesToUsers(){
		int users = UserList.ready_users.size();
		GUI.println("users count " +users );
		String[] roles = new String[users+1]; 
		roles[0]= "mafia";
		roles[1] = "doctor";
		for (int i= 2; i < users; i++ ){
			roles[i] = "civilian";
		}
		
		shuffle(roles);
		int i = 0;
		for(User value : UserList.ready_users.values()){
			value.role = roles[i];
			cards.put(value.role, value.port+"" );
			i++;
		}
	}
	
	void sendRoles(){
		 
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
			GUI.println("sleeping stoped");
			sendGameResults();
			}
		};
		Timer time = new Timer();
		time.schedule(isNotSleeping, 10000);
	}	
	
	
	
	public void sendGameResults() {
		Map<String, String> result = new HashMap<String, String>();
		String killed, hilled = "none";
		result.put("action", "game-results");
		
		killed = night_actions.get("hill");
		hilled = night_actions.get("kill");
		
		if(night_actions.get("kill").equals(night_actions.get("hill"))){
			hilled = night_actions.get("hill");
		}
		
		if(night_actions.get("kill").equals(cards.get("doctor"))){
			killed = cards.get("doctor");
		}
		
				
		result.put("killed_id", killed);
		result.put("hilled_id", hilled);
		UserList.send_message(yaml.dump(result));
		
				
	}
	
}
