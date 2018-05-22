package xbc.tut.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id ;

  @Column(name = "course_label")
  private String courseLabel ;

  @Column(name = "volume")
  private int volume ;

  public Course(){}

  public Course(String courseLabel, int volume) {
    this.courseLabel = courseLabel;
    this.volume = volume;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourseLabel() {
    return courseLabel;
  }

  public void setCourseLabel(String courseLabel) {
    this.courseLabel = courseLabel;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", courseLabel='" + courseLabel + '\'' +
        ", volume='" + volume + '\'' +
        '}';
  }
}
