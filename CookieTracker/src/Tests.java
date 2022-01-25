import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.*;

import org.junit.Test;

public class Tests {
    
	/**
	 * Tests whether the CookieTracker generates the correct output with a valid file
	 * Also tests whether the CookieTracker runs properly without exceptions given valid inputs
	 * @throws FileNotFoundException
	 */
    @Test
    public void testGivenFile() throws FileNotFoundException {
        CookieTracker tracker = new CookieTracker();
        String[] simArgs = {"testFile.txt", "2018-12-09"};
        tracker.main(simArgs);
        File f = new File("testFile.txt");
        ArrayList<String> list = CookieTracker.parseFile(f, "2018-12-09");
        assertEquals(list.get(0), "AtY0laUfhglK3lC7");
    }
    
    /**
     * Tests whether the CookieTracker generates the correct output when there are multiple most active cookies
     * @throws FileNotFoundException 
     */
    @Test
    public void testMultipleActiveCookies() throws FileNotFoundException {
    	CookieTracker tracker = new CookieTracker();
    	File f = new File("testFile2.txt");
    	ArrayList<String> list = CookieTracker.parseFile(f, "2018-12-09");
    	assertTrue(list.contains("AtY0laUfhglK3lC7"));
    	assertTrue(list.contains("SAZuXPGUrfbcn5UA"));
    }

    /**
     * Tests whether the CookieTracker properly handles a file that is not properly formatted
     */
    @Test
    public void testInvalidFormat() {
    	CookieTracker tracker = new CookieTracker();
    	String[] simArgs = {"InvalidFile.txt", "2018-12-09"};
    	tracker.main(simArgs);
    }

    /**
     * Tests whether the CookieTracker properly handles being given a file name that does not exist
     */
    @Test
    public void testNonexistantFile() {
    	CookieTracker tracker = new CookieTracker();
    	String[] simArgs = {"NonexistantFile.txt", "2018-12-09"};
    	tracker.main(simArgs);
    }
    
    /**
     * Tests whether the CookieTracker properly handles being given invalid inputs
     */
    @Test
    public void testInvalidInput() {
    	CookieTracker tracker = new CookieTracker();
    	String[] simArgs = {"testFile.txt"};
    	String[] simArgs2 = {"2018-12-09"};
    	tracker.main(simArgs);
    	tracker.main(simArgs);
    }
}
