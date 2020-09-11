package core;

import profiles.Profile;
import profiles.ProfileManager;

import java.io.*;
import java.util.ArrayList;

public class SaveLoadManager {
	private ProfileManager profileManager;

	public SaveLoadManager(ProfileManager profileManager)
	{
		this.profileManager = profileManager;
	}

	public void saveAllProfiles()
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream("profile_test.profile");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(profileManager.getProfiles());
			out.close();
			fileOut.close();
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong when saving or loading!");
			e.printStackTrace();
		}
	}

	public void loadAllProfiles()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream("profile_test.profile");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			profileManager.setProfiles((ArrayList<Profile>)in.readObject());
			in.close();
			fileIn.close();
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong when saving or loading!");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Cannot convert from object to target class");
			e.printStackTrace();
		}
	}
}
