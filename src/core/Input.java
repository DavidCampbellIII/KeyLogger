package core;

import java.util.Scanner;

public class Input {

	private static Scanner input = new Scanner(System.in);

	public static double validateDouble(String improperInputMessage)
	{
		do
		{
			try
			{
				double num = Double.parseDouble(input.nextLine().trim());
				return num;
			}
			catch (NumberFormatException e)
			{
				System.out.println(improperInputMessage);
			}
		}
		while(true);
	}

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
		do
		{
			try
			{
				int num = Integer.parseInt(input.nextLine().trim());
				for(int i = 0; i < choices.length; i++)
				{
					int current = choices[i];
					if(current == num)
					{
						return num;
					}
				}
				System.out.println(improperInputMessage);
			}
			catch (NumberFormatException e)
			{
				System.out.println(improperInputMessage);
			}
		}
		while(true);
	}

	//Inclusive ranges
	public static int validateInt(String improperInputMessage, int minRange, int maxRange)
	{
		do
		{
			try
			{
				int num = Integer.parseInt(input.nextLine().trim());
				if(num >= minRange && num <= maxRange)
				{
					return num;
				}
				System.out.println(improperInputMessage);
			}
			catch (NumberFormatException e)
			{
				System.out.println(improperInputMessage);
			}
		}
		while(true);
	}

	public static String validateString(String improperInputMessage, String[] choices)
	{
		do
		{
			String answer = input.nextLine().trim();
			for(int i = 0; i < choices.length; i++)
			{
				String current = choices[i];
				if(current.equals(answer))
				{
					return answer;
				}
			}
			System.out.println(improperInputMessage);
		}
		while(true);
	}

	public static String validateOneWord(String improperInputMessage)
	{
		do
		{
			String answer = input.nextLine().trim();
			if(!answer.contains(" "))
			{
				return answer;
			}
			System.out.println(improperInputMessage);
		}
		while(true);
	}

	public static String validateTwoWord(String improperInputMessage)
	{
		do
		{
			String answer = input.nextLine().trim();
			if(answer.contains(" ") && answer.indexOf(' ') == answer.lastIndexOf(' '))
			{
				return answer;
			}
			System.out.println(improperInputMessage);
		}
		while(true);
	}
}
