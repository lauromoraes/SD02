package worker;

import pipe.IPipe;
import pipe.Pipe;

/**
 * The Worker class is a Thread object that performs specifics jobs with Tasks stored on Server's pipes.
 * @param <E>
 * @param <F>
 * @class Worker
 */
public abstract class Worker<E, F> extends Thread implements IWorker<E, F> {
	
	protected IPipe<E> data_source;
	protected IPipe<F> data_sink;
	protected E active_job;
	protected F active_result;
	protected boolean is_finished;
	protected Integer op_code;
	
	public Worker(Pipe<E> data_source, Pipe<F> data_sink) {
		this.data_source = data_source;
		this.data_sink = data_sink;
		this.is_finished = false;
	}

	@Override
	public void select_task() {
		active_job = data_source.read_buffer();
	}
	
	@Override
	public void give_result() {
		data_sink.write_buffer(active_result);
	}

	@Override
	public abstract void execute();
	
	@Override
	public void run() {
		while(!is_finished) {
			select_task();
			execute();
			give_result();
		}
	}

	@Override
	public synchronized boolean is_finished() {
		return is_finished;
	}

	@Override
	public synchronized void set_finished() {
		is_finished = true;
	}
	
	@Override
	public abstract void clear();

}
