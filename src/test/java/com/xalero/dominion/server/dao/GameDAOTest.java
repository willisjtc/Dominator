package com.xalero.dominion.server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dbunit.dataset.DataSetException;
import org.junit.Test;

import com.xalero.dominion.utils.DominionTestBase;
import com.xalero.dominion.utils.TestProperties;

public class GameDAOTest extends DominionTestBase {

	public GameDAOTest() throws DataSetException, FileNotFoundException, IOException {
        super("game_dao_test_fixture.xml",
        	  TestProperties.getProperty("db.driver"),
        	  TestProperties.getProperty("db.connection.url"),
        	  TestProperties.getProperty("db.username"),
        	  TestProperties.getProperty("db.password"));
	}
    
    @Test
    public void testDatabase() {
    	assertTrue(true);
    } 
}
