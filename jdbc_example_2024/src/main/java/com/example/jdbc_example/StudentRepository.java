package com.example.jdbc_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class StudentRowMapper implements RowMapper < Student > {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setUniversity(rs.getString("university"));
            return student;
        }
    }
    public Student findById(long id) {
        return jdbcTemplate.queryForObject("select * from student where id=?", new StudentRowMapper(), id);
    }

    public List<Student> findAll(){
        return jdbcTemplate.query("SELECT * FROM student", new StudentRowMapper());
    }

    public int insertStudent(Student student) {  // Ana 19 Harvard
        String sql = "INSERT INTO student (name, age, university) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getName(),student.getAge(), student.getUniversity());
    }

    public int updateStudent(Student student) {  // Ana 19 Harvard
        String sql = "UPDATE student SET name = ?, age =?, university =? where id= ?";
        return jdbcTemplate.update(sql, student.getName(),student.getAge(), student.getUniversity(), student.getId());
    }
    public int deleteStudent(Long id){
        String sql = "delete from student where id = ?";
        return jdbcTemplate.update(sql,new Object[]{
                id
        });
    }
}
