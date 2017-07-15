package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		super();
		this.dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	if(word == null){
    		throw new NullPointerException();
    	}
    	word = word.toLowerCase();
    	// Check if the word is already inside the dictionary
    	for(String s : dict)
    	{
    		if(s.equals(word)){
    			return false;
    		}
    	}
        return dict.add(word);
    }

	/** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if(s == null){
    		throw new NullPointerException();
    	}
    	s = s.toLowerCase();
        return dict.contains(s);
    }

    
}
