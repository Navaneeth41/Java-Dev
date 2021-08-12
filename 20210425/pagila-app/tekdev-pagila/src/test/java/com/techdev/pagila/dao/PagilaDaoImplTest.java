package com.techdev.pagila.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techdev.pagila.dto.Customer;

@ExtendWith(MockitoExtension.class)
public class PagilaDaoImplTest {

	MockedStatic<DbConfig> mockStaticDbConfig;
	Connection connection;
	Statement stmt;
	ResultSet rs;

	@BeforeEach
	void setUp() {
		mockStaticDbConfig = Mockito.mockStatic(DbConfig.class);

	}

	@AfterEach
	void tearDown() throws Exception {
		if (mockStaticDbConfig != null) {
			mockStaticDbConfig.close();
		}
	}
	
	void mockConnection() throws SQLException {
		rs = Mockito.mock(ResultSet.class);
		stmt = Mockito.mock(Statement.class);
		connection = Mockito.mock(Connection.class);
		mockStaticDbConfig.when(DbConfig::createConnection).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(stmt);		
	}

	@Test
	void testGetCustomerId() throws SQLException {
		mockConnection();
		Mockito.when(stmt.executeQuery(Mockito.anyString())).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getString(Mockito.anyString())).thenReturn("mock-data");
		Mockito.when(rs.getLong(Mockito.anyString())).thenReturn(1l);

		PagilaDao dao = new PagilaDaoImpl();
		Customer c = dao.getCustomerId(1l);
		assertTrue(c != null);
		assertEquals(c.getCustomerId(), 1l);
		assertEquals(c.getFirstName(), "mock-data");
	}

	@Test
	void testSaveCustomer() throws SQLException {
		mockConnection();
		Mockito.when(stmt.executeUpdate(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
		Mockito.when(stmt.getGeneratedKeys()).thenReturn(rs);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getLong(Mockito.anyInt())).thenReturn(1l);

		Customer c = new Customer();
		PagilaDao dao = new PagilaDaoImpl();
		Long id = dao.saveCustomer(c);
		assertEquals(id, 1l);
	}

	@Test
	void testSaveExistingCustomer() throws SQLException {
		Customer c = new Customer();
		c.setCustomerId(1l);
		PagilaDao dao = new PagilaDaoImpl();
		// catch exceptions using assertThrows
		Exception exception = assertThrows(RuntimeException.class, () -> {
			dao.saveCustomer(c);
		});
		String expectedMessage = "Update is not yer implemented.";
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage, expectedMessage);
	}
}
