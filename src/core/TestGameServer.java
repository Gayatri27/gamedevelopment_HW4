package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import configuration.GameServerConf;
import utility.ConfFileParser;

public class TestGameServer {

	public static void main(String[] args) {
		final String host = "127.0.0.1";
		
		GameServerConf configuration = new GameServerConf();
        ConfFileParser confFileParser = new ConfFileParser("conf/gameServer.conf");
        configuration.setConfRecords(confFileParser.parse());
        final int portNumber = configuration.getPortNumber();
        
		System.out.println("Creating socket to '" + host + "' on port " + portNumber);
		
		try {
			Socket socket = new Socket(host, portNumber);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("server says:" + br.readLine());

			out.println("ABC");

			System.out.println("server says:" + br.readLine());
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
