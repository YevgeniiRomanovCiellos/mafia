package mafia;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Analizer {

	protected static Game game = null;
	protected static Yaml yaml = new Yaml();

	public static void analize(Map<String, String> response, User user) {

		try {

			if (response != null) {
				System.out.println("Action Perfom: " + response.get("action"));
				String action = (String) response.get("action");
				System.out.println("Action Perfom2: " + action);

				System.out.println("dump response : " + yaml.dump(response));

				if (action.equals("chat")) {
					System.out.println("Start send message : ");
					UserList.messages_list.add(yaml.dump(response));
					System.out.println("Stop send message : ");
				}

				if (action.equals("ready")) {
					UserList.ready_users.put(user.port, user);
					System.out.println(user.port);
					if (UserList.isUsersReady()) {
						try {
							game = new Game();
						} catch (InterruptedException e) {
							// TODO Автоматически созданный блок catch
							e.printStackTrace();
						}
					}
				}

				if (action.equals("game-action")) {
					GUI.println("ACttion perfomed!!!!!!!!!!");
					GUI.println(response.toString());
					if (game.isSleeping) {
						String userfrom = game.cards.get(response
								.get("userfrom"));

						if (userfrom.equals("mafia")) {
							game.night_actions.put("kill",
									response.get("userto"));
						}

						if (userfrom.equals("doctor")) {
							game.night_actions.put("hill",
									response.get("userto"));
						}
						// recive user messages
					}

				}

				if (action.equals("conection")) {
					user.username = response.get("username");
					GUI.println("Add username");
					GUI.println(user.username);
				}
				
				if (action.equals("vote")) {
					
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

}
