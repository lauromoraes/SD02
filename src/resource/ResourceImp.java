package resource;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ResourceImp<TYPE> implements Resource<TYPE> {
	
	protected ConcurrentLinkedQueue<TYPE> buffer;
	protected boolean is_finished;

	public ResourceImp() {
		this.is_finished	= false;
		this.buffer			= new ConcurrentLinkedQueue<TYPE>();
	}

	@Override
	public boolean put(TYPE item) {
		boolean aux	= false;
		
		if(item != null)
		{
			synchronized (this)
			{
				aux = this.buffer.offer(item);
				this.notify();
			}
		}
		else
		{
			System.out.println("Can't insert null item.");
		}
		
		return aux;
	}

	@Override
	public TYPE get() {
		if(!this.buffer.isEmpty())
		{
			return this.buffer.poll();
		}
		else
		{
			if(this.is_finished == false)
			{
				try
				{
					synchronized (this)
					{
						this.wait();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public Integer size() {
		return this.buffer.size();
	}

	@Override
	public synchronized void setFinished() {
		this.is_finished = true;
		this.notifyAll();
	}

	@Override
	public boolean isFinished() {
		return this.is_finished;
	}
}
