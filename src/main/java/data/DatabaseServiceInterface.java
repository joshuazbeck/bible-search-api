package data;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import beans.BibleResult;

public interface DatabaseServiceInterface {
	
	List<BibleResult> searchWordCI(String word) throws RuntimeException, SQLException;
	

}
