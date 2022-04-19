package org.example.repository;

import org.example.configuration.DatabaseConnection;
import org.example.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final Connection connection;

    public StudentRepository() throws SQLException {
        connection = new DatabaseConnection().getConnection();
    }
    // create table

    public void createTable() throws SQLException {
        String query = """
                create table if not exists students(
                id serial primary key,
                name varchar(50) not null,
                age smallint not null);
                """;
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // find all
    public List<Student> findAll() {
        String query = "select * from students;";
        List<Student> allStudents = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte(3));
                allStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }
    //find by id
    public Student findById(Long id) {
        String query = "select * from student where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Does not exist.");
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte(3));

                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //save

    public void save(Student student) throws SQLException {
        String query = """
                 insert into students (name, age value(?,?)
                 """;
         try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
             preparedStatement.setString(1, student.getName());
             preparedStatement.setByte(2, student.getAge());
             preparedStatement.execute();
         }catch (SQLException e){
             throw new RuntimeException((e));
         }
      }

    // delete by id

        public void deleteById(Long id) {
            String query = " delete drom students where id = ?; ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    //update

    public void update(Long id, Student newStudent){
        String query = """
                uodate students 
                set name = ?,
                set age = ?,
                where id = ?
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, newStudent.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        //delete all

        public void deleteAll() {
            String query = "truncate table students";
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
                System.out.println("You have successfully deleted all students in students table");
            } catch (SQLException e) {
                throw  new RuntimeException(e);
            }
       }
}
