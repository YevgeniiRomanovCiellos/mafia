package mafia;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Analizer {
	protected static Yaml yaml = new Yaml();
	public static void analize ( Map<String, String> response, User user){
		
		try {
			
		
		System.out.println("Action Perfom: " + response.get("action"));
		String action = (String)response.get("action");
		System.out.println("Action Perfom2: " + action);
		
		System.out.println("dump response : " + yaml.dump(response));
		

		if (action.equals("chat")){
			System.out.println("Start send message : ");
			UserList.messages_list.add(yaml.dump(response));
			System.out.println("Stop send message : ");
		}
		
		if(action == "redy"){
			UserList.ready_users.put(user.port, user);
			if(UserList.isUsersReady()){
				try {
					new Game();
				} catch (InterruptedException e) {
					// TODO Автоматически созданный блок catch
					e.printStackTrace();
				}
			}
		}
		if (action == "game-action"){
			
		}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	

}
