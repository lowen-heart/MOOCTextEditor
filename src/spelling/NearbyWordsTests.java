package spelling;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTests {
	
	NearbyWords nw;

	@Before
	public void setUp() throws Exception {
		
		Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        nw = new NearbyWords(d);
	}

	@Test
	public void testInsertions() {
		String s = "hit";
		List<String> currentList = new LinkedList();
		boolean wordsOnly = true;
		nw.insertions(s, currentList, wordsOnly);
		System.out.println(currentList);
	}
	
	@Test
	public void testDeletion(){
		String s = "shits";
		List<String> currentList = new LinkedList();
		boolean wordsOnly = true;
		nw.deletions(s, currentList, wordsOnly);
		System.out.println(currentList);
	}

}
