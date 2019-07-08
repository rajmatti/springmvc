package com.lti.springdemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class JdbcTemplateDemo {

	public static void main(String[] args) {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new oracle.jdbc.driver.OracleDriver());
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        dataSource.setUsername("hr");
        dataSource.setPassword("raj");
         
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
 
        String sqlInsert = "INSERT INTO contact (contact_id,name, email, address, telephone)"
                + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert,1,"Tom", "tomea@mail.com", "USA", "12345");
         
        String sqlUpdate = "UPDATE contact set email=? where name=?";
        jdbcTemplate.update(sqlUpdate, "tomee@mail.com", "Tom");
         
        String sqlSelect = "SELECT * FROM contact";
        List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
 
            public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setName(result.getString("name"));
                contact.setEmail(result.getString("email"));
                contact.setAddress(result.getString("address"));
                contact.setPhone(result.getString("telephone"));
                 
                return contact;
            }
             
        });
         
        for (Contact aContact : listContact) {
            System.out.println(aContact);
        }
         
        String sqlDelete = "DELETE FROM contact1 where name=?";
        jdbcTemplate.update(sqlDelete, "Tom");
	}

}
