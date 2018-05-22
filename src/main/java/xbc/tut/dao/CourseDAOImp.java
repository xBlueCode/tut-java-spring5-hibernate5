package xbc.tut.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xbc.tut.model.Course;

@Repository
public class CourseDAOImp implements CourseDAO{

  @Autowired
  private SessionFactory sessionFactory ;

  @Override
  public int addCourse(Course course) {
    return (int) openSession().save(course);
  }

  @Override
  public void updateCourse(Course course) {
    openSession().update(course);
  }

  @Override
  public void deleteCourse(Course course) {
    openSession().delete(course);
  }

  @Override
  public Course getCourseById(int id) {
    return openSession()
        .get(Course.class, id) ;
  }

  @Override
  public List<Course> getAllCourses() {
    Session session = openSession();

    CriteriaQuery<Course> criteriaQuery= session
        .getCriteriaBuilder()
        .createQuery(Course.class);

    criteriaQuery.from(Course.class);

    return session.createQuery(criteriaQuery).getResultList();
  }

  @Override
  public Session openSession() {
    return sessionFactory.getCurrentSession();
  }
}