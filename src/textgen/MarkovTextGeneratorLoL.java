package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		String[] words = sourceText.split("[ .]+");
		if (words.length > 0) {
			starter = words[0];
			String prevWord = starter;
			wordList.add(new ListNode(starter));
			boolean found = false;
			int index = 0;
			// go through every word starting from the second one in the text
			for (int i = 1; i < words.length; i++) {
				// go through every word in the wordList
				for (int j = 0; j < wordList.size() & !found; j++) {
					// check if the word of prevWord is in our list
					if (wordList.get(j).getWord().equals(prevWord)) {
						found = true;
					} else {
						found = false;
					}
					index = j;
				}
				if (found) {
					wordList.get(index).addNextWord(words[i]);
				} else {
					wordList.add(new ListNode(prevWord));
					wordList.get(index + 1).addNextWord(words[i]);
				}
				prevWord = words[i];
				found = false;
				index = 0;
			}
			wordList.add(new ListNode(words[words.length - 1]));
			wordList.get(wordList.size() - 1).addNextWord(starter);
		}
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		String output = "";
		if (wordList.isEmpty() || numWords == 0) {
			return output;
		}
		String currWord = wordList.get(0).getWord();
		output = output + currWord + " ";
		int currNum = 1;
		String w;
		while (currNum < numWords) {
			for (int i = 0; i < wordList.size(); i++) {
				if (wordList.get(i).getWord().equals(currWord)) {
					w = wordList.get(i).getRandomNextWord(rnGenerator);
					output = output + w + " ";
					currWord = w;
					break;
				}
			}
			currNum++;
		}

		return output;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList.clear();
		starter = "";
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		// String text="hi there hi Leo";
		// System.out.println(text);
		// gen.train(text);
		// System.out.println(gen.toString());

		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		String s;
		if (!nextWords.isEmpty()) {
			s = nextWords.get(generator.nextInt(nextWords.size()));
		} else {
			s = "";
		}
		return s;
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
