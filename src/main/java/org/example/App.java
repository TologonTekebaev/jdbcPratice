package org.example;

import org.example.models.Student;
import org.example.repository.StudentRepository;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.createTable();

        Student tologon = new Student("Tologon", (byte) 19);
        Student almaz = new Student("Almaz", (byte) 18);

        studentRepository.findAll().forEach(System.out::println);

    }
}
