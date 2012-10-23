package com.adgsoftware.mydomo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.adgsoftware.mydomo.engine.Command;

public class Server implements Runnable {

	private int port = 1234;
	private volatile Thread blinker;
	
    public void stop() {
        blinker = null;
    }

    public void start() {
        blinker = new Thread(this);
        blinker.start();
    }
    
    public void run() {
		ServerSocket serveur;
		Thread thisThread = Thread.currentThread();
		// installation
		try {
			serveur = new ServerSocket(port);

			while (blinker == thisThread) {

				System.out
						.println("Waiting connection for Monitor/Command on port ["
								+ port + "]...");
				
				Socket s = serveur.accept(); // création de nouvelles connexions
				BufferedReader depuisClient; // réception de requête
				PrintWriter versClient; 	 // envoi des réponses

				try {
					// Read from client
					depuisClient = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					// Write to client
					versClient = new PrintWriter(new OutputStreamWriter(
							s.getOutputStream()), true);
					// Welcome ack
					write(Command.ACK, versClient);
					String sessionType = read(s, depuisClient);
					if (Command.MONITOR_SESSION.equalsIgnoreCase(sessionType)) {
						System.out.println("Start Monitor Session...");
						write(Command.ACK, versClient);
						ControllerStateManagement.registerMonitorSession(
								new MonitorSession(s, depuisClient,
										versClient)
						);

					} else if (Command.COMMAND_SESSION
							.equalsIgnoreCase(sessionType)) {
						System.out.println("Start Command Session...");
						write(Command.ACK, versClient);
						new Thread(new CommandSession(s, depuisClient,
								versClient)).start();

					} else {
						write(Command.NACK, versClient);
					}
				} catch (IOException e) {
					try {
						s.close();
					} catch (IOException ee) {
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur à la creation d'un objet Socket : "
					+ e.getMessage());
			System.exit(1);
		}
	}
	
	public static void main(String args[]) {

		Server s = new Server();
		
		// définition du port
		try {
			s.port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			s.port = 1234; // valeur par défaut
		}

//		ControllerStateManagement.registerControllerCommand(new LightCommand());
//		ControllerStateManagement.registerControllerDimensionCommand(new GatewayCommand());
		
		s.start();
	}

	private void write(String msg, PrintWriter versClient) {
		versClient.print(msg);
		versClient.flush();
		System.out.println("SERVER WRITE:[" + msg + "]");
	}

	private String read(Socket client, BufferedReader depuisClient) {
		int indice = 0;
		boolean exit = false;
		char respond[] = new char[1024];
		char c = ' ';
		int ci = 0;
		String responseString = null;

		try {
			do {
				if (client != null && !client.isInputShutdown()) {
					ci = depuisClient.read();
					if (ci == -1) {
						// System.out.println("End of read from socket.");
						// client = null;
						// break;
					} else {
						c = (char) ci;
						if (c == '#' && indice > 1
								&& '#' == respond[indice - 1]) {
							respond[indice] = c;
							exit = true;
							break;
						} else {
							respond[indice] = c;
							indice = indice + 1;
						}
					}
				}
			} while (true);
		} catch (IOException e) {
			System.out.println("Socket not available");
		}

		if (exit == true) {
			responseString = new String(respond, 0, indice + 1);
		}

		System.out.println("CLIENT WRITE: " + responseString);

		return responseString;
	}
}
