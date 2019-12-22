package com.sand;

import com.sand.model.Course;
import com.sand.model.Instructor;
import com.sand.model.InstructorDetail;
import com.sand.model.Review;
import com.sand.model.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMappingExample
{
  public static void main(String[] args)
  {
    SessionFactory sessionFactory = new Configuration().configure()
        .addAnnotatedClass(Instructor.class)
        .addAnnotatedClass(InstructorDetail.class)
        .addAnnotatedClass(Course.class)
        .addAnnotatedClass(Review.class)
        .addAnnotatedClass(Student.class)
        .buildSessionFactory();
    
    try
    {
      //      oneToOneMapping(sessionFactory);
      //      oneToManyMappingBiDirectional(sessionFactory);
      //      oneToManyMappingUniDirectional(sessionFactory);
      manyToManyMapping(sessionFactory);
    }
    finally
    {
      sessionFactory.close();
    }
  }
  
  private static void oneToOneMapping(SessionFactory sessionFactory)
  {
    Instructor instructor = new Instructor("Sand", "Fernando", "sandFernando@gmail.com");
    InstructorDetail instructorDetail = new InstructorDetail("Hibernate Tutorials", "Coding");
    instructor.setInstructorDetail(instructorDetail);
  
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.save(instructor);
    session.getTransaction().commit();
    
    //bidirectional saving
    instructor = new Instructor("SandOTO", "FernandoOTO", "sandFernandoOTO@gmail.com");
    instructorDetail = new InstructorDetail("Hibernate Tutorials OTO", "CodingOTO");
    instructorDetail.setInstructor(instructor);
    
    session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.save(instructorDetail);
    session.getTransaction().commit();
  }
  
  private static void oneToManyMappingBiDirectional(SessionFactory sessionFactory)
  {
    Instructor instructor = new Instructor("SandOTMB", "FernandoOTMB", "sandFernandoOTMB@gmail.com");
    InstructorDetail instructorDetail = new InstructorDetail("Hibernate Tutorials OTMB", "CodingOTMB");
    instructor.setInstructorDetail(instructorDetail);
    
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.save(instructor);
    session.getTransaction().commit();
    
    session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    
    Course music = new Course("Music");
    Course history = new Course("History");
    
    instructor.add(music);
    instructor.add(history);
    
    session.save(music);
    session.save(history);
    
    session.getTransaction().commit();
  }
  
  public static void oneToManyMappingUniDirectional(SessionFactory sessionFactory)
  {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    
    Course hibernateCourse = new Course("Hibernate Course");
    
    hibernateCourse.addReview(new Review("A very good Course"));
    hibernateCourse.addReview(new Review("A very bad Course"));
    
    session.save(hibernateCourse);
    
    session.getTransaction().commit();
  }
  
  public static void manyToManyMapping(SessionFactory sessionFactory)
  {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    
    Course hibernateCourse = new Course("Hibernate Course");
    session.save(hibernateCourse);
    Student studentOne = new Student("SandSMTM", "FernandoSMTM", "sandsMTM@gmail.com");
    Student studentTwo = new Student("Sand2SMTM", "Fernando2SMTM", "sand2sMTM@gmail.com");
    hibernateCourse.addStudent(studentOne);
    hibernateCourse.addStudent(studentTwo);
    
    session.save(studentOne);
    session.save(studentTwo);
    session.getTransaction().commit();
  
    
    
    session = sessionFactory.getCurrentSession();
    session.beginTransaction();
  
    Student oldStudent = session.get(Student.class, studentOne.getId());
  
    Course springCourse = new Course("Spring Course");
    Course angularCourse = new Course("Angular Course");
    springCourse.addStudent(oldStudent);
    angularCourse.addStudent(oldStudent);
  
    session.save(springCourse);
    session.save(angularCourse);
    session.getTransaction().commit();
  }
}
