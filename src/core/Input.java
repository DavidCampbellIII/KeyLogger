package core;

import java.util.Scanner;

public class Input {
	//TODO add input validation, such as validateInt, validateOneWord, validateTwoWords etc.

	private static Scanner input = new Scanner(System.in);

	public static int validateInt(String improperInputMessage)
	{
		do
		{
			try
			{
				int num = Integer.parseInt(input.nextLine().trim());
				return num;
			}
			catch (NumberFormatException e)
			{
				System.out.println(improperInputMessage);
			}
		}
		while(true);
	}

	public static int validateInt(String improperInputMessage, int[] choices)
	{
		return 0; //TODO
	}

	public static int validateInt(String improperInputMessage, int minRange, int maxRange)
	{
		return 0; //TODO
	}

	public static String validateString(String improperInputMessage, String[] choices)
	{
		return null; //TODO
	}
}
