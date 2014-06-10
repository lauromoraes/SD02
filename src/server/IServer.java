package server;

public interface IServer {
	public void accept_connection();
	public void run_service();
	public void receive_request();
}
