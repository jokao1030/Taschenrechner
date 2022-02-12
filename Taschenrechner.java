
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 /**
  * Calculator for set operations
  * Union, Intersection, Difference
  * 
  * @author Chin-Wen, Kao
  * @version 1.0
  * @since 18.06.2021
  */


class Taschenrechner{
	
	public static void main(String args[]) {
		System.out.println("Test First!!!\n");
		//Test 1(valid input): null set
		System.out.println("Test 1.(valid input: []-[]): difference two null sets");
		calculate("[]-[]");
		System.out.println("\n");
		//Test 2(valid input): union of two no-null sets
		System.out.println("Test 2.(valid input: [12,345,67,890,0,6]+[89,0,90,91,123445,12]): union of two no-null sets");
		calculate("[12,345,67,890,0,6]+[89,0,90,91,123445,12]");
		System.out.println("\n");
		//Test 3(invalid input): alphabetic
	    System.out.println("Test 3.(invalid input: [d,4,7,c,24]+[d,c]): alphabetic");
	    calculate("[d,4,7,c,24]+[d,c]");
		System.out.println("\n");
		//Test 4(invalid input): no valid operation character
		System.out.println("Test 4.(invalid input: [9,1,23,4,56,7,9000][2,909,89,71]): no valid operation character");
		calculate("[9,1,23,4,56,7,9000][2,909,89,71]");
		System.out.println("\n");
		//Test 5(invalid input): float number
		System.out.println("Test 5.(invalid input: [20,213,10,1111]+[0.3,700,564,89,250]): float number");
		calculate("[20,213,10,1111]+[0.3,700,564,89,250]");
		System.out.println("\n");
		//Test 6(invalid input): not separated with comma
		System.out.println("Test 6.(invalid input: [12,1]+[1 2]): not separated with comma");
		calculate("[12,1]+[1 2]");
		System.out.println("\n");
		
	    acceptInput();
		
	}
	
	/**
	 * This method is used to ask user to enter the sets with operation character,
	 * and call the method calculate to process user input
	 * It is called in main 
	 * It will read arbitrary many inputs until user enter an empty line
	 */
	public static void acceptInput() {
		while(true) {
		  System.out.println("Enter here, please form it like \"Set+/*/-Set\" , the elements in Sets should be splitted by commas: ");
		  Scanner scanner = new Scanner(System. in);
		  String input = scanner. nextLine();
          do{ 
             calculate(input);
             System.out.println("Enter here, please form it like \"Set+/*/-Set\" , the elements in Sets should be splitted by commas: ");
             input = scanner. nextLine();
          }while (!input.isEmpty()); 
          System.out.println("End of Calculation! Bye bye!");
          scanner.close();
          break;
        }
     }
	/**
	 * This method is for checking input from user: check if the elements are separated properly with comma
	 * It will be called and used in the method calculate to check the sets"
	 * @param input The set as String begin with [ close with ] as input that we want to check
	 * @return boolean If there is space and there are more than one element and there is no comma or too few comma return false, else is true
	 */
	private static boolean checkComma(String input) {
		boolean result = false;
		String temp1[] = input.split(" ");

		/*The empty set is valid*/
		if(temp1.length==1 || temp1[0].equals("[]")) {
			result = true;
		}
		if(temp1.length==2 && temp1[0].equals("[") && temp1[1].equals("]")) {
			result = true;
		}
		if(temp1.length>2 && temp1[0].equals("[")&&temp1[temp1.length-1].equals("]")) {
			for(int i=1; i<temp1.length; i++){ 
				  if(temp1[i].isEmpty()){
				   result = true;
				  }
			}
		}
		/*For the non-empty set*/
		input = removeSquareBracket(input);
		String temp2[] = input.split(",");
		int element = temp2.length;
		int comma = 0;
		if(input.length() > 1 && input.contains(",")) {
	
			for(int i = 0; i < input.length(); i++) {
			    if(input.charAt(i) == ',') 
			    	comma++;
			}
			

            if(comma==element-1) {
				result = true;
			}else {
				result = false;
			}
			   
		}else if(input.trim().equals(",")) {//when there is only a comma is also invalid
			result = false;
		}
		
		return result;
		
	}
	
	
	/**
	 * This method is for checking input from user: check if the element in the set is invalid
	 * It will be called and used in the method calculate to check the elements "in the set"
	 * @param String input The input we want to check
	 * @return boolean If there is invalid element it will be false, else is true
	 */
	private static boolean checkElement(String input) {
		
		//First check: there should not be any special characters "in the set", even - or . * + are not allowed, no matter on which places
	    //So we could check the entire String, no need to split to elements one by one yet
	    String specialCharacters = "-.!#$%&'()*+/:;<=>?@[]^_`{|}";
	    for(int i=0; i<specialCharacters.length(); i++){
		    
		    //Check if the input string contain any of the specified Characters
		    if(input.contains(Character.toString(specialCharacters.charAt(i)))){
		        
		        //System.out.println("Invalid input!");
		        return false;
		        
		    }
		}
	    
	    //Alphabetics are invalid
	    //Specify the pattern to all alphabetics and get matcher
	    Pattern patternAbc = Pattern.compile("[a-zA-Z]");
	    Matcher matcherAbc = patternAbc.matcher(input);
	    //Second check: there should not be any alphabetics in the set, no matter on which places
	    if(matcherAbc.find()) {
	    	//Invalid:alphabetic
	    	//System.out.println("Invalid input!");
	    	return false;
	    }
		
		//Third check: there should not be any number begin with 0, only except for 0, 
	    //so if its length is longer than 1 and begin with 0, it is not allowed
		String[ ] temp1 = input.split(",");
		
		for(String s: temp1) {
	    	if(s.length() > 1 && s.startsWith("0")) {
	    		//System.out.println("Invalid input");
	    		return false;
	    	}  	
		}
		return true;
    }
	
