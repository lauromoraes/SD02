package acceptor;

public interface IAcceptor extends Runnable {
	public void accept();
	public void handle_event();
	
}
