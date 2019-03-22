package com.prashanth.derby.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.prashanth.derby.model.Circle;
@Component
public class DerbyJdbcDaoImpl {
	
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

/*	
	public Circle getCircle(int circleId) { //Without using the Spring JdbcTemplate
		
		Connection conn = null;
		Circle circle = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("Select * from circle where id  = ? ");
			ps.setInt(1, circleId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return circle;
	}
*/
	
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getCircleName(int circleId) {
		String sql = "select name from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, String.class);
	}
	
	public Circle getCircleForID(int circleId) {
		String sql = "select * from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles() {
		String sql = "select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
		
	}
	
//	public void insertCircle(Circle circle) {
//		String sql = "insert into circle values (?, ?)";
//		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
//	}
	
	public void insertCircle(Circle circle) {
		String sql = "insert into circle values (:id, :name)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", circle.getId())
												.addValue("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, sqlParameterSource);		
	}

	public void createTable() {
		String sql = "create table triangle (id integer, name varchar(40))";
		jdbcTemplate.execute(sql);
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}
		
	}
}
