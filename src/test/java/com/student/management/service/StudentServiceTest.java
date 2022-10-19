package com.student.management.service;

import com.student.management.exceptions.CustomException;
import com.student.management.model.*;
import com.student.management.payload.request.StudentRequest;
import com.student.management.repo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private MarkRepository markRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @MockBean
    private SubjectRepository subjectRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    /**
     * Method under test: {@link StudentService#addStudent(StudentRequest)}
     */
    @Test
    void testAddStudent() {
        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById((Integer) any())).thenReturn(ofResult);

        Group group1 = new Group();
        group1.setGroupId(123);
        group1.setName("Name");

        Student student = new Student();
        student.setFirstName("Jane");
        student.setGroup(group1);
        student.setLastName("Doe");
        student.setStudentId(123);
        when(studentRepository.save((Student) any())).thenReturn(student);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("Jane");
        studentRequest.setGroupId(123);
        studentRequest.setLastName("Doe");
        assertSame(student, studentService.addStudent(studentRequest));
        verify(groupRepository).findById((Integer) any());
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentService#addStudent(StudentRequest)}
     */
    @Test
    void testAddStudent2() {
        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById((Integer) any())).thenReturn(ofResult);
        when(studentRepository.save((Student) any()))
                .thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("Jane");
        studentRequest.setGroupId(123);
        studentRequest.setLastName("Doe");
        assertThrows(CustomException.class, () -> studentService.addStudent(studentRequest));
        verify(groupRepository).findById((Integer) any());
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentService#addStudent(StudentRequest)}
     */
    @Test
    void testAddStudent3() {
        when(groupRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Student student = new Student();
        student.setFirstName("Jane");
        student.setGroup(group);
        student.setLastName("Doe");
        student.setStudentId(123);
        when(studentRepository.save((Student) any())).thenReturn(student);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("Jane");
        studentRequest.setGroupId(123);
        studentRequest.setLastName("Doe");
        assertThrows(CustomException.class, () -> studentService.addStudent(studentRequest));
        verify(groupRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#deleteStudent(Integer)}
     */
    @Test
    void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById((Integer) any());
        studentService.deleteStudent(1);
        verify(studentRepository).deleteById((Integer) any());
        assertTrue(((Collection<Object>) studentService.findAllStudent()).isEmpty());
    }

    /**
     * Method under test: {@link StudentService#deleteStudent(Integer)}
     */
    @Test
    void testDeleteStudent2() {
        doThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred")).when(studentRepository)
                .deleteById((Integer) any());
        assertThrows(CustomException.class, () -> studentService.deleteStudent(1));
        verify(studentRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#findAllStudent()}
     */
    @Test
    void testFindAllStudent() {
        when(studentRepository.findAll()).thenReturn((Iterable<Student>) mock(Iterable.class));
        studentService.findAllStudent();
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#findAllStudent()}
     */
    @Test
    void testFindAllStudent2() {
        when(studentRepository.findAll()).thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(CustomException.class, () -> studentService.findAllStudent());
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#getMarkByStudentId(int)}
     */
    @Test
    void testGetMarkByStudentId() {
        ArrayList<Mark> markList = new ArrayList<>();
        when(markRepository.findMarkByStudent_StudentId((Integer) any())).thenReturn(markList);
        Object actualMarkByStudentId = studentService.getMarkByStudentId(1);
        assertSame(markList, actualMarkByStudentId);
        assertTrue(((Collection<Object>) actualMarkByStudentId).isEmpty());
        verify(markRepository).findMarkByStudent_StudentId((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getMarkByStudentId(int)}
     */
    @Test
    void testGetMarkByStudentId2() {
        when(markRepository.findMarkByStudent_StudentId((Integer) any()))
                .thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(CustomException.class, () -> studentService.getMarkByStudentId(1));
        verify(markRepository).findMarkByStudent_StudentId((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getStudentsByTeacherId(int)}
     */
    @Test
    void testGetStudentsByTeacherId() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAllByGroup_GroupId((Integer) any())).thenReturn(studentList);

        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Subject subject = new Subject();
        subject.setSubjectId(123);
        subject.setTitle("Dr");

        Teacher teacher = new Teacher();
        teacher.setGroup(group);
        teacher.setSubject(subject);
        teacher.setTeacherId(123);
        Optional<Teacher> ofResult = Optional.of(teacher);
        when(teacherRepository.findById((Integer) any())).thenReturn(ofResult);
        Object actualStudentsByTeacherId = studentService.getStudentsByTeacherId(123);
        assertSame(studentList, actualStudentsByTeacherId);
        assertTrue(((Collection<Object>) actualStudentsByTeacherId).isEmpty());
        verify(studentRepository).findAllByGroup_GroupId((Integer) any());
        verify(teacherRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getStudentsByTeacherId(int)}
     */
    @Test
    void testGetStudentsByTeacherId2() {
        when(studentRepository.findAllByGroup_GroupId((Integer) any()))
                .thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));

        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Subject subject = new Subject();
        subject.setSubjectId(123);
        subject.setTitle("Dr");

        Teacher teacher = new Teacher();
        teacher.setGroup(group);
        teacher.setSubject(subject);
        teacher.setTeacherId(123);
        Optional<Teacher> ofResult = Optional.of(teacher);
        when(teacherRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(CustomException.class, () -> studentService.getStudentsByTeacherId(123));
        verify(studentRepository).findAllByGroup_GroupId((Integer) any());
        verify(teacherRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getStudentsByTeacherId(int)}
     */
    @Test
    void testGetStudentsByTeacherId3() {
        when(studentRepository.findAllByGroup_GroupId((Integer) any())).thenReturn(new ArrayList<>());
        when(teacherRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Subject subject = new Subject();
        subject.setSubjectId(123);
        subject.setTitle("Dr");

        Group group1 = new Group();
        group1.setGroupId(123);
        group1.setName("Name");
        Teacher teacher = mock(Teacher.class);
        when(teacher.getGroup()).thenReturn(group1);
        doNothing().when(teacher).setGroup((Group) any());
        doNothing().when(teacher).setSubject((Subject) any());
        doNothing().when(teacher).setTeacherId((Integer) any());
        teacher.setGroup(group);
        teacher.setSubject(subject);
        teacher.setTeacherId(123);
        assertThrows(CustomException.class, () -> studentService.getStudentsByTeacherId(123));
        verify(teacherRepository).findById((Integer) any());
        verify(teacher).setGroup((Group) any());
        verify(teacher).setSubject((Subject) any());
        verify(teacher).setTeacherId((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getListOfMarksInEachSubjectByStudentId(int)}
     */
    @Test
    void testGetListOfMarksInEachSubjectByStudentId() {
        when(markRepository.findMarkByStudent_StudentId((Integer) any())).thenReturn(new ArrayList<>());
        assertTrue(((Map<Object, Object>) studentService.getListOfMarksInEachSubjectByStudentId(123)).isEmpty());
        verify(markRepository).findMarkByStudent_StudentId((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getListOfMarksInEachSubjectByStudentId(int)}
     */
    @Test
    void testGetListOfMarksInEachSubjectByStudentId4() {
        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Student student = new Student();
        student.setFirstName("Jane");
        student.setGroup(group);
        student.setLastName("Doe");
        student.setStudentId(123);

        Subject subject = new Subject();
        subject.setSubjectId(123);
        subject.setTitle("Dr");

        Subject subject1 = new Subject();
        subject1.setSubjectId(123);
        subject1.setTitle("Dr");
        Mark mark = mock(Mark.class);
        when(mark.getMark()).thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));
        when(mark.getSubject()).thenReturn(subject1);
        doNothing().when(mark).setDate((LocalDateTime) any());
        doNothing().when(mark).setMark((Integer) any());
        doNothing().when(mark).setMarkId((Integer) any());
        doNothing().when(mark).setStudent((Student) any());
        doNothing().when(mark).setSubject((Subject) any());
        mark.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        mark.setMark(1);
        mark.setMarkId(123);
        mark.setStudent(student);
        mark.setSubject(subject);

        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark);
        when(markRepository.findMarkByStudent_StudentId((Integer) any())).thenReturn(markList);

        Subject subject2 = new Subject();
        subject2.setSubjectId(123);
        subject2.setTitle("Dr");
        Optional<Subject> ofResult = Optional.of(subject2);
        when(subjectRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(CustomException.class, () -> studentService.getListOfMarksInEachSubjectByStudentId(123));
        verify(markRepository).findMarkByStudent_StudentId((Integer) any());
        verify(mark).getSubject();
        verify(mark).getMark();
        verify(mark).setDate((LocalDateTime) any());
        verify(mark).setMark((Integer) any());
        verify(mark).setMarkId((Integer) any());
        verify(mark).setStudent((Student) any());
        verify(mark).setSubject((Subject) any());
        verify(subjectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getListOfMarksInEachSubjectByStudentId(int)}
     */
    @Test
    void testGetListOfMarksInEachSubjectByStudentId5() {
        Group group = new Group();
        group.setGroupId(123);
        group.setName("Name");

        Student student = new Student();
        student.setFirstName("Jane");
        student.setGroup(group);
        student.setLastName("Doe");
        student.setStudentId(123);

        Subject subject = new Subject();
        subject.setSubjectId(123);
        subject.setTitle("Dr");

        Subject subject1 = new Subject();
        subject1.setSubjectId(123);
        subject1.setTitle("Dr");
        Mark mark = mock(Mark.class);
        when(mark.getMark()).thenThrow(new CustomException(HttpStatus.CONTINUE, "An error occurred"));
        when(mark.getSubject()).thenReturn(subject1);
        doNothing().when(mark).setDate((LocalDateTime) any());
        doNothing().when(mark).setMark((Integer) any());
        doNothing().when(mark).setMarkId((Integer) any());
        doNothing().when(mark).setStudent((Student) any());
        doNothing().when(mark).setSubject((Subject) any());
        mark.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        mark.setMark(1);
        mark.setMarkId(123);
        mark.setStudent(student);
        mark.setSubject(subject);

        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark);
        when(markRepository.findMarkByStudent_StudentId((Integer) any())).thenReturn(markList);
        when(subjectRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertTrue(((Map<Object, Object>) studentService.getListOfMarksInEachSubjectByStudentId(123)).isEmpty());
        verify(markRepository).findMarkByStudent_StudentId((Integer) any());
        verify(mark).getSubject();
        verify(mark).setDate((LocalDateTime) any());
        verify(mark).setMark((Integer) any());
        verify(mark).setMarkId((Integer) any());
        verify(mark).setStudent((Student) any());
        verify(mark).setSubject((Subject) any());
        verify(subjectRepository).findById((Integer) any());
    }
}

