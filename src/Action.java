public class Action {
	private int key;
	private KeyState state;
	private long timeStamp;

	public Action(int key, KeyState state)
	{
		this.key = key;
		this.state = state;
		timeStamp = System.nanoTime();
	}

	public int getKey()
	{
		return key;
	}

	public KeyState getState()
	{
		return state;
	}

	public long getTimeStamp()
	{
		return timeStamp;
	}
}
