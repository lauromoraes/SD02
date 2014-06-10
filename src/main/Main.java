package main;

import server.Server;

public class Main {

	public Main() {
	}
	
	public void server_app()
	{
		Server s = new Server();
		s.init_server(7070);
		s.listening();
		try {s.finalize();} catch (Throwable e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.server_app();
		System.out.println("END MAIN");
	}

}
