package com.example.demo.dao;

import com.example.demo.Student;
import com.example.demo.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student getById(Integer studentId) {

        String sql = "SELECT id, name FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        //rowMapper 是將資料庫查詢出來的數據轉換成Java Object
        List<Student> list = namedParameterJdbcTemplate.query(sql,map, new StudentRowMapper());

        if (list.size()>0) {
            return list.get(0);
        } else {
            return null;
        }

    }

    @Override
    public void postByStudent(Student student) {
        String sql = "INSERT INTO student (name) VALUES (:studentName )";

        Map<String,Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
    }

    @Override
    public void postByStudentlist(List<Student> studentList) {
        String sql = "INSERT INTO student (name) VALUE (:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i=0; i<studentList.size(); i++){
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
    }

    @Override
    public void putByStudent(Student student) {
        String sql = "UPDATE student SET name = :studentName WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());
        map.put("studentId", student.getId());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteById(Integer studentId) {
        String sql ="DELETE FROM student WHERE id = :studentId";

        Map<String, Integer> map = new HashMap<>();
        map.put("studentId", studentId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
