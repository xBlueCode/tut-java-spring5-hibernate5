package xbc.tut.service;

import java.util.List;
import xbc.tut.model.Course;

public interface CourseService {

  int add(Course course);

  void update(Course course);

  void delete(Course course);

  Course get(int id);

  List<Course> getAll();

}
