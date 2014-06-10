package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import resource.Resource;
import acceptor.Acceptor;
import acceptor.IAcceptor;


public class Server implements IServer {
	
	protected ConcurrentHashMap<Object, Object> location_map_hosts; // 
	protected ConcurrentHashMap<Object, Object> location_map_tasks; // k: client IP; v: task object
	
	protected ServerSocket peer_acceptor;
	protected Resource<Socket> connections;
	
	protected IAcceptor acceptor;
	
	public Server() {
		this.acceptor = new Acceptor(peer_acceptor, connections);
	}

	@Override
	public void accept_connection() {
		// TODO Auto-generated method stub
		acceptor.accept();
	}

	@Override
	public void run_service() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receive_request() {
		// TODO Auto-generated method stub
		
	}
}
