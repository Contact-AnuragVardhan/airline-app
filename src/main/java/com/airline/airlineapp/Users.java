package com.airline.airlineapp;

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@RestController
public class Users {

	@Autowired
	private JdbcTemplate jdbcTemplate;
    
    @CrossOrigin
    @PostMapping(path= "/users", consumes = "application/json", produces = "application/json")
    public Map<String,String> method(@RequestBody  Map<String, String> map) throws Exception {
        System.out.println("users endpoint" + map.entrySet());
        String query = "INSERT INTO `airlineapp`.`users`(`fullName`,`email`,`username`,`password`)\r\n" + 
        		"Values(?,?,?,?)";
        int result = jdbcTemplate.update(query, map.get("fullName"),map.get("email"),map.get("username"),map.get("password"));
        System.out.println("Row Inserted with rows " + result);
        Map<String,String> mapOutput = new HashMap<String, String>();
        mapOutput.put("result", String.valueOf(result));
        mapOutput.put("success", String.valueOf(result > 0));
        return mapOutput;
    }
    
    @CrossOrigin
    @PostMapping(path= "/logininfo", consumes = "application/json", produces = "application/json")
    public Map<String,String> logininfo(@RequestBody  Map<String, String> map) throws Exception {
        System.out.println("login endpoint" + map.entrySet());
        String query = "SELECT * FROM airlineapp.users\r\n" + 
        		"where username = ? and password = ?";
        List<Map<String,String>> result = jdbcTemplate.query(query, new Object[]{map.get("username"),map.get("password")}, new RowMapper<Map<String,String>>() {
			public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("fullName", rs.getString("fullName"));
				map.put("email", rs.getString("email"));
				map.put("username", rs.getString("username"));
				//map.put("password", rs.getString("password"));
				return map;
			}
		});
        if(result != null && result.size() > 0)
        {
        	return result.get(0);
        }
        return null;
    }
    
    @CrossOrigin
    @PostMapping(path= "/savebooking", consumes = "application/json", produces = "application/json")
    public Map<String,String> savebooking(@RequestBody  Map<String, String> map) throws Exception {
    	System.out.println("savebooking endpoint" + map.entrySet());
        String query = "INSERT INTO `airlineapp`.`booking`(`bookingToken`,`cityFrom`,`cityTo`,`bookingId`,`arrival`,`departure`,`price`,`userId`)\r\n" + 
        		"VALUES (?,?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(query, map.get("bookingToken"),map.get("cityFrom"),map.get("cityTo"),map.get("bookingId"),map.get("local_arrival"),map.get("local_departure"),
        		map.get("price"),map.get("userId"));
        System.out.println("Row Inserted with rows " + result);
        Map<String,String> mapOutput = new HashMap<String, String>();
        mapOutput.put("result", String.valueOf(result));
        mapOutput.put("success", String.valueOf(result > 0));
        return mapOutput;
    }
    
    @CrossOrigin
    @PostMapping(path= "/getbooking", consumes = "application/json", produces = "application/json")
    public List<Map<String,String>> getbooking(@RequestBody  Map<String, String> map) throws Exception {
        System.out.println("login getbooking " + map.entrySet());
        String query = "SELECT * FROM airlineapp.booking\r\n" + 
        		"where userId = ?";
        List<Map<String,String>> result = jdbcTemplate.query(query, new Object[]{map.get("userId")}, new RowMapper<Map<String,String>>() {
			public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("bookingToken", rs.getString("bookingToken"));
				map.put("cityFrom", rs.getString("cityFrom"));
				map.put("cityTo", rs.getString("cityTo"));
				map.put("bookingId", rs.getString("bookingId"));
				map.put("arrival", rs.getString("arrival"));
				map.put("departure", rs.getString("departure"));
				map.put("price", rs.getString("price"));
				map.put("userId", rs.getString("userId"));
				return map;
			}
		});
        return result;
    }
}
