package es.deusto.ingenieria.sd.Facebook.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class FacebookService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private static String DELIMITER = "#";
	
	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();			
			System.out.println("   - FacebookService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			boolean user = this.login(data);
			this.out.writeBoolean(user);					
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FacebookService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public boolean login(String user) {
		boolean userExists = false;
		
		if (user != null && !user.trim().isEmpty()) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(user, DELIMITER);		
				String userEmail = tokenizer.nextToken();
				String userPassword = tokenizer.nextToken();
				//String text = tokenizer.nextToken();
				//System.out.println("   - Starting translation of " + text + " from: " + langFrom + " to " + langTo);
		
				if (userEmail != null && userPassword != null) {
					FacebookLogin fb = new FacebookLogin();
					userExists = fb.userExists(userEmail, userPassword);
					if (userExists == true) {
						System.out.println("User " + userEmail + " exists in the Facebook server");
					}
					else {
						System.out.println("User " + userEmail + " does not exist on Facebook server");
					}
				}
			} catch (Exception e) {
				System.err.println("   # FacebookLogin - Facebook API error:" + e.getMessage());
				userExists = false;
			}
		}
		
		return userExists;
	}
}