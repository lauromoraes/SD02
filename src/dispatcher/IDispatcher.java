package dispatcher;

public interface IDispatcher {
	public long register_service();
	public long unregister_service();
	public long locate_server();
	public long establish_channel();
	public long get_channel();
}
