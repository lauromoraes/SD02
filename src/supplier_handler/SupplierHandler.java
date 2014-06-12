package supplier_handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;

import pipe.Pipe;
import task.ITask;
import task.Task;
import worker.Worker;

@SuppressWarnings("rawtypes")
public class SupplierHandler extends Worker<Socket, ITask> implements ISupplierHandler {

	protected ObjectInputStream input_stream;
	protected LinkedList<Object> data_pack;
	
	public SupplierHandler(Pipe<Socket> data_source, Pipe<ITask> data_sink) {
		super(data_source, data_sink);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void execute() {
		try {
			
			/* Clear previously data */
			clear();
			
			/* Generate new Input Stream from current socket */
			input_stream = new ObjectInputStream( active_job.getInputStream() );

			/* Read data object from transport end point */
			data_pack = (LinkedList<Object>) input_stream.readObject();
			
			/* Create new Task from received object */
			ITask task = new Task(data_pack);
			
			/* Define new task result */
			active_result = task;
			
			/* Close transport end point */
			input_stream.close();
			active_job.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		input_stream = null;
		data_pack = null;
	}
	
}
