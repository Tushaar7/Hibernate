package Dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import Model.Student;

public class StudentDaoImpl implements StudentDao {		
		
	@Autowired
	SessionFactory hibernateSessionFactory;

		public Boolean createStudent(Student student) {
			Session session = hibernateSessionFactory.openSession();
			
			session.save(student);
			session.beginTransaction().commit();
			return session.beginTransaction().wasCommitted();
	}

}
