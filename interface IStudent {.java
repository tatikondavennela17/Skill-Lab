import java.util.ArrayList;
import java.util.List;

interface IStudent {
    String getStudentId();
    String getName();
    void enrollInCourse(ICourse course);
    void listCourses();
}
class Student implements IStudent {
    private String studentId;
    private String name;
    private List<ICourse> courses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }
    public String getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public void enrollInCourse(ICourse course) {
        courses.add(course);
    }
    public void listCourses() {
        System.out.println("Courses enrolled by " + name + ":");
        for (ICourse course : courses) {
            System.out.println(course.getCourseName());
        }
    }
}
interface ICourse {
    String getCourseId();
    String getCourseName();
    void enrollStudent(IStudent student);
    List<IStudent> getEnrolledStudents();
}
class Course implements ICourse {
    private String courseId;
    private String courseName;
    private List<IStudent> enrolledStudents;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }
    public String getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void enrollStudent(IStudent student) {
        enrolledStudents.add(student);
    }
    public List<IStudent> getEnrolledStudents() {
        return enrolledStudents;
    }
}
interface IEnrollmentService {
    void enrollStudentInCourse(IStudent student, ICourse course);
}
class EnrollmentService implements IEnrollmentService {
    public void enrollStudentInCourse(IStudent student, ICourse course) {
        student.enrollInCourse(course);
        course.enrollStudent(student);
    }
}
 class Main {
    public static void main(String[] args) {
        IStudent student1 = new Student("S001", "Alice");
        IStudent student2 = new Student("S002", "Bob");
        ICourse course1 = new Course("C101", "Mathematics");
        ICourse course2 = new Course("C102", "Computer Science");
        IEnrollmentService enrollmentService = new EnrollmentService();
        enrollmentService.enrollStudentInCourse(student1, course1);
        enrollmentService.enrollStudentInCourse(student1, course2);
        enrollmentService.enrollStudentInCourse(student2, course2);
        student1.listCourses();
        student2.listCourses();
        System.out.println("\nStudents enrolled in " + course2.getCourseName() + ":");
        for (IStudent student : course2.getEnrolledStudents()) {
            System.out.println(student.getName());
        }
    }
}
