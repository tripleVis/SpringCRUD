package com.example.SpringCRUD.dao;

import com.example.SpringCRUD.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("Select * from Person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (9,?,?,?)",
                person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name =?, age=?,email=? where id=?",
                updatedPerson.getName(),updatedPerson.getAge(),updatedPerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE ID=?",id);
    }
}
