package core;

import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.time.LocalDateTime;

public class Action {
	private GlobalKeyEvent key;
	private KeyState state;
	private long fromStart;

	private long timeStamp;
	private LocalDateTime dateTime;

	public Action(GlobalKeyEvent key, KeyState state, long fromStart)
	{
		this.key = key;
		this.state = state;
		this.fromStart = fromStart;
		timeStamp = System.currentTimeMillis();
		dateTime = LocalDateTime.now();
	}

	public GlobalKeyEvent getKey()
	{
		return key;
	}

	public KeyState getState()
	{
		return state;
	}

	public long getFromStart()
	{
		return fromStart;
	}

	public long getTimeStamp()
	{
		return timeStamp;
	}

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
}
