package business;

import java.io.FileNotFoundException;
import java.util.List;

import javax.ejb.Local;

import beans.BibleResult;
import beans.BibleResults;

/**
 * An interface
 * @author Josh Beck
 *
 */
@Local
public interface BibleSearchInterface {

	/**
	 * Search the Bible by word
	 * @param word
	 * @return
	 * @throws FileNotFoundException
	 */
	public BibleResults searchBible(String word) throws FileNotFoundException;

}
