import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistContentService {
	private DatabaseConnectionService dbService = null;

	public ArtistContentService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean getSongsByArtist(String ArtistName) {
		String query = "SELECT Song FROM ArtistSongs WHERE ArtistName = ?";
		PreparedStatement ps;
		
		try{
			ps = dbService.getConnection().prepareStatement(query);
			
			ps.setString(1, ArtistName);
			ResultSet rs = ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean getAlbumsByArtist(String ArtistName) {
		String query = "SELECT Song FROM ArtistAlbums WHERE ArtistName = ?";
		PreparedStatement ps;
		
		try{
			ps = dbService.getConnection().prepareStatement(query);
			
			ps.setString(1, ArtistName);
			ResultSet rs = ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
