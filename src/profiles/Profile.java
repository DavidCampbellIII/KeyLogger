package profiles;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Profile implements Serializable {
	private int ID;
	private String firstName;
	private String lastName;
	private String role;
	private String notes;

	private LocalDateTime startDate;
	private KeyLog keyLog;
	private Statistics statistics;

	public Profile(int ID, String firstName, String lastName, String role)
	{
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.notes = "None";

		keyLog = new KeyLog(ID);
		statistics = new Statistics();
	}

	public String toString()
	{
		String result = "ID: " + ID;
		result += "\nFirst name: " + firstName;
		result += "\nLast name: " + lastName;
		result += "\nRole: " + role;
		return result;
	}

	public int getID()
	{
		return ID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getRole()
	{
		return role;
	}

	public String getNotes()
	{
		return notes;
	}

	public LocalDateTime getStartDate()
	{
		return startDate;
	}

	public KeyLog getKeyLog()
	{
		return keyLog;
	}

	public Statistics getStatistics()
	{
		return statistics;
	}
}
