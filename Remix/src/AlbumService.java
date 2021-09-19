import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class AlbumService {
	private DatabaseConnectionService dbService = null;

	public AlbumService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean insertAlbum(String title) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Insert_Album(?)");

			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, title);

			cs.execute();
			returnVal = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "Title cannot be NULL");
			return false;
		}
		return true;
	}

	public boolean updateAlbum(int albumID, String albumTitle) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Update_Album(?, ?)");

			cs.registerOutParameter(1, Types.INTEGER);

			cs.setInt(2, albumID);
			cs.setString(3, albumTitle);

			cs.execute();
			returnVal = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "AlbumID and Title can not be NULL");
			return false;
		}
		if (returnVal == 2) {
			JOptionPane.showMessageDialog(null, "AlbumID can not be NULL");
			return false;
		}
		if (returnVal == 3) {
			JOptionPane.showMessageDialog(null, "Title cannot be NULL");
			return false;
		}
		if (returnVal == 4) {
			JOptionPane.showMessageDialog(null, "Given AlbumID does not exist");
			return false;
		}
		if (returnVal == 5) {
			JOptionPane.showMessageDialog(null, "You cannot modify Album 1");
			return false;
		}

		return true;
	}
	
	public boolean deleteAlbum(int albumID) {
		CallableStatement cs;
		int returnVal;
		try {
			cs = dbService.getConnection().prepareCall("? = call Delete_Album(?)");

			cs.registerOutParameter(1, Types.INTEGER);

			cs.setInt(2, albumID);

			cs.execute();
			returnVal = cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (returnVal == 1) {
			JOptionPane.showMessageDialog(null, "AlbumID can not be NULL");
			return false;
		}
		if (returnVal == 2) {
			JOptionPane.showMessageDialog(null, "Given AlbumID does not exist");
			return false;
		}
		if (returnVal == 3) {
			JOptionPane.showMessageDialog(null, "You cannot delete Album 1");
			return false;
		}
		return true;
	}
}
