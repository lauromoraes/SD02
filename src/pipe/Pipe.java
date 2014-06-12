package pipe;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Pipe<E> implements IPipe<E> {
	
	protected ConcurrentLinkedQueue<E> buffer;
	protected boolean finished;
	
	public Pipe() {
		finished = false;
		buffer = new ConcurrentLinkedQueue<E>();
	}

	@Override
	public synchronized void write_buffer(E data) {
		if(data != null) {
			buffer.offer(data);
			notify();
		} else {
			System.err.println("Null data: fail to insert on buffer.");
		}
	}

	@Override
	public synchronized E read_buffer() {
		E data = null;
		if(!is_empty()) {
			data = buffer.poll();
		} else {
			if(!is_finished()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	@Override
	public boolean is_empty() {
		return buffer.isEmpty();
	}

	@Override
	public boolean is_finished() {
		return finished;
	}

	@Override
	public synchronized void set_finished() {
		finished = true;
		notifyAll();
	}

	@Override
	public int buffer_size() {
		int t = buffer.size();
		return t;
	}

}
