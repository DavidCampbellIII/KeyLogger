package core;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import profiles.KeyLog;

import java.util.Map;

public class KeyHook {
	private EventLine eventLine;
	private KeyLog keyLog;
	private boolean isActive = true;

	public KeyHook(KeyLog keyLog)
	{
		this.keyLog = keyLog;
		eventLine = new EventLine(keyLog);
		run();
	}

	private void run()
	{
		// Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of raw input

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");

		for (Map.Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet())
		{
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}

		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyPressed(GlobalKeyEvent event)
			{
				System.out.println("DOWN:\t" + event.getKeyChar());
				eventLine.addEvent(event, KeyState.KEY_DOWN);
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					isActive = false;
				}
			}

			@Override
			public void keyReleased(GlobalKeyEvent event)
			{
				System.out.println("UP:\t" + event.getKeyChar());
				eventLine.addEvent(event, KeyState.KEY_UP);
			}
		});

		try
		{
			while (isActive)
			{
				Thread.sleep(128);
			}
		}
		catch (InterruptedException e)
		{
			//Do nothing
		}
		finally
		{
			keyboardHook.shutdownHook();
		}
	}

	public void flushKeyLog()
	{
		keyLog.writeActionsToFile();
		keyLog.writeEventsToFile();
	}
}
