package pipe;

public interface IPipe<E> {
	public void write_buffer(E data);
	public E read_buffer();
	public boolean is_empty();
	public boolean is_finished();
	public void set_finished();
	public int buffer_size();
}
