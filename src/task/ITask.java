package task;

import java.util.LinkedList;

public interface ITask<E> {
	public Integer get_op_code();
	public LinkedList<Object> get_params();
	public E get_result();
	public void set_result(E result);
}
