package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.StudentService;
import com.mes.entity.Student;

import java.util.List;

public class StudentServiceImp implements StudentService {


	@Override
	public int addStudent(Student stu) {
		return 0;
	}

	@Override
	public void modifyStudent(Student stu) {

	}

	@Override
	public void deleteStudent(Student stu) {

	}

	@Override
	public Student findStudentById(int stuid) {
		return null;
	}

	@Override
	public List<Student> findAllStudent() {
		return null;
	}

	@Override
	public List<Student> findStudentByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
