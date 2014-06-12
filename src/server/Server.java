package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import client.IClient;
import pipe.IPipe;
import pipe.Pipe;
import task.Task;
import worker.Worker;
import acceptor.Acceptor;
import acceptor.IAcceptor;


public class Server implements IServer {

	protected ConcurrentHashMap<Object, Object> location_map_tasks	= null; // k: client IP; v: task object
	
	protected ServerSocket peer_acceptor = null;
	protected IPipe<Socket> sock_stream_pipe = null;
	protected IPipe<String> tasks = null;
	protected IPipe<String> results = null;
	protected int port = 6969;
	protected boolean is_finished;
	
	protected IAcceptor acceptor = null;
	protected IClient client = null;
	protected LinkedList<Worker> workers = null;
	
	public Server() {
		
		this.setup_server_socket();
		this.init_acceptor();
		this.setup_sock_stream_pipe_pipe();
		this.is_finished = false;
	}

	@Override
	public void init_acceptor() {
		this.acceptor = new Acceptor(this.peer_acceptor, sock_stream_pipe);
	}

	@Override
	public void accept_connection() {
		/* Create new Thread Acceptor Handler to listening new requests on passive transport end point */
		((Acceptor) this.acceptor).start();
	}

	@Override
	public void run_service() {
		// TODO
	}

	@Override
	public void receive_request() {
		// TODO Auto-generated method stub
		
	}
	
	protected void setup_server_socket() {
		try {
			/* Create new passive transport end point */
			this.peer_acceptor = new ServerSocket(this.port);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void setup_sock_stream_pipe_pipe() {
		this.sock_stream_pipe = new Pipe<Socket>();
	}
	
	public void set_port(int port) {
		this.port = port;
	}
	
	public void setup_workers(int num_workers) {
		workers = new LinkedList<Worker>();
		for(int i=0; i<num_workers; i++) {
			Worker w = new Worker();
			workers.add(w);
		}
	}
	
	public void finish_all_workers() {
		for(Worker w : this.workers) {
			try {
				w.join();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
