package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.BibleResult;
import beans.BibleResults;
import data.DatabaseServiceInterface;

@Stateless
@Local(BibleSearchInterface.class)
@LocalBean
@Alternative
public class BibleSearchService implements BibleSearchInterface {

	//Inject the database service
	@EJB
	DatabaseServiceInterface service;
	
	public BibleResults searchBible(String word) throws FileNotFoundException {
		BibleResults bibleResults = new BibleResults();
		try {
			//Handle getting the results from the database
			List<BibleResult> results = service.searchWordCI(word);
	
			int numOfVerses = results.toArray().length;
			int numOfWordReferences = 0;
			
			
			for (BibleResult phrase: results) {
				
				//Count then number of references of this phrase/word
				
				//NOTE - Counts overlaps so 'aaaa' looking for 'aa' will result in 3
				
				String input = phrase.getVerseContent();
			    Pattern pattern = Pattern.compile(word);
			    Matcher matcher = pattern.matcher(input);
			    int from = 0;
			    while(matcher.find(from)) {
			    	numOfWordReferences++;
			        from = matcher.start() + 1;
			    }
			}
			
			//Get total number of word references not just number of verses containing word
			//Sort results by OT or NT
			List<BibleResult> otVerses = this.getOTVerses(results);
			List<BibleResult> ntVerses = this.getNTVerses(results);
			
			//Set arrays
			bibleResults.setOldTestementResults(otVerses);
			bibleResults.setNewTestementResults(ntVerses);
			
			//Set other variables
			bibleResults.setSearchedWord(word);
			bibleResults.setTotalResults(numOfWordReferences);
			bibleResults.setVerseResults(numOfVerses);
			
			return bibleResults;
			
		} catch (RuntimeException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	private List<BibleResult> getOTVerses(List<BibleResult> results){
		List<BibleResult> otVerses = new ArrayList<BibleResult>();
		for (BibleResult r: results) {
			if (r.isOldTestement()) otVerses.add(r);
		}
		return otVerses;
	}
	private List<BibleResult> getNTVerses(List<BibleResult> results){
		List<BibleResult> ntVerses = new ArrayList<BibleResult>();
		for (BibleResult r: results) {
			if (!r.isOldTestement()) ntVerses.add(r);
		}
		return ntVerses;
	}
	
}

