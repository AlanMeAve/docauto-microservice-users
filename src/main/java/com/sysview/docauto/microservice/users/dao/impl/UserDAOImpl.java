package com.sysview.docauto.microservice.users.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sysview.docauto.microservice.users.dao.UserDAO;
import com.sysview.docauto.microservice.users.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired JdbcTemplate jdbcTemplate;
	
	public class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setName(rs.getString("USUARIOPROPIONOMBRE"));
			user.setFirstSurname(rs.getString("USUARIOPROPIOPATERNO"));
			user.setSecondSurname(rs.getString("USUARIOPROPIOMATERNO"));
			user.setUserName(rs.getString("USUARIOPROPIO"));
			user.setPassword(rs.getString("USUARIOPROPIOPASSWORD"));
			user.setEmail(rs.getString("USUARIOPROPIOCORREO"));
			user.setIsActive(rs.getBoolean("ACTIVO"));
			user.setRole(rs.getString("ROL"));
			return user;
		}
	}
	
	private static final String SQL_SAVE = "INSERT INTO USUARIOPROPIO (USUARIOPROPIO, USUARIOPROPIONOMBRE, USUARIOPROPIOPATERNO, USUARIOPROPIOMATERNO, USUARIOPROPIOPASSWORD, USUARIOPROPIOCORREO, ROL, ACTIVO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public User save(User user) {
		jdbcTemplate.update(SQL_SAVE, user.getUserName(), user.getName(), user.getFirstSurname(), user.getSecondSurname(), user.getPassword(), user.getEmail(), user.getRole(), user.getIsActive());
		return user;
	}

	private static final String SQL_UPDATE = "UPDATE USUARIOPROPIO SET USUARIOPROPIO = ?, USUARIOPROPIONOMBRE = ?, USUARIOPROPIOPATERNO = ?, USUARIOPROPIOMATERNO = ?, USUARIOPROPIOPASSWORD = ?, USUARIOPROPIOCORREO = ?, ROL = ?, ACTIVO = ? WHERE USUARIOPROPIO = ?";
	@Override
	public User update(String username, User user) {
		jdbcTemplate.update(SQL_UPDATE, user.getUserName(), user.getName(), user.getFirstSurname(), user.getSecondSurname(), user.getPassword(), user.getEmail(), user.getRole(), user.getIsActive(), username);
		return user;
	}
	
	private static final String SQL_GET_ALL = "SELECT USUARIOPROPIO, USUARIOPROPIONOMBRE, USUARIOPROPIOPATERNO, USUARIOPROPIOMATERNO, USUARIOPROPIOPASSWORD, USUARIOPROPIOCORREO, ROL, ACTIVO FROM USUARIOPROPIO";
	@Override
	public List<User> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
	}
	
	private static final String SQL_FIND_BY_USERNAME = "SELECT USUARIOPROPIO, USUARIOPROPIONOMBRE, USUARIOPROPIOPATERNO, USUARIOPROPIOMATERNO, USUARIOPROPIOPASSWORD, USUARIOPROPIOCORREO, ROL, ACTIVO FROM USUARIOPROPIO WHERE USUARIOPROPIO = ?";
	@Override
	public User findByUsername(String username) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new UserMapper(), username);
		} catch (EmptyResultDataAccessException ex) {
			log.error("User not found: {0} ", ex);
			return null;
		}
	}
	
	private static final String SQL_DELETE_BY_USERNAME = "DELETE FROM USUARIOPROPIO WHERE USUARIOPROPIO = ?";
	@Override
	public Integer delete(String username) {
		return jdbcTemplate.update(SQL_DELETE_BY_USERNAME, username);
	}

}
