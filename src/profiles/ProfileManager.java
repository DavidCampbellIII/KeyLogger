package profiles;

import java.util.ArrayList;
import java.util.List;

public class ProfileManager {
	private List<Profile> profiles;

	public ProfileManager()
	{
		profiles = new ArrayList<>();
	}

	public void addProfile(Profile profile)
	{
		profiles.add(profile);
	}

	public Profile getProfile(int ID)
	{
		int index = getProfileIndex(ID);
		if(index == -1)
		{
			return null;
		}
		return profiles.get(index);
	}

	public Profile removeProfile(int ID)
	{
		int index = getProfileIndex(ID);
		if(index == -1)
		{
			return null;
		}
		return profiles.remove(index);
	}

	private int getProfileIndex(int ID)
	{
		Profile currentProfile;
		for(int i = 0; i < profiles.size(); i++)
		{
			currentProfile = profiles.get(i);
			if(currentProfile.getID() == ID)
			{
				return i;
			}
		}
		return -1;
	}

	public void displayAllProfiles()
	{
		if(profiles.size() == 0)
		{
			System.out.println("No profiles on record");
			return;
		}

		for(int i = 0; i < profiles.size(); i++)
		{
			System.out.println(profiles.get(i) + "\n");
		}
	}

	public List<Profile> getProfiles()
	{
		return profiles;
	}

	public void setProfiles(List<Profile> profiles)
	{
		this.profiles = profiles;
	}
}
