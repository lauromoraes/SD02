package acceptor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import resource.Resource;

public class Acceptor implements IAcceptor {
	
	protected ServerSocket peer_acceptor;
	protected Resource<Socket> connections;
	protected boolean is_accepting;
	
	public Acceptor() {}
	
	public Acceptor(ServerSocket peer_acceptor, Resource<Socket> connections) {
		// Recebe um ServerSocket e o define como Handle Transport Endpoint
		this.peer_acceptor = peer_acceptor;
		
		// Recebe um Resource de Sockets onde ira adicionar as conexoes que chegaram
		this.connections = connections;
		
		this.is_accepting = true;
	}

	@Override
	public void accept() {
		// TODO Auto-generated method stub
		try {
			Socket s = this.peer_acceptor.accept();
			this.connections.put(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle_event() {
		// TODO Auto-generated method stub
		while(is_accepting) {
			this.accept();
		}
	}

}