	/**
	 * This is the method for checking input from user: Check if the String begin with [ and end with ]
	 * It used in the method calculate to check if the set begin with [ and end with ]
	 * @param String input The input we want to check
	 * @return boolean If the input String begin with [ and end with ] return true, else false
	 */
	private static boolean checkBracket(String input) {
		if(input.startsWith("[")&&input.endsWith("]")) {
			return true;
		}else
			return false;
	}
	
	/**
	 * This is the method for our calculation
	 * It splits the entire user input String with + , * or - to two Strings first,
	 * and check the elements and forms with calling the methods checkComma, checkBracket and checkElement,
	 * and calls the removeSquareBracket method to reform the String,
	 * if the inputs are all valid, (if not will ask the user to reform)
	 * then build two String sets with these two Strings
	 * and finally show the result according to the operation
	 * @param String user input
	 */
	public static void calculate(String input) {
		
        //+ for detecting union operation
		//Specify the pattern to + and get matcher
		Pattern patternPlus = Pattern.compile("\\+");
	    Matcher matcherPlus = patternPlus.matcher(input);
	    //* for detecting intersect operation
	    //Specify the pattern to * and get matcher
	    Pattern patternStar = Pattern.compile("\\*");
	    Matcher matcherStar = patternStar.matcher(input);
	    //- for detecting difference operation
	    //Specify the pattern to - and get matcher
	    Pattern patternMinus = Pattern.compile("\\-");
	    Matcher matcherMinus = patternMinus.matcher(input);
	    
	    
	    //Check which operation character(+ , * , -) the String contains
	        //+ operation character found
	        if(matcherPlus.find()) {
	        	//Split the entire String to two Strings with the + operation character 
	        	String str1,str2;
	        	//Get the Index of +
	        	int plus = input.indexOf("+");
	        	//Get the String before + as str1 and after + as str2
	        	str1 = input.substring(0,plus);
	        	str2 = input.substring(plus+1,input.length());
	        	//We check if there is missing comma
	        	if(checkComma(str1) == true && checkComma(str2) == true) {
	        		//Get the String before + as str1 and after + as str2(with removing whitespace)
	        		str1 = input.substring(0,plus).replace(" ", "");
		        	str2 = input.substring(plus+1,input.length()).replace(" ", "");
		        	//Check if these two String all begin with [ and end with ] 
		        	if(checkBracket(str1) == true && checkBracket(str2) == true) {
		        	   //Remove the square bracket on begin and end
		               str1 = removeSquareBracket(str1);
		        	   str2 = removeSquareBracket(str2);
		        	   //Check if there is invalid element in the String, if there is no invalid element, 
		        	   //build two sets with these two String
		               if(checkElement(str1) == true && checkElement(str2) == true) {
					        Set<String> outputset1 = convertToSet(str1);
					        Set<String> outputset2 = convertToSet(str2);
					        
					        //The union set will show on the console
					        System.out.println("Result from union: " + union(outputset1,outputset2));
					     }else {
					    	//If there is invalid element
						    System.out.println("Please reform and try it again!");
					     }
		            }else {
		            	//If there is String not begin with [ and end with ] correctly
		            	System.out.println("Please reform and try it again!");
		            }
	        	}else {
	        		//If there is missing comma 
	        		System.out.println("Comma or space problem?!");
	        	}
	        
	         //* operation character found   
	        }else if(matcherStar.find()){
	        	String str1,str2;
	        	//Get the Index of *
	        	int star = input.indexOf("*");
	        	//Get the String before * as str1 and after * as str2
	        	str1 = input.substring(0,star);
	        	str2 = input.substring(star+1,input.length());
	        	//We check if there is missing comma
	        	if(checkComma(str1) == true && checkComma(str2) == true) {
	        	   //Get the String before * as str1 and after * as str2(with removing whitespace)
	        	   str1 = input.substring(0,star).replace(" ", "");
	        	   str2 = input.substring(star+1,input.length()).replace(" ", "");
	        	   //Check if these two String all begin with [ and end with ]
	        	   if(checkBracket(str1) == true && checkBracket(str2) == true) {
		        	  //Remove the square bracket
	        	      str1 = removeSquareBracket(str1);
	        	      str2 = removeSquareBracket(str2);
	        	      //Check if there is invalid element in the String, if there is no invalid element, 
		        	  //build two sets with these two String
	        	      if(checkElement(str1) == true && checkElement(str2) == true) {
				         Set<String> outputset1 = convertToSet(str1);
				         Set<String> outputset2 = convertToSet(str2);
				         //The intersection set will show on the console
				         System.out.println("Result from intersect: " + intersect(outputset1,outputset2));
				      }else {
					     //If there is invalid element
					     System.out.println("Please reform and try it again!");
				      }
	        	   }else {
	        	   	  //If there is String not begin with [ and end with ] correctly
	            	  System.out.println("Please reform and try it again!");
	               }
	        	}else {
	        		//If there is missing comma
	        		System.out.println("Comma or space problem?!");
	        	}
	        	    
	          //- operation character found
			 }else if(matcherMinus.find()) {
				String str1,str2;
				//Get the Index of -
		    	int minus = input.indexOf("-");
		    	//Get the String before - as str1 and after - as str2
	        	str1 = input.substring(0,minus);
	        	str2 = input.substring(minus+1,input.length());
	        	//We check if there is missing comma
	        	if(checkComma(str1) == true && checkComma(str2) == true) {
		    	   //Get the String before - as str1 and after - as str2(with removing whitespace)
	        	   str1 = input.substring(0,minus).replace(" ", "");
	        	   str2 = input.substring(minus+1,input.length()).replace(" ", "");
	        	   //Check if these two String all begin with [ and end with ]
	        	   if(checkBracket(str1) == true && checkBracket(str2) == true) {
		        	  //Remove the square bracket
	        	      str1 = removeSquareBracket(str1); 
	        	      str2 = removeSquareBracket(str2);
	        	      //Check if there is invalid element in the String, if there is no invalid element, 
		        	  //build two sets with these two String
	        	      if(checkElement(str1) == true && checkElement(str2) == true) {
				         Set<String> outputset1 = convertToSet(str1);
				         Set<String> outputset2 = convertToSet(str2);
				         //The difference set will show on the console
				         System.out.println("Result from minus: " + minus(outputset1,outputset2));
				      }else {
				         //If there is invalid element
					     System.out.println("Please reform and try it again!");
				      }
	        	  }else {
	        	      //If there is String not begin with [ and end with ] correctly
	        		  System.out.println("Please reform and try it again!");
	        	  }
	        	}else {
	        		//If there is missing comma
	        		System.out.println("Comma or space problem?!");
	            }
				
		      }else {
		    	  //If there is no valid operation character found
		    	  System.out.println("There is no valid operation character!");}
		      
		
	}
	
