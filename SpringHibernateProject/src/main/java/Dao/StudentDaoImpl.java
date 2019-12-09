package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Model.Student;

public class StudentDaoImpl implements StudentDao {

	@Autowired
	SessionFactory hibernateSessionFactory;

	public Boolean CreateStudent(Student student) {
		Session session = hibernateSessionFactory.openSession();
		session.save(student);
		session.beginTransaction().commit();
		return session.beginTransaction().wasCommitted();
	}

}
