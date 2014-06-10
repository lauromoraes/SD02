package backup;

public class Server01 {

	public Server01() {
		
	}
	
//	{
//
//		protected ServerSocket		serverSocket;
//		protected Socket			socket;
//		protected boolean			is_finished;
//		
//		protected Resource<Socket>	sockets_buffer;
//		protected Resource<String>	tasks_buffer;
//		
//		protected Consumer<Socket>	sockets_handles;
//		
//		protected ConcurrentHashMap<InetSocketAddress, LinkedList<InetSocketAddress>> hosts_map;
//		
//		public Server()
//		{
//			this.is_finished = false;
//			this.set_socket_buffer();
//			this.set_tasks_buffer();
//			
//			this.sockets_handles = new Consumer<Socket>(this.sockets_buffer);
//		}
//		
//		@Override
//		public void finalize() throws Throwable {
//			super.finalize();
//			System.out.println("Server has being destroied.");
//			
//			// Close all sockets connections on buffer
//			while(this.sockets_buffer.size() > 0)
//			{
//				Socket s = this.sockets_buffer.get();
//				if(!s.isClosed())
//				{
//					System.out.println("Closing Socket " + s.getRemoteSocketAddress().toString());
//					s.close();
//				}
//			}
//			
//			// Checks buffer to finish
//			this.sockets_buffer.setFinished();
//			
//			// Synchronizes handles
//			this.sockets_handles.join();
//		}
//		
//		public void set_socket_buffer()
//		{
//			this.sockets_buffer = new ResourceImp<Socket>();
//		}
//		
//		public void set_tasks_buffer()
//		{
//			this.tasks_buffer = new ResourceImp<String>();
//		}
//		
//		public void init_server(Integer port)
//		{
//			try
//			{
//				this.serverSocket		= new ServerSocket();
//				InetSocketAddress addr	= new InetSocketAddress(port);
//				
//				this.serverSocket.bind( addr );
//				
//				
//				this.sockets_handles.start();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		public void listening()
//		{
//			System.out.println("Start to listening loop.");
//			
//			while(!this.is_finished)
//			{
//				try
//				{
//						System.out.println("Listening...");
//						
//						// Defines timeout to accept any connection
//						this.serverSocket.setSoTimeout(5000);
//						
//						// Blocks to receive any connection
//						Socket s = this.serverSocket.accept();
//						System.out.println("Add " + s.getRemoteSocketAddress().toString() );
//						
//						// Add income socket connection to buffer
//						this.sockets_buffer.put(s);
//				}
//				// TIMEOUT Exception Handler
//				catch(SocketTimeoutException e)
//				{
//					System.out.println("Timeout reached because any connections arrived in time.");
//					this.is_finished = true;
//				}
//				catch (IOException e)
//				{
//					System.out.println("ERROR 1 " + e.getClass().toString() );
//					e.printStackTrace();
//				}
//				finally
//				{
//					// Close server socket if server has to end
//					if(this.is_finished && this.serverSocket != null)
//					{
//						try
//						{
//							System.out.println("Ending listening loop.");
//							this.serverSocket.close();
//							break;
//						}
//						catch (IOException e)
//						{
//							System.out.println("ERROR 2 " + e.getClass().toString() );
//							e.printStackTrace();
//							break;
//						}
//					}
//					// Return to listening
//					else if(!this.is_finished)
//					{
//						System.out.println("Continue to listening.");
//						this.listening();
//					}
//				}
//			}
//		}
//
//	}
}
