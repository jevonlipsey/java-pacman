import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * A class to handle database communication for the leaderboard.
 */
public class HighScoreDatabase {
	private static final String DB_NAME = "PacMan";
    private static final String DB_USER = "d_sherwood"; // Edit this
    private static final String DB_PASSWORD = "Changeme_00"; 
    private static final String CONNECTION_NAME = "jdbc:mysql://localhost:1521/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASSWORD;
    private static Connection conn;
    private static Statement stmt;
    
    /**
     * A constructor used to define a connection to the Database and a universal statement
     */
    public HighScoreDatabase() {
    	 try {
			conn = DriverManager.getConnection(CONNECTION_NAME);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Constructor Error");
			e.printStackTrace();
		}
    }
    
    /**
     * A method to enter a new score into the database
     * @param score
     * @param name
     */
    public void addNewScore(int score, String name) {
    	String scoreString = Integer.toString(score);
    	String toAdd = "("+scoreString+", '"+name+"')";
    	try {
    		stmt.execute("insert into highScores value" + toAdd);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * A method to retrieve the top N (number) of scores from the data base
     * @param n
     * @return ArrayList of top scores.
     */
    public ArrayList<String> getTopNScores(int n){
    	ArrayList<String> scores = new ArrayList<String>();
    	try {
    		ResultSet rset = stmt.executeQuery("select * from highScores ORDER BY score DESC;");
    		int count = 0;
    		while ((count < n) && (rset.next())){
    			String score = rset.getString("score");
    			String name = rset.getString("name");
    			scores.add(name + " " + score);
    			count++;
    		}
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return scores;
    }
    
    /**
     * A method to retrieve the top N (number) of scores from the data base
     * @param n
     * @return ArrayList of top scores.
     */
    public String getTopScoreNumber(){
    	try {
    		ResultSet rset = stmt.executeQuery("select * from highScores ORDER BY score DESC;");
    		return rset.getString("score");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NullPointerException e) {
		return "0";
	}
		return "0";
    }
    
    /**
     * Clearing the leaderboard entirely.
     */
    public void clearHighScores() {
    	try {
			stmt.execute("DELETE FROM highScores");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}


