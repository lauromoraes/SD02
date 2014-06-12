package info;

public class Info implements IInfo {

	protected Object o;
	
	
	public Info(Object o) {
		this.o = o;
	}
	
	@Override
	public void print_info() {
		String	info_class		= o.getClass().toString();
		String	info_object		= o.toString();
		long	info_thread_id	= Thread.currentThread().getId();
		long 	info_timestamp	= System.nanoTime();

		String msg = "";
		msg += "{\n";
		msg += "\tClass : {"		+info_class		+"};\n";
		msg += "\tObject : {"		+info_object	+"};\n";
		msg += "\tThreadI : {"		+info_thread_id	+"};\n";
		msg += "\nTimestamp : {"	+info_timestamp	+"};\n";
		msg += "}\n";

		System.out.println(msg);
	}
	
	@Override
	public String get_info() {
		String	info_class		= o.getClass().toString();
		String	info_object		= o.toString();
		long	info_thread_id	= Thread.currentThread().getId();
		long 	info_timestamp	= System.nanoTime();

		String msg = "";
		msg += "{\n";
		msg += "\tClass : {"		+info_class		+"};\n";
		msg += "\tObject : {"		+info_object	+"};\n";
		msg += "\tThreadI : {"		+info_thread_id	+"};\n";
		msg += "\nTimestamp : {"	+info_timestamp	+"};\n";
		msg += "}\n";

		return msg;
	}
}
