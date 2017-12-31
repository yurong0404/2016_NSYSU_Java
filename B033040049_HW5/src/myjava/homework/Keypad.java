package myjava.homework;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Keypad {
	
	private BufferedReader br;
	int input;		//save the input
	
	// initializes 
	public Keypad () {
		br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	/*
	 *  This function may throw some Exception if the user enters non-integer input.
	 *  You must use try...catch to deal with it.
	 */
	public int getInput() {
		this.input=0;
		try
		{
			this.input = Integer.parseInt(br.readLine());
		}
		catch(Exception e)
		{
			this.input = -1;		//if input wrong,let input = -1
		}
		return this.input;
	}
	
}