	/**
	 * This is the method for helping to build String set, it is used in the method calculate
	 * @param String Input begin with [ and end with ]
	 * @return String A String without square bracket on begin and end
	 * */
	private static String removeSquareBracket(String input) {
		//Remove [ and ] with replacing all [ and ] with ""
		String str = input;
		str = str.replaceAll("\\[","");
		str = str.replaceAll("\\]","");
		return str;
	}
	
	/**
	 * This is the method for building String set from String: Transfer a String separated by comma to a Hashset of String and return it
	 * @param String The String we want to build as a Set
	 * @return Set of String
	 * */
	private static Set<String> convertToSet (String input){
		Set<String> setOfString = new HashSet<String>();
		//Store firstly every String element to an array
		String[ ] temp = input.split(",");
		//Add all String elements from the temp array to the set
		for(String s: temp) {
		setOfString.add(s);}
		return setOfString;
	}
	
	
	/**
	 * This is the method for the operation of two sets: union
	 * @param two sets setA and setB
	 * @return a set as result of the operation
	 **/
	private static <T> Set<T> union(Set<T> setA, Set<T> setB) {
		//First set as Hashset
		Set<T> set = new HashSet<T>(setA);
		//Add all elements from another set to our Hashset using addAll() method
		//(Hashset doesn't allow duplicated elements, so we don't need to worry about it here!)
		set.addAll(setB);
		return set;
	}
	/**
	 * This is the method for the operation of two sets: intersection
	 * @param two sets setA and setB
	 * @return a set as result of the operation
	 **/
	private static <T> Set<T> intersect(Set<T> setA, Set<T> setB) {
		//First set as Hashset
		Set<T> set = new HashSet<T>(setA);
		//Remove from this Hashset all of its elements that are not contained in the other set
		set.retainAll(setB);
		return set;
	}
	/**
	 * This is method for the operation of two sets: difference
	 * @param two sets setA and setB
	 * @return a set as result of the operation
	 **/
	private static <T> Set<T> minus(Set<T> setA, Set<T> setB) {
		//First set as Hashset
		Set<T> set = new HashSet<T>(setA);
		//Remove from this Hashset all elements that are also contained in the other set
		set.removeAll(setB);
		return set;
	}
	
}