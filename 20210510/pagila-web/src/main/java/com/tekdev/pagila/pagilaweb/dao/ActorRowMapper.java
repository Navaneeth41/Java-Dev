package com.tekdev.pagila.pagilaweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.tekdev.pagila.pagilaweb.dto.Actor;


@Component
public class ActorRowMapper implements RowMapper<Actor>{
	
	@Override
	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
	Actor actor = new Actor();
	actor.setActorid(rs.getLong("actor_id"));
	actor.setFirstname(rs.getString("first_name"));
	actor.setLastname(rs.getString("last_name"));
	return actor;

}
}
