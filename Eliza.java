//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Eliza
// Files: Eliza.java
// Course: CS 200 Fall 2018
//
// Author: Jacob Sweis
// Email: jdsweis@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: name of your pair programming partner
// Partner Email: email address of your programming partner
// Lecturer's Name: name of your partner's lecturer
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that collects user input
 * and responds appropriately. Eliza is based off of a computer program written at MIT in the 1960's
 * by Joseph Weizenbaum. Eliza uses keyword matching to respond to users in a way that displays
 * interest in the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /*
     * This method does input and output with the user. It calls supporting methods to read and
     * write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for reading user input
        Random rand = new Random(Config.SEED); // Random generator to produce response
        ArrayList<String> phrases = new ArrayList<String>(); // Separated phrases from input
        ArrayList<String> dialog = new ArrayList<String>(); // Dialog of conversation
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(); // Response table
        String userInput = ""; // User input
        String fileName = ""; // File name
        String response = ""; // Response chosen
        String conversationFile = ""; // File for conversation
        String therapistName = ""; // Therapist name
        boolean save = false; // Save file

        // If args length is 0, therapist is Eliza
        if (args.length == 0) {
            therapistName = "Eliza";
            fileName = therapistName + Config.RESPONSE_FILE_EXTENSION;
            // If args length 1, therapist is command line argument
        } else if (args.length == 1) {
            therapistName = args[0];
            fileName = therapistName + Config.RESPONSE_FILE_EXTENSION;
            // Otherwise, prompt for multiple command line arguments, therapist is chosen by user
        } else {
            System.out.print("Would you like to speak with ");
            for (int i = 0; i < args.length; i++) {
                if (i == args.length - 1) {
                    System.out.println("or " + args[i] + "?");
                } else {
                    System.out.print(args[i] + ", ");
                }
            }
            therapistName = sc.nextLine();
            fileName = therapistName + Config.RESPONSE_FILE_EXTENSION;
        }

        table = loadResponseTable(fileName);

        String welcomePrompt = "Hi I'm " + therapistName + ", what is your name?"; // Welcome prompt
        System.out.println(welcomePrompt);
        dialog.add(welcomePrompt);
        String userName = sc.nextLine(); // User name
        dialog.add(userName);
        String firstPrompt = "Nice to meet you " + userName + ". What is on your mind?"; // First
                                                                                         // prompt
                                                                                         // after
                                                                                         // welcome
        System.out.println(firstPrompt);
        dialog.add(firstPrompt);

        // Begin conversation loop
        while (true) {
            userInput = sc.nextLine();
            dialog.add(userInput);
            String[] userWords = prepareInput(userInput);
            // If userWords is null, break loop
            if (userWords == null) {
                break;
                // Otherwise, prepareReponse, and print out response
            } else {
                response = prepareResponse(userWords, rand, table);
                System.out.println(response);
                dialog.add(response);
            }
        }
        String endPrompt = "Goodbye " + userName + "."; // End prompt
        System.out.println(endPrompt);
        dialog.add(endPrompt);

        do {
            System.out.println("Would you like to save a record of this conversation? y/n");
            String yesOrNo = sc.nextLine(); // Yes or no for save
            // If char is 'y' or 'Y' then save is true and execute try block
            if (yesOrNo.charAt(0) == 'y' || yesOrNo.charAt(0) == 'Y') {
                save = true;
                try {
                    System.out.println("What would you like to name the file?");
                    conversationFile = sc.nextLine().trim();
                    saveDialog(dialog, conversationFile);
                    System.out.println("Thanks again for talking! Our conversation is saved in: "
                        + conversationFile);
                    break;
                } catch (IOException e) {
                    System.out.println("Unable to save conversation to: " + conversationFile);
                    e.printStackTrace();
                }
            } else {
                System.out.println("Thanks again for talking!");
            }
            // Do while save is true
        } while (save);
    }

    /**
     * This method processes the user input, returning an ArrayList containing Strings, where each
     * String is a phrase from the user's input. This is done by removing leading and trailing
     * whitespace, making the user's input all lower case, then going through each character of the
     * user's input. When going through each character this keeps all digits, alphabetic characters
     * and ' (single quote). The characters ? ! , . signal the end of a phrase, and possibly the
     * beginning of the next phrase, but are not included in the result. All other characters such
     * as ( ) - " ] etc. should be replaced with a space. This method makes sure that every phrase
     * has some visible characters but no leading or trailing whitespace and only a single space
     * between words of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and phrases with
     * just invalid/whitespace characters should NOT be added to the list.
     * 
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i am", "a big fun
     * robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {
        ArrayList<String> phrases = new ArrayList<>(); // List of separated phrases
        String currentPhrase = ""; // Phrase that is built on to be added to phrases
        String stringCh = ""; // Char that is added to currentPhrase

        userInput = userInput.trim();
        userInput = userInput.toLowerCase();
        userInput = userInput.replaceAll("\\s+", " "); // Replaces more than one space with a
                                                       // singular space

        if (userInput == null) {
            return null;
        }
        if (userInput == "") {
            return phrases;
        }
        // If a userInput does not contain punctuation, add userInput to phrases and return
        if (!(userInput.contains("?") || userInput.contains("!") || userInput.contains(".")
            || userInput.contains(","))) {
            phrases.add(userInput);
            return phrases;
        }
        // Iterates through all the chars in userInput
        for (int i = 0; i < userInput.length(); i++) {
            char ch = userInput.charAt(i);

            // If char is a letter or a digit or single quote or space, add char to currentPhrase
            if ((Character.isLetterOrDigit(ch) == true) || (ch == '\'') || (ch == ' ')) {
                stringCh = Character.toString(ch);
                currentPhrase += stringCh;
                // If char is punctuation, add currentPhrase to phrases
            } else if ((ch == '?') || (ch == '!') || (ch == ',') || (ch == '.')) {
                currentPhrase = currentPhrase.trim();
                if (currentPhrase.length() != 0) {
                    phrases.add(currentPhrase);
                    currentPhrase = "";
                }
                // If any other char, add to currentPhrase
            } else {
                ch = ' ';
                stringCh = Character.toString(ch);
                currentPhrase += stringCh;
            }

            // If iterating through the last char in userInput
            if (i == userInput.length() - 1) {
                currentPhrase = currentPhrase.trim();
                if (currentPhrase.length() != 0 && !(currentPhrase == "")) {
                    phrases.add(currentPhrase);
                }
            }
        }
        return phrases;
    }

    /**
     * Checks whether any of the phrases in the parameter match a quit word from Config.QUIT_WORDS.
     * Note: complete phrases are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        boolean found = false; // quitWord found
        // Iterates through phrases
        for (String phrase : phrases) {
            // Iterates through Config.QUIT_WORDS
            for (String quitWord : Config.QUIT_WORDS) {
                found = phrase.equals(quitWord);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding the longest phrase to which to
     * respond. If two phrases are the same length, returns whichever has the lower index in the
     * list. If phrases parameter is null or size 0 then return null.
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {
        String longestPhrase = ""; // Longest Phrase

        // If phrases is null or 0 length, return ""
        if (phrases == null || phrases.size() == 0) {
            return "";
        }

        // Iterates through phrases
        for (String phrase : phrases) {
            // If phrase is longer than longestPhrase, assign phrase to longestPhrase
            if (phrase.length() > longestPhrase.length()) {
                longestPhrase = phrase;
            }
        }
        return longestPhrase;
    }

    /**
     * Looks for a replacement word for the word parameter and if found, returns the replacement
     * word. Otherwise if the word parameter is not found then the word parameter itself is
     * returned. The wordMap parameter contains rows of match and replacement strings. On a row, the
     * element at the 0 index is the word to match and if it matches return the string at index 1 in
     * the same row. Some example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     * 
     * If word is null return null. If wordMap is null or wordMap length is 0 simply return word
     * parameter. For this implementation it is reasonable to assume that if wordMap length is >= 1
     * then the number of elements in each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap otherwise the
     *         word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        // If word is null, return null
        if (word == null) {
            return null;
        }
        // If wordMap is null or 0 length, return word
        if (wordMap == null || wordMap.length == 0) {
            return word;
        }

        // Iterates through wordMap
        for (int i = 0; i < wordMap.length; i++) {
            // If word equals the any words in the first column of wordMap, assign word to
            // replacement word in the second column of wordMap
            if (word.equals(wordMap[i][0])) {
                word = wordMap[i][1];
            }
        }
        return word;
    }

    /**
     * Concatenates the elements in words parameter into a string with a single space between each
     * array element. Does not change any of the strings in the words array. There are no leading or
     * trailing spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String sentence = ""; // Sentence being created

        // Iterates through words, and concatenates word with sentence to form the phrase
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            sentence += (word + " ");
        }
        return sentence.trim();
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord parameter. A
     * word at a time from phrase parameter is looked for in wordMap which may result in more than
     * one word. For example: i'm => i am Uses the replaceWord and assemblePhrase methods. Example
     * wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will itself be a key to
     * another entry. In other words, only one pass through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {
        // If wordMap is null, return phrase
        if (wordMap == null) {
            return phrase;
        }

        String[] words = phrase.split(" "); // Splits phrase by spaces, and assigns each word to
                                            // words
        int i = 0; // Count variable

        // Iterates through words, and replaces word. Then assigns word to current index of words
        for (String word : words) {
            word = replaceWord(word, wordMap);
            // System.out.println(word);
            words[i] = word;
            i++;
        }

        String reassembledPhrase = assemblePhrase(words); // Creates the reassembled phrase
        // System.out.println(reassembledPhrase);
        return reassembledPhrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using separatePhrases).
     * If a phrase is a quit word (foundQuitWord) then return null. Otherwise, select a phrase
     * (selectPhrase), swap input words (swapWords with Config.INPUT_WORD_MAP) and return an array
     * with each word its own element in the array.
     * 
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        ArrayList<String> phrases = new ArrayList<>(); // List of phrases
        phrases = separatePhrases(input); // Assigns separated phrases to phrases

        // If quit word is found, return null
        if (foundQuitWord(phrases)) {
            return null;
        } else {
            String longestPhrase = selectPhrase(phrases); // Longest phrase in phrases
            longestPhrase = swapWords(longestPhrase, Config.INPUT_WORD_MAP); // Replaces necessary
                                                                             // words in
                                                                             // longestPhrase
            String[] words = longestPhrase.split(" "); // Splits longestPhrase at spaces and assigns
                                                       // words to array
            return words;
        }
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line is
     * ignored. A keyword line begins with "keywords" with all the following tokens on the line, the
     * keywords. Each line that follows a keyword line that is not blank is a possible response for
     * the keywords. For example (the numbers are for our description purposes here and are not in
     * the file):
     * 
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers? 4 5 keywords i
     * dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while you were awake? 8 9 Have you ever
     * dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses on lines 2 and 3.
     * Line 4 and 8 are ignored since they are blank (contain only whitespace). Line 5 begins new
     * keywords that are the words "i" and "dreamed". This keyword list is followed by three
     * possible responses on lines 6, 7 and 9.
     * 
     * The keywords and associated responses are each stored in their own ArrayList. The response
     * table is an ArrayList of the keyword and responses lists. For every keywords list there is an
     * associated response list. They are added in pairs into the list that is returned. There will
     * always be an even number of items in the returned list.
     * 
     * Note that in the event an IOException occurs when trying to read the file then an error
     * message "Error reading <fileName>", where <fileName> is the parameter, is printed and a
     * non-null reference is returned, which may or may not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(); // Response table
        int index = -1; // Index of phrases
        try {
            File file = new File(fileName); // File of keywords and responses
            Scanner input = new Scanner(file); // Scanner to read file
            String fileLine = ""; // Line being read by scanner

            // Loop until there are no lines in the file left
            while (input.hasNextLine()) {

                fileLine = input.nextLine();
                // If fileLine is nothing, continue looping
                if (fileLine.equals("")) {
                    continue;
                }
                fileLine = fileLine.trim();
                // If index of keywords is 0
                if (fileLine.indexOf("keywords") == 0) {
                    fileLine = fileLine.replace("keywords", "").trim(); // Replaces keywords with
                                                                        // nothing, so it keywords
                                                                        // is ignored
                    String[] tempArray = fileLine.split(" "); // Splits keywords by spaces, and
                                                              // assigns to temporary array
                    ArrayList<String> keywords = new ArrayList<String>(); // List of keywords
                    // Iterates through tempArray, and assigns keywords to index of tempArray
                    for (int i = 0; i < tempArray.length; i++) {
                        keywords.add(tempArray[i]);
                    }
                    index += 2; // Index of a phrase is every other in table
                    table.add(keywords);
                    table.add(new ArrayList<String>()); // Creates new list in table
                    // Otherwise, add response to table
                } else {
                    table.get(index).add(fileLine);
                }
            }
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords
     * match then this method returns an array with the unmatched words before, between and after
     * the keywords. If the keywords do not match then null is returned.
     * 
     * When the phrase contains elements before, between, and after the keywords, each set of the
     * three is returned in its own element String[] keywords = {"i", "dreamed"}; String[] phrase =
     * {"do", "you", "know", that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
     * 
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being an astronaut"
     * 
     * In an example where there is a single keyword, the resulting List's first element will be the
     * the pre-sequence element and the second element will be everything after the keyword, in the
     * phrase String[] keywords = {"always"}; String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I" toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"}; String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     *         are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        // see the algorithm presentation linked in Eliza.pdf.
        ArrayList<String> unmatched = new ArrayList<String>(); // Unmatched phrases
        ArrayList<String> match = new ArrayList<String>(); // Matched keywords
        String buildPhrase = ""; // Phrase to build unmatched phrases
        boolean keyword = false; // Keyword found

        // Iterates through phrase
        for (int i = 0; i < phrase.length; i++) {
            // Iterates through keywords
            for (int j = 0; j < keywords.size(); j++) {
                // If phrase equals keywords, add keywords to match, and set keyword to true
                if (phrase[i].equals(keywords.get(j))) {
                    match.add(keywords.get(j));
                    keyword = true;
                }
            }
            // If keyword is false, add phrase to buildPhrase
            if (keyword == false) {
                buildPhrase += phrase[i] + " ";
            }
            // If keyword is true, check to see if it is a 0 length string
            if (keyword) {
                // If buildPhrase is not 0 length, add phrase to unmatched. Otherwise, add keyword
                // as 0 length string
                if (!(buildPhrase.equals(""))) {
                    unmatched.add(buildPhrase.trim());
                } else {
                    unmatched.add(buildPhrase);
                }
                buildPhrase = ""; // Resets buildPhrase
                keyword = false; // Resets keyword
            }
        }
        // If buildPhrase is not 0 length, add phrase to unmatched.
        if (!(buildPhrase.equals(""))) {
            unmatched.add(buildPhrase.trim());
        }
        // If keywords don't match return null
        if (!(keywords.equals(match))) {
            return null;
        }
        // If last keyword is equal to last phrase, add 0 length string to unmatched
        if (keywords.get(keywords.size() - 1).equals(phrase[phrase.length - 1])) {
            unmatched.add("");
        }

        String[] toReturn = new String[unmatched.size()]; // Array to return
        // Iterated through unmatched, and puts each element into array
        for (int i = 0; i < unmatched.size(); i++) {
            toReturn[i] = unmatched.get(i);
        }
        return toReturn;
    }

    /**
     * Selects a randomly generated response within the list of possible responses using the
     * provided random number generator where the number generated corresponds to the index of the
     * selected response. Use Random nextInt( responseList.size()) to generate the random number. If
     * responseList is null or 0 length then return null.
     * 
     * @param rand A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        // If responseList is null or 0 length, return null
        if (responseList == null || responseList.size() == 0) {
            return null;
        }
        int randomNum = rand.nextInt(responseList.size()); // Random number created from size of
                                                           // responseList
        String response = responseList.get(randomNum); // Randomly chosen response
        return response;
    }

    /**
     * This method takes processed user input and forms a response. This looks through the response
     * table in order checking to see if each keyword pattern matches the userWords. The first
     * matching keyword pattern found determines the list of responses to choose from. A keyword
     * pattern matches the userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See the
     * findKeyWordsInPhrase algorithm in the Eliza.pdf.
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned. Otherwise one of
     * possible responses for the matched keywords is selected with selectResponse method. The
     * response selected is checked for the replacement symbol <n> where n is 1 to the length of
     * unmatchedWords array returned by findKeyWordsInPhrase. For each replacement symbol the
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.) has its pronouns
     * swapped with swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol in
     * the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable A table containing a list of keywords and response pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand,
        ArrayList<ArrayList<String>> responseTable) {
        boolean keywordFound = false; // Keyword found
        String response = ""; // Response chosen
        int posOfKeyword = 0; // Position of keyword

        // Iterate through the response table
        for (int i = 0; i < responseTable.size(); i += 2) {
            // If findKeyWordsInPhrase for response is not null, set keywordFound to true,
            // posOfKeyword to i, and break
            if (findKeyWordsInPhrase(responseTable.get(i), userWords) != null) {
                keywordFound = true;
                posOfKeyword = i;
                break;
            }
        }
        // If no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        if (keywordFound == false) {
            return Config.NO_MATCH_RESPONSE;
        }
        // Otherwise, select a response using the appropriate list of responses for the keywords
        else {
            response = selectResponse(rand, responseTable.get(posOfKeyword + 1));
        }

        String[] unmatchedWords = findKeyWordsInPhrase(responseTable.get(posOfKeyword), userWords); // Unmatched
                                                                                                    // words
                                                                                                    // for
                                                                                                    // method
                                                                                                    // call
        while (response.contains("<") && response.contains(">")) {
            int numIndex = response.indexOf("<") + 1; // Index of '<'
            char num = response.charAt(numIndex); // Number inside of <>
            int number = Character.getNumericValue(num); // Converts char to int
            String replace = swapWords(unmatchedWords[number - 1], Config.PRONOUN_MAP); // Swaps
                                                                                        // pronouns
            response = response.replaceAll("<" + number + ">", replace);
        }
        return response;
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the tracked
     * conversation. Every line ends with a newline. Throws an IOException if a writing error
     * occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
        File file = new File(fileName); // Creates new file
        PrintWriter writer = new PrintWriter(file); // PrintWriter for the new file

        // Iterates through dialog, and writes the line of conversation
        for (String line : dialog) {
            writer.println(line);
        }
        writer.close();
    }
}
