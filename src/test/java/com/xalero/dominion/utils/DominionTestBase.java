package com.xalero.dominion.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class DominionTestBase extends DBTestCase {
	
	private IDataSet dataSet;
	public DominionTestBase(String dataSet,
							String driver,
							String connectionUrl,
							String dbUsername,
							String dbPassword) throws DataSetException, FileNotFoundException, IOException {
		super("Test");
		this.dataSet = new FlatXmlDataSet(new FileInputStream(getClass().getResource(dataSet).getFile()));
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, connectionUrl );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, dbUsername );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, dbPassword);
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return dataSet;
	}

	protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.DELETE;
    }
}
