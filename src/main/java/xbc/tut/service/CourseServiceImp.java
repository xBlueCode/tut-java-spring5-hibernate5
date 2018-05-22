package xbc.tut.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xbc.tut.dao.CourseDAO;
import xbc.tut.model.Course;

@Service ("courseService")
@Transactional(readOnly = true)
public class CourseServiceImp implements CourseService{

  @Autowired
  private CourseDAO courseDAO ;

  @Override
  @Transactional(readOnly = false)
  public int add(Course course) {
    return courseDAO.addCourse(course);
  }

  @Override
  @Transactional(readOnly = false)
  public void update(Course course) {
    courseDAO.updateCourse(course);
  }

  @Override
  @Transactional(readOnly = false)
  public void delete(Course course) {
    courseDAO.deleteCourse(course);
  }

  @Override
  public Course get(int id) {
    return courseDAO.getCourseById(id);
  }

  @Override
  public List<Course> getAll() {
    return courseDAO.getAllCourses();
  }

}
