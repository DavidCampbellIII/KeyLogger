package profiles;

import core.Action;
import core.Event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class KeyLog implements Serializable {
	private int profileID;
	private ArrayList<Action> actions;
	private ArrayList<Event> events;

	public KeyLog(int profileID)
	{
		this.profileID = profileID;
		actions = new ArrayList<>();
		events = new ArrayList<>();
	}

	public void addAction(Action action)
	{
		actions.add(action);
	}

	public void addEvent(Event event)
	{
		events.add(event);
	}

	public void writeActionsToFile()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(profileID + "_output_actions.txt"));
			out.write(toStringActions());
			out.flush();
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong when writing the file!");
			e.printStackTrace();
		}
	}

	public void writeEventsToFile()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(profileID + "_output_events.txt"));
			out.write(toStringEvents());
			out.flush();
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong when writing the file!");
			e.printStackTrace();
		}
	}

	public String toStringActions()
	{
		StringBuilder result = new StringBuilder();

		for(Action action : actions)
		{
			result.append("TIMESTAMP:");
			result.append(action.getDateTime());
			result.append("\t\tFROM START:");
			result.append(String.format("%10d", action.getFromStart()));
			result.append("\t");
			result.append(action.getKey().getKeyChar());
			result.append("\t");
			result.append(action.getState());
			result.append("\n");
		}

		return result.toString();
	}

	public String toStringEvents()
	{
		StringBuilder result = new StringBuilder();

		for(Event event : events)
		{
			result.append(event.getType().getKeyChar());
			result.append(" - ELAPSED TIME (DU):\t\t");
			result.append(event.getElaspedTimeStamp());
			result.append("ms\n");
		}

		return result.toString();
	}
}
