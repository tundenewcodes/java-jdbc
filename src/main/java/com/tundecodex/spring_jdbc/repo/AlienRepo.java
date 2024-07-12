package com.tundecodex.spring_jdbc.repo;

import com.tundecodex.spring_jdbc.model.Alien;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class AlienRepo {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Alien alien) {

        String sql = "insert into alien(id, name, tech) values(?,?,?)";
        int rows = template.update(sql, alien.getId(), alien.getName(), alien.getTech());
        System.out.println(rows + "added!");

    };

    public List<Alien> getAlienList() {
        String sql = "SELECT * FROM alien";

        RowMapper<Alien> rowMapper = new RowMapper<Alien>() {
            @Override
            public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {

                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setTech(rs.getString(3));

                return a;
            }
        };
        List<Alien> aliens = template.query(sql, rowMapper);
        return aliens;
    };

}
