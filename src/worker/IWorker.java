package worker;

public interface IWorker<E, F> extends Runnable {
	public void select_task();
	public void give_result();
	public void execute();
	public boolean is_finished();
	public void set_finished();
	public void clear();
}
