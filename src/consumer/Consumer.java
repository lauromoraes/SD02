package consumer;

import pipe.IPipe;

public class Consumer<TYPE> extends Thread
{
	protected IPipe<TYPE> buffer;
	
	public Consumer(IPipe<TYPE> buffer)
	{
		System.out.println("New Consumer Handler.");
		this.buffer = buffer;
	}
	
	public void execute(TYPE data)
	{
		System.out.println("Received:");
		System.out.println( data.toString() );
	}

	@Override
	public void run()
	{
		System.out.println("Starting Handle loop.");
		
		// Consumes all data in buffer while his still on work
		while( (!this.buffer.is_finished()) || (this.buffer.buffer_size() > 0) )
		{
			System.out.println("Handle trying to get data.");
			TYPE data = this.buffer.read_buffer();
			if(data != null)
			{
				this.execute(data);
			}
			else
			{
				System.out.println("Null data Received! Nothing to do.");
			}
		}
		System.out.println("Consumer Handler finished work.");
	}

}
