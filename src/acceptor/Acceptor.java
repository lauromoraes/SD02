package acceptor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pipe.IPipe;

public class Acceptor extends Thread implements IAcceptor {
	
	protected ServerSocket peer_acceptor;
	protected IPipe<Socket> hosts_endpoints_pipe;
	protected boolean is_accepting;
	
	public Acceptor() {}
	
	public Acceptor(ServerSocket peer_acceptor, IPipe<Socket> hosts_endpoints_pipe) {
		/* Define a passive mode socket end point factory */
		this.peer_acceptor = peer_acceptor;
		
		/* Define a Pipe to store temporary end point data on buffer */
		this.hosts_endpoints_pipe = hosts_endpoints_pipe;
		
		/* Set state of Acceptor to accepting new requests */
		this.is_accepting = true;
	}

	@Override
	public void accept() {
		try {
			/* Accept one new request and create new socket end point */
			Socket s = peer_acceptor.accept();
			/* Put created socket end point on buffer */
			hosts_endpoints_pipe.write_buffer(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle_event() {
		/* While Pipe is defined as accepting, continues to listening and factoring */
		while(is_accepting) {
			this.accept();
		}
	}
	
	@Override
	public void run() {
		this.handle_event();
	}
	

}
