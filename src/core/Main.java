package core;

import profiles.Profile;
import profiles.ProfileManager;

import java.util.Scanner;

public class Main {
	private Scanner input;
	private ProfileManager profileManager;
	private SaveLoadManager saveLoadManager;
	private KeyHook keyHook;

	public Main()
	{
		input = new Scanner(System.in);
		profileManager = new ProfileManager();
		saveLoadManager = new SaveLoadManager(profileManager);
		keyHook = new KeyHook();
		run();
	}

	private void run()
	{
		saveLoadManager.loadAllProfiles();
		int choice = 0;
		while (choice != 5)
		{
			displayMenu();
			choice = Input.validateInt("Please enter a valid choice");
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
		System.out.println("===KEY LOGGER V0.00.001===");
		String trackingString = keyHook.getIsActive() ? "Tracking" : "Not tracking";
		System.out.print("Currently tracked profile (" + trackingString + "): ");
		Profile currentProfile = profileManager.getCurrentlyTrackedProfile();
		if(currentProfile == null)
		{
			System.out.println("None");
		}
		else
		{
			System.out.println(currentProfile.getShortInfo());
		}
		System.out.println("1. Add profile");
		System.out.println("2. Select profile");
		System.out.println("3. Start tracking");
		System.out.println("4. Pause tracking");
		System.out.println("5. Quit (stop tracking) and flush logs");
	}

	private void addProfile()
	{
		input.nextLine(); //clearing buffer
		System.out.println("What is the first name of the person you're creating a profile for?");
		String firstName = input.nextLine();

		System.out.println("What is the last name of the person you're creating a profile for?");
		String lastName = input.nextLine();

		System.out.println("What is the ID of this person?");
		int ID = input.nextInt();

		input.nextLine(); //clearing buffer
		System.out.println("What is their role?");
		String role = input.nextLine();

		System.out.println("Thank you!  Profile has been created!");
		profileManager.addProfile(new Profile(ID, firstName, lastName, role));
	}

	private void selectProfile()
	{
		Profile currentlyTrackedProfile = profileManager.getCurrentlyTrackedProfile();
		if(currentlyTrackedProfile != null)
		{
			System.out.println("You are currently tracking " + currentlyTrackedProfile.getShortInfo());
			System.out.println("Do you want to stop tracking this profile, and choose a different profile to track? (Y/N)");
			if(input.next().toLowerCase().charAt(0) == 'n')
			{
				return;
			}

			keyHook.toggleTracking(false);
		}

		System.out.println("==AVAILABLE PROFILES TO TRACK==");
		if(profileManager.displayAllProfiles())
		{
			System.out.println("Please select the ID of the profile you would like to track");
			int ID;
			Profile profile;
			do
			{
				ID = Input.validateInt("Please enter a valid choice");
				profile = profileManager.getProfile(ID);
				if(profile == null)
				{
					System.out.println("A profile with that ID does not exist!  Please try again!");
				}
			}
			while(profile == null);

			profileManager.setCurrentlyTrackedProfile(profile);
			System.out.println("Success!  Currently tracking " + profile.getShortInfo() + "\n");
		}
	}

	private void startTracking()
	{
		Profile currentProfile = profileManager.getCurrentlyTrackedProfile();
		if(currentProfile == null)
		{
			System.out.println("No profile selected!  Cannot start tracking!\n");
			return;
		}
		else if(keyHook.getIsActive())
		{
			System.out.println("Already tracking!\n");
			return;
		}

		System.out.println("Tracking has started for " + currentProfile.getShortInfo() + "\n");
		keyHook.setKeyLog(currentProfile.getKeyLog());
		keyHook.toggleTracking(true);
	}

	private void pauseTracking()
	{
		if(!keyHook.getIsActive())
		{
			System.out.println("You need to start tracking before you pause tracking!\n");
			return;
		}
		System.out.println("Tracking has paused for " + profileManager.getCurrentlyTrackedProfile().getShortInfo() + "\n");
		keyHook.toggleTracking(false);
	}

	public static void main(String[] args)
	{
		new Main();
	}
}
