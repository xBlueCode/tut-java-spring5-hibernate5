package xbc.tut.demo;

import java.util.Comparator;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xbc.tut.config.AppConfig;
import xbc.tut.model.Course;
import xbc.tut.service.CourseService;
import xbc.tut.service.CourseServiceImp;


public class CourseDemo {

  static CourseService courseService ; //= new CourseServiceImp();
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

    courseService = context.getBean("courseService", CourseService.class);

    boolean running = true ;
    while (running) {
      System.out.println("---------------------------------------------------");
      System.out.println("What do you want to do?");
      System.out.print("Add(a), Update(u), Delete(d), Show(s), Show All(sa), Show All ordered by Vol(sav), Quit(q):  ");

      String choice = scanner.next();

      switch (choice){
        case "a":
          addNewCourse();
          break;
        case "u":
          updateCourse();
          break;
        case "d":
          deleteCourse();
          break;
        case "s":
          showCourse();
          break;
        case "sa":
          showAllCourses();
          break;
        case "sav":
          showAllCoursesOrderedByVol();
          break;
        case "q":
          running = !running;
          break;
        default:
          break ;
      }

    }

  }

  private static void addNewCourse() {

    Course course = new Course();

    System.out.println("Add Course: ");

    System.out.print("Course Label (Without spaces): ");
    course.setCourseLabel(scanner.next());

    System.out.print("Course Volume: ");
    course.setVolume(scanner.nextInt());

    courseService.add(course);

  }

  private static void updateCourse() {

    Course course = new Course();

    System.out.println("Update Course: ");

    System.out.print("Course ID: ");
    course.setId(scanner.nextInt());

    System.out.print("Course label (Without spaces): ");
    course.setCourseLabel(scanner.next());

    System.out.print("Course Volume: ");
    course.setVolume(scanner.nextInt());

    System.out.println(course);

    courseService.update(course);

  }

  private static void deleteCourse() {
    Course course = new Course();

    System.out.print("Delete Course with ID: ");
    course.setId(scanner.nextInt());

    courseService.delete(course);
  }

  private static void showCourse() {
    System.out.println("---------------------------------------------------");
    System.out.print("Show Course with the ID: ");

    Course course = courseService.get(scanner.nextInt());

    System.out.println( course!=null ? course : "Course Not Found" );
  }

  private static void showAllCourses() {
    System.out.println("---------------------------------------------------");
    System.out.println("List of Courses");
    courseService.getAll().stream()
        .filter(course -> course != null)
        .forEach(System.out::println);
  }

  private static void showAllCoursesOrderedByVol() {
    System.out.println("---------------------------------------------------");
    System.out.println("List of Courses");
    courseService.getAll().stream()
        .filter(course -> course != null)
        .sorted(Comparator.comparing(Course::getVolume))
        .forEach(System.out::println);
  }

}