package chat;

import java.awt.EventQueue;

public class Chat {
	Client client;
	ClientView view;
	public Chat(){
		client = new Client();
		view = new ClientView();
		client.setView(view);
		view.setClient(client);
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		client.start();
	}

	public static void main(String[] args) {
		Chat c = new Chat();
	}
}
