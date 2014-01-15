package dominion.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominion.database.utils.DatabaseProperties;

public class InitializationDAO extends BasicDAO {

    public void run() {
        createTableUser();
        insertDefaultUser();
    }

    public void createTableUser() {
        String sql = DatabaseProperties.getProperty("dominion.doesTableExist");
        boolean tableExists = false;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setString(++index, "DOMINION_USER");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tableExists = rs.getInt(index) == 0 ? false : true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (tableExists) {
            return;
        }

        sql = DatabaseProperties.getProperty("dominion.createTableUser");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDefaultUser() {
        String sql = DatabaseProperties.getProperty("dominion.defaultUserExists");
        boolean defaultUserExists = false;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setLong(++index, 1);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                defaultUserExists = rs.getLong(index) == 1 ? true : false;
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        
        if (defaultUserExists) {
            return;
        }
        
        sql = DatabaseProperties.getProperty("dominion.addUser");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setString(++index, "default username");
            ps.setString(++index, "default password");
            
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
