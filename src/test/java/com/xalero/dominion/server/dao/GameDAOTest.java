package com.xalero.dominion.server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dbunit.dataset.DataSetException;
import org.junit.Test;

import com.xalero.dominion.utils.DominionTestBase;
import com.xalero.dominion.utils.TestProperties;

public class GameDAOTest extends DominionTestBase {

	public GameDAOTest(String dataSet, String driver, String connectionUrl,
			String dbUsername, String dbPassword) throws DataSetException,
			FileNotFoundException, IOException {
		super(dataSet, driver, connectionUrl, dbUsername, dbPassword);
	}

}
