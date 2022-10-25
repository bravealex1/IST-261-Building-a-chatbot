/*
 * SlotFiller.java is added for Assignment 4 (Language Understanding)
 */

package chatbot.component;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlotFiller {

	/*
	 * Task 1: Extract slot values from the input user message
	 * 
	 * [Input] 
	 * One user message (e.g., "What's the weather in State College?")
	 * 
	 * [Output]
	 * A hash table that contains a set of (key, value) tuples, where the "key"
	 * is the name of the slot (e.g., "location") and "value" is the extracted
	 * value (e.g., "State College").
	 * 
	 */
	public Hashtable<String, String> extractSlotValues(String nowInputText) {
		
		//initialize the hash table. You do not need to change this line of code.
		Hashtable<String, String> result = new Hashtable<String, String>();
		
		//-------------- Modify Code Here (Assignment 4) Begins ---------------
		
		//modify the following code to implement your own slot extractor
		
		String[] dayOfWeekList = new String[] {"FRIDAY", "MONDAY", "SATURDAY", "SUNDAY", "THURSDAY", "TUESDAY", "WEDNESDAY"};
		for(String nowDayOfWeek: dayOfWeekList) {
			if(nowInputText.toUpperCase().contains(nowDayOfWeek)) {
				//adding value to the result hash table
				result.put("DayOfWeek", nowDayOfWeek);
			}
		}
		
		
		//modify the following code to implement your own slot extractor
		
		String[] relativeDateList = new String[] {"TODAY", "TOMORROW", "YESTERDAY"};
		for(String nowRelativeDate: relativeDateList) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeDate", nowRelativeDate);
			}
		}
		
		
		//Demo 1: Match key phrases with multiple words by normalizing input strings 
		//Tips: Tokenize and reconstruct the sentence using a "standard" way
		//Error Case: This example incorrect returns "LA" if you mentioned "LAs Vegas"
//		String[] nowTokens = nowInputText.trim().toUpperCase().split("[\\s]+");
//		String normalizedStr = "";
//		for(String nowToken: nowTokens) {
//			normalizedStr+=nowToken+" ";//add one space
//		}
//		normalizedStr = normalizedStr.trim();
//		System.out.println("normalized input (Demo 1):"+normalizedStr);
//		String[] locationList = new String[] {
//			"LA", 
//			"STATE COLLEGE",
//			"NYC" 
//		};
//		for(String nowLocation: locationList) {
//			if(normalizedStr.toUpperCase().contains(nowLocation)) {
//				//adding value to the result hash table
//				result.put("Location", nowLocation);
//			}
//		}
		
		
		//Demo 2: Match key phrases with multiple words using double for loop
		
		String[] nowInputWords = nowInputText.trim().toUpperCase().split("[\\s]+");
		String[] locationList1 = new String[] {
			"LA", 
			"NEW YORK CITY",
			"STATE COLLEGE",
			"NYC" 
		};
		for(String nowLocation: locationList1) {
			String[] nowLocationWords = nowLocation.trim().toUpperCase().split("[\\s]+");
			//findPhrase() is our own method, see below
			if(findPhrase(nowLocationWords, nowInputWords)){
				//adding value to the result hash table
				result.put("Location", nowLocation);
			}
		}
		
		
		//Demo 3: Extract substring using regular expression
		 
		String regexPattern = "\\b((?:0?[1-9]|1[0-2])(?!\\d| (?![ap]))[:.]?(?:(?:[0-5][0-9]))?(?:\\s?[ap]m)?)\\b";
		Pattern nowPattern = Pattern.compile(regexPattern);
	    Matcher nowMatcher = nowPattern.matcher(nowInputText.trim().toLowerCase());
	    while (nowMatcher.find()) {
	    	String nowMatchedSubstring = nowMatcher.group();
	    	System.out.println("The time is: " + nowMatchedSubstring);
	    	//adding value to the result hash table
			result.put("Time", nowMatchedSubstring);
	  	}
		
//		String[] nowInputTime = nowInputText.trim().toUpperCase().split("[\\s]+");
//		String[] timeList = new String[] {
//			"2 am", 
//			"3 pm",
//			"5 am",
//			"7 pm" 
//		};
//		for(String nowTime: timeList) {
//			String[] nowTimeWords = nowTime.trim().toUpperCase().split("[\\s]+");
//			//findPhrase() is our own method, see below
//			if(findPhrase(nowTimeWords, nowInputTime)){
//				//adding value to the result hash table
//				result.put("Time", nowTime);
//			}
//		}
		
	  	
		
		//-------------- Modify Code Here (Assignment 4) Ends ---------------
		
		//return the result hash table. You do not need to change this part of code.
		return result;
		
	}

	/*
	 * Code added for Demo 2
	 * Return true if nowLocationWords can be found in nowInputWords
	 * Tips: Double-layer loop
	 */
	private boolean findPhrase(String[] nowLocationWords, String[] nowInputWords) {
		
		//iterate through each word in the sentence
		for(int i=0;i<nowInputWords.length;i++) {
			//allWordsMatchStartsWith() is our own method, see below
			if(allWordsMatchStartsWith(nowLocationWords, nowInputWords, i)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Code added for Demo 2
	 * Return true if all the words in nowLocationWords match with nowInputWords, starting in index
	 */
	private boolean allWordsMatchStartsWith(String[] nowLocationWords, String[] nowInputWords, int index) {
		// TODO Auto-generated method stub
		for(int i=0;i<nowLocationWords.length;i++) {
			if(!nowLocationWords[i].equals(nowInputWords[index+i])) {
				return false;
			}
		}
		return true;
	}

}


