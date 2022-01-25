import java.io.File;
import java.io.*;
import java.util.*;

/**
 * This class takes in a filename and a date, and prints out the most active cookies from the file on the given date
 * Handles invalid inputs, improperly formatted files, and files that do not exist
 * @author Cary Lefteroff
 *
 */
public class CookieTracker {
    
	/**
	 * Takes in arguments, calls method to parse the file, and prints out results.
	 * Handles invalid inputs
	 * @param args command line arguments
	 */
    public static void main(String[] args) {
        String fileName;
        String date;
        try {
            fileName = args[0]; //Setup parseFile call
            date = args[1];
            File f = new File(fileName);
            ArrayList<String> arr = parseFile(f, date); //Make the call with the setup variables
            for(String s : arr) { //Print the results
                System.out.println(s);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Given a file and date, parses the file and generated the most active cookie for the given date
     * @param file the file being parsed
     * @param currDate the date being searched (YYYY-MM-DD format)
     * @return ArrayList with the most active cookies for the date
     * @throws FileNotFoundException
     */
    public static ArrayList<String> parseFile(File file, String currDate) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Map<String, Integer> map = new HashMap<>(); //HashMap counts # of instances of each cookie on the given date
        ArrayList<String> cookieNames = new ArrayList<>(); //Stores the names of the cookies on the date so we can iterate through them later
        try {
            while(scanner.hasNext()) { //Iterate through the file

                String curr = scanner.nextLine();
                String[] strArr = curr.split(",");
                String name = strArr[0]; //Isolate the cookie name portion of the string
                String[] dateArr = strArr[1].split("T");
                String date = dateArr[0]; //Isolate the date portion of the string
                if(date.equals(currDate)) {
                    if(map.containsKey(name)) { //If the cookie is already in the HashMap, iterate the counter
                        map.put(name, map.get(name) + 1);
                    } else { //If it isn't in the HashMap, add it and set its counter to 1. Add its name to the list of Cookie Names
                        cookieNames.add(name);
                        map.put(name, 1);
                    }
                }
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.println("File not formatted correctly");
        }
        ArrayList<String> retArr = new ArrayList<>();
        int maxVal = 0;
        for(String s : cookieNames) { //Iterate through the cookies for the date, tracking the # of instances per cookie
            if(map.get(s) == maxVal) { //If we have a tie, add it to the return ArrayList
                retArr.add(s);
            } else if(map.get(s) > maxVal) { //If we have a new max, clear the return ArrayList and add the new most active Cookie
                maxVal = map.get(s);
                retArr.clear();
                retArr.add(s);
            }
        }
        return retArr;
    }
}
