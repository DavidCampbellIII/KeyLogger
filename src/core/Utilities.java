package core;

public class Utilities {

	public static void pauseThread(long milliseconds)
	{
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
