package core;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import profiles.KeyLog;

import java.util.Map;

public class KeyHook {
	private EventLine eventLine;
	private KeyLog keyLog;
	private GlobalKeyboardHook keyboardHook;
	private boolean isActive = false;

	public KeyHook() { }

	public KeyHook(KeyLog keyLog)
	{
		setKeyLog(keyLog);
		run();
	}

	public void setKeyLog(KeyLog keyLog)
	{
		this.keyLog = keyLog;
		eventLine = new EventLine(keyLog);
	}

	public void toggleTracking(boolean status)
	{
		isActive = status;
		if(status)
		{
			run();
		}
		else
		{
			keyboardHook.shutdownHook();
		}
	}

	private void run()
	{
		// Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
		keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of raw input

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");

		for (Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet())
		{
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}

		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyPressed(GlobalKeyEvent event)
			{
				//System.out.println("DOWN:\t" + event.getKeyChar());
				eventLine.addEvent(event, KeyState.KEY_DOWN);
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					toggleTracking(false);
				}
			}

			@Override
			public void keyReleased(GlobalKeyEvent event)
			{
				//System.out.println("UP:\t" + event.getKeyChar());
				eventLine.addEvent(event, KeyState.KEY_UP);
			}
		});

//		try
//		{
//			while (isActive)
//			{
//				Thread.sleep(128);
//			}
//		}
//		catch (InterruptedException e)
//		{
//			//Do nothing
//		}
//		finally
//		{
//			keyboardHook.shutdownHook();
//		}
	}

	public void flushKeyLog()
	{
		keyLog.writeActionsToFile();
		keyLog.writeEventsToFile();
	}

	public boolean getIsActive()
	{
		return isActive;
	}
}
