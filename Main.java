public class Main {
    public static void main(String[] args) {
        // Crear una clase
        Class sistemas = new Class("SIS101", "Estructuras de Datos", 3);

        // Crear estudiantes
        Student juan = new Student("Juan", "Salamanca", "202110045678", 3, "Ingeniería de Sistemas");
        Student maria = new Student("María", "Gómez", "202110045679", 3, "Ingeniería de Sistemas");
        Student pedro = new Student("Pedro", "Ramírez", "202110045680", 3, "Ingeniería de Sistemas");

        // Inscribir estudiantes
        sistemas.enrollStudent(juan);
        sistemas.enrollStudent(maria);
        sistemas.enrollStudent(pedro);

        // Intentar inscribir duplicado
        Student juanDuplicado = new Student("Juan", "Salamanca", "202110045678", 3, "Ingeniería de Sistemas");
        sistemas.enrollStudent(juanDuplicado); // Debería fallar

        // Listar estudiantes
        sistemas.listStudents();

        // Actualizar un estudiante
        sistemas.updateStudent("202110045679", "María Alejandra", "Gómez López", 4, "Ingeniería de Sistemas");

        // Eliminar un estudiante
        sistemas.removeStudent("202110045680"); // Eliminar a Pedro

        // Listar de nuevo
        sistemas.listStudents();

        // Intentar eliminar no existente
        sistemas.removeStudent("999999999999");

        // Intentar actualizar no existente
        sistemas.updateStudent("999999999999", "Fantasma", "SinApellido", 1, "Ninguno");
    }
}