package com.prashanth.derby.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

//Incomplete
public class NamedParameterDaoImpl extends NamedParameterJdbcDaoSupport {
	
	public String getCircleCount() {
		String sql = "select count(*) from circle";
		return this.getNamedParameterJdbcTemplate().query(sql, new CircleResultSetExtractor());
	}
	
	private static final class CircleResultSetExtractor implements ResultSetExtractor<String> {

		@Override
		public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
			return resultSet.getString("1");
		}
	}
}
