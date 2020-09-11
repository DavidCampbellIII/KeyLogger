package core;

import profiles.Profile;
import profiles.ProfileManager;

import java.util.Scanner;

public class Main {

	public Main()
	{
		run();
	}

	private void run()
	{
		ProfileManager profileManager = new ProfileManager();
		SaveLoadManager saveLoadManager = new SaveLoadManager(profileManager);
		saveLoadManager.loadAllProfiles();
		KeyHook keyHook = null; // = new KeyHook(profile.getKeyLog());

		Scanner input = new Scanner(System.in);
		int choice = 0;
		while (choice != 5)
		{
			displayMenu();
			choice = input.nextInt();
			switch (choice)
			{
				case 1:
					addProfile();
					break;
				case 2:
					selectProfile();
					break;
				case 3:
					startTracking();
					break;
				case 4:
					pauseTracking();
					break;
				case 5:
					System.out.println("Stopping tracking, flushing logs...");
					break;
				default:
					System.out.println("Not a valid option, please try again!");
					break;
			}
		}

		saveLoadManager.saveAllProfiles();
		keyHook.flushKeyLog();
	}

	private void displayMenu()
	{
		System.out.println("1. Add profile");
		System.out.println("2. Select profile");
		System.out.println("3. Start tracking");
		System.out.println("4. Pause tracking");
		System.out.println("5. Quit (stop tracking) and flush logs");
	}

	private void addProfile()
	{

	}

	private void selectProfile()
	{

	}

	private void startTracking()
	{

	}

	private void pauseTracking()
	{

	}

	public static void main(String[] args)
	{
		new Main();
	}
}
