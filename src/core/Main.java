package core;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

public class Main {
	private boolean run = true;
	private EventLine eventLine;

	public Main()
	{
		eventLine = new EventLine();
		run();
	}

	private void run()
	{
		// Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of raw input

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");

		for (Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet())
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
					run = false;
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
			while (run)
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

		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter("output_events.txt"));
			out.write(eventLine.toStringEvents());
			out.flush();
			out.close();

			out = new BufferedWriter(new FileWriter("output_actions.txt"));
			out.write(eventLine.toStringActions());
			out.flush();
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong when writing the file!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new Main();
	}
}
