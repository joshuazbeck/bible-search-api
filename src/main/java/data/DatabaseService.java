package data;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import org.jboss.resteasy.util.Hex;

import beans.BibleResult;


/**
 * Based off of some code for Milestone project by @Tanner Ray
 * @author Josh Beck
 *
 */
@Stateless
@Local(DatabaseServiceInterface.class)
@LocalBean
@Alternative
public class DatabaseService implements DatabaseServiceInterface {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/benchmark?autoReconnect=true&useSSL=false";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "password";

	// Search for a word case insensitive (CI)
	private static final String SEARCH_WORD_CI = "SELECT n as BOOK, t_kjv.c as CHAP, key_english.t as TEST, t_kjv.t as TXT, c as CHAP, v as VERSE FROM `t_kjv` INNER JOIN `key_english` ON `t_kjv`.`b` = `key_english`.`b` WHERE t_kjv.t LIKE ? COLLATE utf8mb4_general_ci";

	@Override
	public List<BibleResult> searchWordCI(String word) throws RuntimeException, SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;

		conn = getConnection();
		stmt = conn.prepareStatement(SEARCH_WORD_CI, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, "%" + word + "%");
		
		ResultSet rs = stmt.executeQuery();
		
		List<BibleResult> bibleResults = new ArrayList<BibleResult>();
		while (rs.next()) {
			//TODO: Error check
			String text = rs.getString("TXT");
			String book = rs.getString("BOOK");
			int verse = rs.getInt("VERSE");
			int chapter = rs.getInt("CHAP");
			boolean isOldTestement = (rs.getString("TEST").equals("OT"));
			
			BibleResult r = new BibleResult();
			r.setVerseContent(text);
			r.setOldTestement(isOldTestement);
			r.setChapterNum(chapter);
			r.setBookName(book);
			r.setVerseNum(verse);

			bibleResults.add(r);
		}
		
		close(stmt);
		close(conn);
		return bibleResults;

	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}