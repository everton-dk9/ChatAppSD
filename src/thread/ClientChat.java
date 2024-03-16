package thread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientChat {
	public static final int PORT = 4000;
	private SocketUtils clientsocket = null;
	private final String SERVER_ADDRESS = "127.0.0.1";
	
	private Scanner scanner;

	public ClientChat() {
		this.scanner = new Scanner(System.in);
	}

	public void start() throws UnknownHostException, IOException {
		clientsocket = new SocketUtils(
				new Socket(SERVER_ADDRESS, PORT));
		
		System.out.println("client connected to server in "
				+SERVER_ADDRESS+": "+PORT);
		messageLoop();
	}
	
	private void messageLoop() {
		String msg = null;
		do {
			System.out.println("Write your message or type exit to quit");
			msg = scanner.nextLine();
			clientsocket.send(msg);
		} while (!msg.equalsIgnoreCase("exit"));
	}

	public static void main(String[] args) {
		ClientChat chatClient = new ClientChat();
		try {
			chatClient.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client exit");
	}

}
