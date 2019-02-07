//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Eliza
// Files: ElizaTests.java
// Course: CS 200 Fall 2018
//
// Author: Jacob Sweis
// Email: jdsweis@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    name of your pair programming partner
// Partner Email:   email address of your programming partner
// Lecturer's Name: name of your partner's lecturer
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza
 * class as they are developed. These methods are private since they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author TODO add your name here when you add tests and comment the tests
 *
 */
public class ElizaTests {
    
    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String[] args) {

        //Milestone 1: Process User Input
        //M1: main  nothing to do   
        testSeparatePhrases();  
        testFoundQuitWord();    
        testSelectPhrase();     
        testReplaceWord();      
        testAssemblePhrase();   

        //Milestone 2: 
        //M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();
        
        //Milestone 3: 
        //main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();  
        testSelectResponse();      
        testInputAndResponse();     
        testSaveDialog();           
    }
    
    /**
     * This runs some tests on the separatePhrases method. 
     * 1. Tests to see if the size of the list returned matches with size of expected
     * 2. Tests the phrase "No.  I'm talking  to my dog! Bye." to see if it matches with expected after separatePhrases call
     * 3. Tests the phrase "Thank, /[]} you()()!" to see if it matches with expected after separatePhrases call
     * 4. Tests the phrase "NO" to see if it matches with expected after separatePhrases call
     * 5. Tests the phrase "Thank you, but no, I have to go. Goodbye!!!" to see if it matches with expected after separatePhrases call
     * 6. Tests the phrase "What?" to see if it matches with expected after separatePhrases call
     * 7. Tests the phrase "Help!!! !" to see if it matches with expected after separatePhrases call
     */ 
    private static void testSeparatePhrases() {
        boolean error = false; // Error

        // 1.
        ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("no");
        expected.add("i'm talking to my dog");
        expected.add("bye");

        if (phrases.size() != expected.size()) {
            error = true;
            System.out.println(
                    "testSeparatePhrases: expected " + expected.size() + " but found " + phrases.size() + " phrases.");
        }

        // 2.
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(phrases.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected.get(i));
                System.out.println("  " + phrases.get(i));
            }
        }
        // 3.
        ArrayList<String> phrases1 = Eliza.separatePhrases("Thank, /[]} you()()!");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("thank");
        expected1.add("you");
        for (int i = 0; i < expected1.size(); i++) {
            if (!expected1.get(i).equals(phrases1.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected1.get(i));
                System.out.println("  " + phrases1.get(i));
            }
        }
        // 4.
        ArrayList<String> phrases2 = Eliza.separatePhrases("NO");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("no");
        for (int i = 0; i < expected2.size(); i++) {
            if (!expected2.get(i).equals(phrases2.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected2.get(i));
                System.out.println("  " + phrases2.get(i));
            }
        }
        // 5.
        ArrayList<String> phrases3 = Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
        ArrayList<String> expected3 = new ArrayList<>();
        expected3.add("thank you");
        expected3.add("but no");
        expected3.add("i have to go");
        expected3.add("goodbye");
        for (int i = 0; i < expected3.size(); i++) {
            if (!expected3.get(i).equals(phrases3.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected3.get(i));
                System.out.println("  " + phrases3.get(i));
            }
        }
        // 6.
        ArrayList<String> phrases4 = Eliza.separatePhrases("What?");
        ArrayList<String> expected4 = new ArrayList<>();
        expected4.add("what");
        for (int i = 0; i < expected4.size(); i++) {
            if (!expected4.get(i).equals(phrases4.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected4.get(i));
                System.out.println("  " + phrases4.get(i));
            }
        }
        // 6.
        ArrayList<String> phrases5 = Eliza.separatePhrases("Help!!! !");
        ArrayList<String> expected5 = new ArrayList<>();
        expected5.add("help");
        for (int i = 0; i < expected5.size(); i++) {
            if (!expected5.get(i).equals(phrases5.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected5.get(i));
                System.out.println("  " + phrases5.get(i));
            }
        }

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 
     * 1. Tests phrases ["thank you for talking", "bye"], foundQuitWord should return true 
     * 2. Tests phrases ["thank you for talking bye"], foundQuitWord should return false
     * 3. Tests phrases ["goodbye"], foundQuitWord should return true
     * 4. Tests phrases ["hello"], foundQuitWord should return false 
     */
    // TODO should QUIT_WORDS be embedded within foundQuitWord?
    private static void testFoundQuitWord() {
        boolean error = false; // Error

        // 1.
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("thank you for talking");
        phrases.add("bye");

        boolean quit = Eliza.foundQuitWord(phrases);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }
        
        // 2.
        ArrayList<String> phrases1 = new ArrayList<>();
        phrases1.add("thank you for talking bye");

        quit = Eliza.foundQuitWord(phrases1);
        if (quit) {
            error = true;
            System.out.println("testFoundQuitWord 2: failed");
        }
        
        // 3.
        ArrayList<String> phrases2 = new ArrayList<>();
        phrases2.add("goodbye");

        quit = Eliza.foundQuitWord(phrases2);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }
        
        // 4.
        ArrayList<String> phrases3 = new ArrayList<>();
        phrases3.add("hello");

        quit = Eliza.foundQuitWord(phrases3);
        if (quit) {
            error = true;
            System.out.println("testFoundQuitWord 4: failed");
        }

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 
     * 1. Tests phrases ["no", "sometimes I remember being on a boat", "not often"], selectPhrase should return "sometimes I remember being on a boat" 
     * 2. Tests phrases [null], selectPhrase should return ""
     * 3. Tests phrases ["hello", "howdy"], selectPhrase should return "hello"
     */ 
    private static void testSelectPhrase() {
        boolean error = false; // Error

        // 1.
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("no");
        phrases.add("sometimes I remember being on a boat");
        phrases.add("not often");
        String choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("sometimes I remember being on a boat")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }
        
        // 2.
        ArrayList<String> emptyList = new ArrayList<>();
        String empty = Eliza.selectPhrase(emptyList);
        if (!empty.equals("")) {
            error = true;
            System.out.println("testSelectPhrase 2: failed");
        }
        
        // 3.
        ArrayList<String> phrases1 = new ArrayList<>();
        phrases1.add("hello");
        phrases1.add("howdy");
        choice = Eliza.selectPhrase(phrases1);
        if (!choice.equals("hello")) {
            error = true;
            System.out.println("testSelectPhrase 3: failed");
        }
        
        // additional test suggestions:
        // What should happen when there are 2 choices of the same length?
        // What should happen if an empty list is passed to selectPhrase?

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 
     * 1. Tests the array { "This", "is a", "sentence" }, assemblePhrase should return "This is a sentence"
     * 2. Tests the array {}, assemblePhrase should return ""
     * 3. Tests the array {"Hello", "Jacob"}, assemblePhrase should return "Hello Jacob"
     */
    private static void testAssemblePhrase() {
        boolean error = false; // Error

        // 1.
        String[] words = { "This", "is a", "sentence" };
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }
        
        // 1.
        String[] words1 = {};
        String sentence1 = Eliza.assemblePhrase(words1);
        String expectedSentence1 = "";

        if (!sentence1.equals(expectedSentence1)) {
            error = true;
            System.out.println("testAssembleSentence 2 failed '" + sentence1 + "'");
        }
        
        // 3.
        String[] words2 = {"Hello", "Jacob"};
        String sentence2 = Eliza.assemblePhrase(words2);
        String expectedSentence2 = "Hello Jacob";

        if (!sentence2.equals(expectedSentence2)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence2 + "'");
        }

        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method. 
     * 1. Tests the phrase { "are", "you", "a", "computer" }, findKeyWordsInPhrases should return ["are you a", ""]
     * 2. Tests the phrase { "computer", "are", "you" }, findKeyWordsInPhrases should return ["", "are you"]
     * 3. Tests the phrase { "does", "that", "computer", "on", "your", "desk", "work" }, findKeyWordsInPhrases should return ["does that", "on your desk work"]
     * 4. Tests the phrase { "why", "don't", "you", "like", "me" }, findKeyWordsInPhrases should return ["why don't", "like", ""]
     * 5. Tests the phrase { "me", "don't", "like", "you" }, findKeyWordsInPhrases should return [null]
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {
            // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "are", "you", "a", "computer" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a") || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "computer", "are", "you" };

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("") || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                    || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");            
            keywords.add("me");         
            String[] phrase = { "why", "don't", "you", "like", "me" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't") || !matches[1].equals("like")
                    || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");            
            keywords.add("me");             
            String[] sentence = { "me", "don't", "like", "you" };
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }
    
    /**
     * This runs some tests on the saveDialog method. 
     * 1. Tests the list ["this is a single line."] 
     * 2. Tests the list [""this is a single line.", "this is a second line."]
     */ 
    private static void testSaveDialog()  {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog( list, TEST_FILENAME);
                String readFromFile = readFile( TEST_FILENAME);
                if ( !readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile( TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
        { // 2.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            list.add("this is a second line.");
            try {
                Eliza.saveDialog( list, TEST_FILENAME);
                String readFromFile = readFile( TEST_FILENAME);
                if ( !readFromFile.equals(list.get(0) + "\n" + list.get(1) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 2 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile( TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }

        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }
    
    /**
     * Supporting method for testSaveDialog
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * 
     * @param filename  file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This runs some tests on the replaceWord method. 
     * 1. Tests the word "machine", should replace with "computer" 
     * 2. Tests the word "yourself", should replace with "myself"
     * 3. Tests a null wordMap, replaceWord should return "yourself"
     */ 
    private static void testReplaceWord()  {
        boolean error = false;
        
        {    //1. 
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }   
        
        {    //2. 
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        {    //3. 
            String word = "yourself";
            String expected = "yourself";
            String result = Eliza.replaceWord(word, null);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }
    
    /**
     * This runs some tests on the swapWords method. 
     * 1. Tests the phrase "i'm cant recollect", swapWords should return "i am cannot remember"
     * 2. Tests the phrase "i'm happy", swapWords should return "you are happy"
     * 3. Tests the phrase "about my dog", swapWords should return "about your dog"
     * 4. Tests with a null wordMap, swapWords should return "about my dog"
     */ 
    private static void testSwapWords()  {
        boolean error = false;
        
        {    //1. 
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }       

        {    //2. 
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        {    //3. 
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
        
        {    //4. 
            String someWords = "about my dog";
            String expected = "about my dog";
            String result = Eliza.swapWords(someWords, null);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testSwapWords 4 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }
      
        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }       
    }   

    /**
     * This runs some tests on the selectResponse method. 
     * 1. Tests the list ["The", "happy", "cat"], selectResponse should choose one of those
     * 2. Tests the list ["this", "is", "a", "list", "to", "choose", "from"], selectResponse should choose one of those
     * 3. Tests the list ["The"], selectResponse should "The"
     * 4. Tests the list [null], selectResponse should return null
     */ 
    private static void testSelectResponse() {
        boolean error = false;
        
        {  //1.
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");         
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }
        
        { // 2.
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int [] actualCount = new int[strList.size()];
            int [] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for ( int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if ( choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }

            for ( int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if ( actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString( expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString( actualCount));
                }
            }

        }
        
        {  //3.
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");         
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The"))) {
                error = true;
                System.out.println("testSelectResponse 3 failed.");
            }
        }
        
        {  //3.
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice == null)) {
                error = true;
                System.out.println("testSelectResponse 4 failed.");
            }
        }
        
        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }           
    }   

    /**
     * This runs some tests on the prepareInput method. 
     * 1. Tests the input "bye", prepareInput should return null
     * 2. Tests the input "I said goodbye", prepareInput should return {"i", "said", "goodbye"}
     * 3. Tests the input "I can't do that", prepareInput should return {"i", "cannot", "do", "that"}
     * 4. Tests the input "The weather-it is great, bye", prepareInput should return null
     * 5. Tests the input "Great talk!!goodbye,Expert", prepareInput should return null
     */     
    private static void testPrepareInput() {
        boolean error = false;
        
        { // 1.
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        
        { // 2.
            String input = "I said goodbye";
            String[] result = null;
            String[] expected = {"i", "said", "goodbye"};
            result = Eliza.prepareInput(input);
            if (!(Arrays.equals(result, expected))) {
                error = true;
                System.out.println("testPrepareInput 2: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        
        { // 3.
            String input = "I can't do that";
            String[] result = null;
            String[] expected = {"i", "cannot", "do", "that"};
            result = Eliza.prepareInput(input);
            if (!(Arrays.equals(result, expected))) {
                error = true;
                System.out.println("testPrepareInput 3: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        
        { // 4.
            String input = "The weather-it is great, bye";
            String[] result = null;
            result = Eliza.prepareInput(input);
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 4: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        
        { // 5.
            String input = "Great talk!!goodbye,Expert";
            String[] result = null;
            result = Eliza.prepareInput(input);
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 5: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
                
        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }   
    }
    
    /**
     * This runs some tests on the loadResponseTable method. 
     * 1. Tests first row of table, loadResponseTable should return [computer] 
     * 2. Tests if table size is even
     * 3. Tests last row of table, loadResponseTable should return [I'm not sure I understand you fully., Please go on., What does that suggest to you?, Do you feel strongly about discussing such things?]
     */     
    private static void testLoadResponseTable() {
        boolean error = false;
        
        { // 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals( expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " +table.get(0));
            }
            
            // 2.
            if ( table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println("  expected an even number of elements in table but found: " + table.size());
            }
        }
        
        { // 3.
            ArrayList<String> expectedlastRow = new ArrayList<String>();
            expectedlastRow.add("I'm not sure I understand you fully.");
            expectedlastRow.add("Please go on.");
            expectedlastRow.add("What does that suggest to you?");
            expectedlastRow.add("Do you feel strongly about discussing such things?");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(table.size() - 1).equals( expectedlastRow)) {
                error = true;
                System.out.println("testLoadResponseTable 3: failed");
                System.out.println("  expectedlastRow: " + expectedlastRow);
                System.out.println("  tablelastRow: " +table.get(table.size() - 1));
            }
        }
 
        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }   
    }   
    
    /*
     * Supporting method for testInputAndResponse.
     * Returns 1 if the test passed and 0 if the test failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand, ArrayList<ArrayList<String>> table) {
        
        String [] words = Eliza.prepareInput(input);
        if ( words == null) {
            if ( expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }
        
        String response = Eliza.prepareResponse(words, rand, table);
        if ( !response.equals( expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response +"'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }
    
    /*
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        //since we are using a seed in the random number generator, the responses should be fixed for the provided response table.
        Random randGen = new Random( 923);
        ArrayList<ArrayList<String>> responseTable = Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.", "What do you think machines have to do with your problem?", randGen, responseTable);
        numPassed += checkResponse("What is your name?", "I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.", "What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?", randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?", randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.", "Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.", "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.", "Why do you think you apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.", "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.", "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.", "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.", "Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen, responseTable);
        numPassed += checkResponse("Are you a Packers fan?", "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?", randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.", "Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.", "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.", "How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);
        
        numPassed += checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);
        
        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }   
    }
    
}