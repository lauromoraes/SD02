package resource;

public interface Resource<TYPE> {
	public boolean put(TYPE item);
	public TYPE get();
	public Integer size();
	public void setFinished();
	public boolean isFinished();
}
