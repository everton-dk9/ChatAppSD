package thread;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerChat {
	public static final int PORT = 4000;
	private ServerSocket serversocket = null;
	
	public void start() throws IOException {
		serversocket = new ServerSocket(PORT);
		System.out.println("Server started in port: "+ PORT);
		clientConnectionLoop();
	}
	
	private void clientConnectionLoop() throws IOException {
		SocketUtils socketutils = new SocketUtils(serversocket.accept());
		
		new Thread(()->{
			try {
				clientMessageLoop(socketutils);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}).start();	
	}
	
	
	public void clientMessageLoop(SocketUtils socketutils) throws IOException {
		String msg;
		try {
			while((msg=socketutils.getMessage()) != null) {
				if("sair".equalsIgnoreCase(msg))
					return;
				
				System.out.printf("Message received from client %s: %s",
						socketutils.getRemoteSocketAddress(), msg);			
			}
		} finally {
			socketutils.close();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerChat chatServer = new ServerChat();
		chatServer.start();
	}

}
