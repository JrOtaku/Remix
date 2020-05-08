import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private DatabaseConnectionService dbService = null;

	public UserService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}
	
	public boolean login(String username, String password) {
		if(username == null || username.isEmpty()) return false;
		String query = "SELECT PasswordSalt, PasswordHash FROM [Login] WHERE Username = ?";
		try {
			PreparedStatement stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{
				byte[] salt = dec.decode(rs.getString(1));
				return hashPassword(salt, password).equals(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean register(String username, String password) {
		byte[] salt = getNewSalt();
		String hash = hashPassword(salt, password);
		
		try {
			CallableStatement stmt = this.dbService.getConnection().prepareCall("{? = call Register(?, ?, ?)}");
			stmt.setString(2, username);
			stmt.setString(3, getStringFromBytes(salt));
			stmt.setString(4, hash);
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.execute();
			if(stmt.getInt(1) == 1 || stmt.getInt(1) == 2 || stmt.getInt(1) == 3 || stmt.getInt(1) == 4)
			{
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
