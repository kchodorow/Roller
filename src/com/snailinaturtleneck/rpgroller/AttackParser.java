package com.snailinaturtleneck.rpgroller;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AttackParser {
	/**
	 * Parses EditText number of dice into an int.  Only works for strings
	 * consisting of 1-4 characters.  If an invalid string is passed in,
	 * this defaults to 1.
	 * 
	 * TODO: log a warning on invalid input
	 */
	public static int parseNumDice(View view) {
		String numAsString = ((EditText)view).getText().toString();
		return parse(numAsString, 4, 1);
	}
	
	/**
	 * Parses Spinner into dice size.
	 */
	public static int parseSize(View view) {
		Spinner spinner = (Spinner)view;
		return DieRoller.getD(spinner.getSelectedItemPosition());
	}
	
	/**
	 * Return the bonus for a roll.
	 */
	public static int parseBonus(View view) {
		String numAsString = ((EditText)view).getText().toString();
		return parse(numAsString, 6, 0);
	}
	
	/**
	 * Parses a string into an int.
	 * @param numAsString The string to parse.
	 * @param max The maximum length for the int in chars.
	 * @param fallback The value to return if something goes wrong.
	 * @return The string's int value, or the default if parsing failed.
	 */
	private static int parse(String numAsString, int max, int fallback) {
		if (numAsString.isEmpty() || numAsString.length() > max) {
			return fallback;
		}
		
		int total = 0;
		for (int i=0; i<numAsString.length(); i++) {
			if (numAsString.charAt(i) < '0' ||  numAsString.charAt(i) > '9') {
				return fallback;
			}
			total *= 10;
			total += (numAsString.charAt(i)-'0');
		}
		
		return total;
	}
}
