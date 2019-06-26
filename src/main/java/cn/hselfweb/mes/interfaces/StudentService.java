package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Student;

public interface StudentService {
	public int addStudent(Student stu) ;
	public void modifyStudent(Student stu);
	public void deleteStudent(Student stu);	
	public Student findStudentById(int stuid);	
	public List<Student> findAllStudent();	
	public List<Student> findStudentByPropertys(String model,
			String[] propertyName, String[] value);
}
