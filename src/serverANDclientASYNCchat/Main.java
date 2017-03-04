package serverANDclientASYNCchat;

public class Main {

	public static void main(String[] args) {
		Server s = new Server(new aSyncChatClientHandler());
		
		s.Start(6666, "exit");

	}

}
