public class Class {
    private String id;
    private String name;
    private int credits;
    private Student head;  // Referencia al primer estudiante (head de la lista enlazada)

    // Constructor
    public Class(String id, String name, int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser positivos.");
        }
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.head = null;
    }

    // Método para inscribir un estudiante (al inicio de la lista)
    public boolean enrollStudent(Student newStudent) {
        // Validar que no exista duplicado por idNumber
        if (findStudentById(newStudent.idNumber) != null) {
            System.out.println("Error: El estudiante con ID " + newStudent.idNumber + " ya está inscrito.");
            return false;
        }

        // Insertar al inicio (más eficiente, O(1))
        newStudent.next = head;
        head = newStudent;
        System.out.println("Estudiante " + newStudent.firstName + " inscrito correctamente.");
        return true;
    }

    // Buscar estudiante por idNumber
    public Student findStudentById(String idNumber) {
        Student current = head;
        while (current != null) {
            if (current.idNumber.equals(idNumber)) {
                return current;
            }
            current = current.next;
        }
        return null; // No encontrado
    }

    // Actualizar datos de un estudiante
    public boolean updateStudent(String idNumber, String firstName, String lastName, int semester, String program) {
        Student student = findStudentById(idNumber);
        if (student != null) {
            student.firstName = firstName;
            student.lastName = lastName;
            student.semester = semester;
            student.program = program;
            System.out.println("Estudiante actualizado correctamente.");
            return true;
        } else {
            System.out.println("Error: Estudiante con ID " + idNumber + " no encontrado.");
            return false;
        }
    }

    // Eliminar estudiante por idNumber
    public boolean removeStudent(String idNumber) {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return false;
        }

        // Caso 1: Eliminar el primer nodo (head)
        if (head.idNumber.equals(idNumber)) {
            head = head.next;
            System.out.println("Estudiante eliminado (era el primero).");
            return true;
        }

        // Caso 2: Eliminar nodo intermedio o último
        Student current = head;
        while (current.next != null && !current.next.idNumber.equals(idNumber)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Estudiante con ID " + idNumber + " no encontrado.");
            return false;
        }

        // Saltar el nodo a eliminar
        current.next = current.next.next;
        System.out.println("Estudiante eliminado correctamente.");
        return true;
    }

    // Listar todos los estudiantes
    public void listStudents() {
        if (head == null) {
            System.out.println("No hay estudiantes inscritos.");
            return;
        }

        System.out.println("Estudiantes inscritos en " + name + ":");
        Student current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current);
            current = current.next;
            count++;
        }
    }

    // Getters (opcional, para mostrar info de la clase)
    public String getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
}