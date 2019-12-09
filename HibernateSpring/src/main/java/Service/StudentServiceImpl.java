package Service;

import org.springframework.beans.factory.annotation.Autowired;

import Dao.StudentDao;
import Model.Student;

public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	public Boolean createStudent(Student student) {
		return studentDao.createStudent(student);
	}

}
