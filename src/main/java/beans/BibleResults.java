package beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A model representing the found results of a Bible search including search statistics and found verses
 * @author Josh Beck
 *
 */

@XmlRootElement
public class BibleResults {

	/************ DECLARATIONS **************/
	String searchedWord = "";
	int totalResults = -1;
	int verseResults = -1;
	List<BibleResult> oldTestementResults = new ArrayList<BibleResult>();
	List<BibleResult> newTestementResults = new ArrayList<BibleResult>();
	

	/************ GETTERS/SETTERS **************/
	public int getVerseResults() {
		return verseResults;
	}
	public void setVerseResults(int verseResults) {
		this.verseResults = verseResults;
	}
	public String getSearchedWord() {
		return searchedWord;
	}
	public void setSearchedWord(String searchedWord) {
		this.searchedWord = searchedWord;
	}
	public List<BibleResult> getOldTestementResults() {
		return oldTestementResults;
	}
	public void setOldTestementResults(List<BibleResult> oldTestementResults) {
		this.oldTestementResults = oldTestementResults;
	}
	public List<BibleResult> getNewTestementResults() {
		return newTestementResults;
	}
	public void setNewTestementResults(List<BibleResult> newTestementResults) {
		this.newTestementResults = newTestementResults;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	
	/************* CONSTRUCTORS *************/
	public BibleResults(String searchedWord, int verseResults, int totalResults, List<BibleResult> oldTestementResults,
			List<BibleResult> newTestementResults) {
		super();
		this.searchedWord = searchedWord;
		this.oldTestementResults = oldTestementResults;
		this.newTestementResults = newTestementResults;
		this.totalResults = totalResults;
		this.verseResults = verseResults;
	}
	public BibleResults() {
		super();
	}
	
	
}
