package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface StudentMapper {

    public Student getStudent(String id);
}
