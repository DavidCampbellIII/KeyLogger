package core;

import profiles.Profile;
import profiles.ProfileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveLoadManager {
	private final String FILE_PATH = "profileSaveData/";
	private ProfileManager profileManager;

	public SaveLoadManager(ProfileManager profileManager)
	{
		this.profileManager = profileManager;
	}

	public void saveAllProfiles()
	{
		try
		{
			List<Profile> profiles = profileManager.getProfiles();
			for(int i = 0; i < profiles.size(); i++)
			{
				Profile currentProfile = profiles.get(i);
				FileOutputStream fileOut = new FileOutputStream(FILE_PATH + currentProfile.getID() + ".profile");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(currentProfile);
				out.close();
				fileOut.close();
			}
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
			File root = new File(FILE_PATH);
			if(root.listFiles().length == 0)
			{
				System.out.println("No profiles to load from root save directory " + FILE_PATH);
				return;
			}

			ArrayList<Profile> profiles = new ArrayList<>();
			for(File currentFile : root.listFiles())
			{
				FileInputStream fileIn = new FileInputStream(currentFile);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				profiles.add((Profile) in.readObject());
				in.close();
				fileIn.close();
			}
			profileManager.setProfiles(profiles);
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
