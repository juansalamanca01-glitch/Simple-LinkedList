import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

// Clase Jugador
class Jugador {
    String nombre;
    int puntaje;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }
}

// Nodo modificado para almacenar Jugador
class Node {
    Jugador data; 
    Node next;
    Node prev;

    public Node(Jugador data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Lista doblemente enlazada para Jugadores (ranking)
class ListaEnlazadaDoble {
    private Node head;
    private Node tail;

    public ListaEnlazadaDoble() {
        this.head = null;
        this.tail = null;
    }

    // Insertar jugador manteniendo el orden descendente por puntaje (top 5)
    public void insertarOrdenado(Jugador nuevoJugador) {
        Node nuevoNodo = new Node(nuevoJugador);
        
        // Si la lista está vacía
        if (head == null) {
            head = tail = nuevoNodo;
            return;
        }

        // Si el nuevo puntaje es mayor que el primero
        if (nuevoJugador.puntaje > head.data.puntaje) {
            nuevoNodo.next = head;
            head.prev = nuevoNodo;
            head = nuevoNodo;
        } else {
            Node current = head;
            // Buscar posición de inserción
            while (current.next != null && current.next.data.puntaje >= nuevoJugador.puntaje) {
                current = current.next;
            }
            nuevoNodo.next = current.next;
            nuevoNodo.prev = current;
            if (current.next != null) {
                current.next.prev = nuevoNodo;
            } else {
                tail = nuevoNodo;
            }
            current.next = nuevoNodo;
        }

        // Limitar a 5 jugadores (eliminar el último si hay más de 5)
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count > 5) {
            // Eliminar el último nodo
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null; // en caso de que solo quede uno y se elimine
            }
        }
    }

    // Mostrar el ranking (top 5)
    public void mostrarRanking() {
        if (head == null) {
            System.out.println("No hay jugadores en el ranking.");
            return;
        }
        System.out.println("\n TOP 5 JUGADORES:");
        System.out.println("-------------------");
        Node current = head;
        int posicion = 1;
        while (current != null && posicion <= 5) {
            System.out.println(posicion + ". " + current.data.nombre + " - " + current.data.puntaje + " puntos");
            current = current.next;
            posicion++;
        }
        System.out.println();
    }
}

public class Main {
    private static ListaEnlazadaDoble ranking = new ListaEnlazadaDoble();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        boolean jugarDeNuevo = true;

        while (jugarDeNuevo) {
            System.out.println(" ¡Bienvenido a SUMAS RÁPIDAS! ");
            System.out.print("Ingresa tu nombre de usuario: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                nombre = "Anónimo";
            }

            int puntaje = jugarPartida(nombre);
            System.out.println(" Juego terminado. Tu puntaje final: " + puntaje + " puntos.\n");

            // Agregar al ranking
            ranking.insertarOrdenado(new Jugador(nombre, puntaje));
            ranking.mostrarRanking();

            // Preguntar si quiere jugar de nuevo
            System.out.print("¿Quieres jugar otra partida? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            jugarDeNuevo = respuesta.equals("s") || respuesta.equals("si");
            System.out.println();
        }

        System.out.println(" ¡Gracias por jugar! Hasta pronto.");
        scanner.close();
    }

    public static int jugarPartida(String nombre) {
        int puntaje = 0;
        int nivel = 1;
        int tiempoLimite = 10; // segundos

        System.out.println("\n ¡Comienza la partida, " + nombre + "!");
        System.out.println("Reglas: 5 sumas por nivel. Tiempo inicial: 10s. Cada nivel reduce el tiempo en 2s.");
        System.out.println("¡Cada acierto suma 100 puntos!  Fin inmediato si fallas, te quedas sin tiempo o ingresas algo inválido.\n");

        while (true) {
            System.out.println("🔷 Nivel " + nivel + " | Tiempo por operación: " + tiempoLimite + " segundos");

            for (int i = 1; i <= 5; i++) {
                int a = random.nextInt(50) + 1; // 1 a 50
                int b = random.nextInt(50) + 1;
                int resultadoCorrecto = a + b;

                System.out.print("Suma " + i + ": ¿Cuánto es " + a + " + " + b + "? ");

                // Medir tiempo de respuesta
                long inicio = System.currentTimeMillis();
                try {
                    // Leer entrada
                    String input = scanner.nextLine().trim();
                    long fin = System.currentTimeMillis();
                    double tiempoTranscurrido = (fin - inicio) / 1000.0;

                    // Verificar tiempo
                    if (tiempoTranscurrido > tiempoLimite) {
                        System.out.println(" ¡Tiempo agotado! (" + String.format("%.2f", tiempoTranscurrido) + "s)");
                        return puntaje;
                    }

                    // Validar entrada
                    int respuesta = Integer.parseInt(input);
                    if (respuesta == resultadoCorrecto) {
                        puntaje += 100;
                        System.out.println(" ¡Correcto! +100 puntos. Puntaje actual: " + puntaje);
                    } else {
                        System.out.println(" Incorrecto. La respuesta era " + resultadoCorrecto);
                        return puntaje;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Entrada inválida. Solo se permiten números enteros.");
                    return puntaje;
                }
            }

            // Completó el nivel
            System.out.println(" ¡Nivel " + nivel + " completado!");
            nivel++;
            tiempoLimite -= 2;
            if (tiempoLimite <= 0) tiempoLimite = 1; // Evitar tiempo 0 o negativo
        }
    }
}