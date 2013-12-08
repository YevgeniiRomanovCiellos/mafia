package mafia;


public class MessagesSender extends Thread {
	public MessagesSender() {
		start();
	}

	@Override
	public void run() {
		try {
			while (true){
				if (!UserList.messages_list.isEmpty()){
					System.out.println(UserList.messages_list.size());
					String message = UserList.messages_list.get(0).replace("\n", ";;");	
					
					GUI.println("Sending message");
					for(User value : UserList.user_list.values()){
						value.out.println(message);
					}
					GUI.println(UserList.ready_users.size()+ "" );
					GUI.println(message);
					UserList.messages_list.remove(0);
				}
				sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Exception Rised!!!");
		}
	}
}
