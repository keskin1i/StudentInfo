
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Student {
     String name;
     int grade;
     String className;

    public Student(String name, int grade, String className) {
        this.name = name;
        this.grade = grade;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getClassName() {
        return className;
    }

    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", className='" + className + '\'' +
                '}';
    }
}

public class StudentInfoSystem {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();

        // Öğrenci listesine öğrencileri ekleyin
        studentList.add(new Student("Ahmet", 85, "A101"));
        studentList.add(new Student("Mehmet", 90, "A102"));
        studentList.add(new Student("Ayşe", 75, "A101"));
        studentList.add(new Student("Fatma", 95, "A103"));
        studentList.add(new Student("Ali", 70, "A102"));

        // Öğrencileri sınıf adına göre grupluyoruz
        Map<String, List<Student>> studentsByClass = studentList.stream()
                .collect(Collectors.groupingBy(Student::getClassName));
        System.out.println("Öğrenciler Sınıfına Göre Gruplandı: " + studentsByClass);

        // Her sınıfın en yüksek notlu öğrencisini buluyoruz
        studentsByClass.forEach((className, students) -> {
            Optional<Student> topStudent = students.stream()
                    .max((s1, s2) -> Integer.compare(s1.getGrade(), s2.getGrade()));
            topStudent.ifPresent(student -> System.out.println(className + " Sınıfının En Yüksek Notlu Öğrencisi: " + student.getName()));
        });

        // Notu en yüksek öğrenciyi buluyoruz
        Optional<Student> topStudentOverall = studentList.stream()
                .max((s1, s2) -> Integer.compare(s1.getGrade(), s2.getGrade()));
        topStudentOverall.ifPresent(student -> System.out.println("Notu En Yüksek Öğrenci: " + student.getName()));

        // Notları 70'in üzerinde olan öğrencileri filtreledik
        List<String> studentsAbove70 = studentList.stream()
                .filter(student -> student.getGrade() > 70)
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("Notları 70'in Üzerinde Olan Öğrenciler: " + studentsAbove70);

// Öğrencileri notlarına göre sıraladık
        List<String> sortedStudents = studentList.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getGrade(), s1.getGrade()))
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("Notlarına Göre Sıralanmış Öğrenciler: " + sortedStudents);

    }
}


