package task;

import java.util.List;

public interface ITask extends Runnable  {
	public void execute(List<Object> param);
}
