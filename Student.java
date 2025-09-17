public class Student {
    public String firstName;
    public String lastName;
    public String idNumber;  // Identificador único
    public int semester;
    public String program;
    public Student next;     // Puntero al siguiente estudiante

    // Constructor
    public Student(String firstName, String lastName, String idNumber, int semester, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.semester = semester;
        this.program = program;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", semester=" + semester +
                ", program='" + program + '\'' +
                '}';
    }
}