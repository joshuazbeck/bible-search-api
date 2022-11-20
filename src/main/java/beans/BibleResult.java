package beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A model to represent a single verse
 * @author Josh Beck
 *
 */

@XmlRootElement
public class BibleResult {

	/************ DECLARATIONS **************/
	public String bookName = "";
	public int verseNum = -1;
	public int chapterNum = -1;
	public boolean isOldTestement = false;
	
	/************** GETTER/SETTERS **************/
	public int getChapterNum() {
		return chapterNum;
	}
	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}
	public String verseContent = "";
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getVerseNum() {
		return verseNum;
	}
	public void setVerseNum(int verseNum) {
		this.verseNum = verseNum;
	}
	public boolean isOldTestement() {
		return isOldTestement;
	}
	public void setOldTestement(boolean isOldTestement) {
		this.isOldTestement = isOldTestement;
	}
	public String getVerseContent() {
		return verseContent;
	}
	public void setVerseContent(String verseContent) {
		this.verseContent = verseContent;
	}
	
	/************* CONSTRUCTORS *************/
	public BibleResult(String bookName, int chapterNum, int verseNum, boolean isOldTestement, String verseContent) {
		super();
		this.bookName = bookName;
		this.verseNum = verseNum;
		this.isOldTestement = isOldTestement;
		this.verseContent = verseContent;
		this.chapterNum = chapterNum;
	}
	public BibleResult() {
		super();
	}
	
	
	
	
	
}
