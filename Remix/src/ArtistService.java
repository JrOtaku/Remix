import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class ArtistService {
	private DatabaseConnectionService dbService = null;

	public ArtistService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean addArtist(String artistName) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Insert_Artist(?)");
			
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, artistName);
			cs.execute();
			
			returnVal = cs.getInt(1); 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "Artist name cannot be null");
			return false;
		}
		return true;
	}

	public boolean updateArtist(int artistID, String newArtistName) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Update_Artist(?, ?)");

			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, artistID);
			cs.setString(3, newArtistName);

			cs.execute();
			returnVal = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "Artist does not exist");
			return false;
		}

		return true;
	}
	
	public boolean deleteArtist(int artistID) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Delete_Artist(?)");

			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, artistID);

			cs.execute();
			returnVal = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "Artist does not exist");
			return false;
		}
		if (returnVal == 2) {
			JOptionPane.showMessageDialog(null, "ArtistID cannot be null");
			return false;
		}

		return true;
	}
}
