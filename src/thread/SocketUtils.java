package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketUtils {
	private PrintWriter out;
	private Socket socket;
	private BufferedReader in;
	
	 
	public SocketUtils(Socket socket) throws IOException {
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream());
		System.out.println("Client " 
		+socket.getRemoteSocketAddress()+ " has been connected!");
	}
	
	public SocketAddress getRemoteSocketAddress() {
		return this.socket.getRemoteSocketAddress();
	}
	
	public String getMessage() throws IOException {
		return in.readLine();
	}
	
	public boolean send(String msg) {
		out.println(msg);
		return !out.checkError();
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}


	public static void main(String[] args) {
		
	}
}
