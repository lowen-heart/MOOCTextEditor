package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should convert the string to all lower case before you insert it.
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes
	 * into the trie, as described outlined in the videos for this week. It
	 * should appropriately use existing nodes in the trie, only creating new
	 * nodes when necessary. E.g. If the word "no" is already in the trie, then
	 * adding the word "now" would add only one additional node (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already
	 *         exists in the dictionary.
	 */
	public boolean addWord(String word) {
		if (word == null) {
			throw new NullPointerException();
		}
		if(word.equals("")){
			return false;
		}
		word = word.toLowerCase();
		return checkAddTrie(root, word, 0);
	}

	/** Helper method for adding a new word **/
	private boolean checkAddTrie(TrieNode current, String word, int index) {
		// if the text is equal to the text given, do not add, return false
		// System.out.println("Begin check " + current.getText() + " " + word +
		// " " + index);
		if (current.getText().equals(word)) {
			if (!current.endsWord()) {
				// System.out.println("Equals " + current.getText() + " " + word
				// + " " + index);
				current.setEndsWord(true);
				++size;
			}
			return false;
		}
		// if the next chars contains the char at index i of the word follow the
		// link
		TrieNode next = current.insert(word.charAt(index));
		// check if the next word is new (not null)
		if (next != null) {
			// if we are not at the end of the word, setEndWords to false and go
			// ahead with a new check
			// else we are at the end of the word, the word is new and added so
			// setEndWord to true and return true
			if (index < word.length() - 1) {
				next.setEndsWord(false);
				return checkAddTrie(next, word, ++index);
			} else {
				next.setEndsWord(true);
				++size;
				// System.out.println("New word " + next.getText() + " " + word
				// + " " + index + " " + word.length() + " " + size);
				return true;
			}
		}
		// if the next word is null, so the char exists, go ahead with a new
		// check
		if (index < word.length()) {
			return checkAddTrie(current.getChild(word.charAt(index)), word, ++index);
		}
		// else we ended and the word is not inserted
		return false;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week.
	 */
	@Override
	public boolean isWord(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		s = s.toLowerCase();
		TrieNode node = checkTrie(root, s, 0);
		if (node != null) {
			return node.endsWord();
		}
		return false;

	}

	// helper method to find a node given a string
	private TrieNode checkTrie(TrieNode current, String s, int index) {
		if (current.getText().equals(s)) {
			return current;
		}
		if (current.getValidNextCharacters().contains(s.charAt(index))) {
			 //System.out.println(current.getText() + " " + s + " " + index);
			return checkTrie(current.getChild(s.charAt(index)), s, ++index);
		}
		//System.out.println(current.getText() + " " + s + " " + index);
		return null;
	}

	/**
	 * Return a list, in order of increasing (non-decreasing) word length,
	 * containing the numCompletions shortest legal completions of the prefix
	 * string. All legal completions must be valid words in the dictionary. If
	 * the prefix itself is a valid word, it is included in the list of returned
	 * words.
	 * 
	 * The list of completions must contain all of the shortest completions, but
	 * when there are ties, it may break them in any order. For example, if
	 * there the prefix string is "ste" and only the words "step", "stem",
	 * "stew", "steer" and "steep" are in the dictionary, when the user asks for
	 * 4 completions, the list must include "step", "stem" and "stew", but may
	 * include either the word "steer" or "steep".
	 * 
	 * If this string prefix is not in the trie, it returns an empty list.
	 * 
	 * @param prefix
	 *            The text to use at the word stem
	 * @param numCompletions
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to numCompletions best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		if (prefix == null) {
			throw new NullPointerException();
		}
		if (numCompletions < 0) {
			return new LinkedList<String>();
		}
		prefix = prefix.toLowerCase();
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the
		// trie, return an
		// empty list
		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem
		// to the back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions

		TrieNode stem = checkTrie(root, prefix, 0);
		// if null return an empty list
		if (stem == null) {
			return new LinkedList<String>();
		}
		Queue<TrieNode> queue = new LinkedList<TrieNode>();
		queue.add(stem);
		List<String> completions = new LinkedList<String>();
		TrieNode current;
		while (!queue.isEmpty() && completions.size() < numCompletions) {
			current = queue.remove();
			if (current != null) {
				if (current.endsWord()) {
					completions.add(current.getText());
				}
				Set<Character> chars = current.getValidNextCharacters();
				for (char c : chars) {
					queue.add(current.getChild(c));
				}
			}
		}
		return completions;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}