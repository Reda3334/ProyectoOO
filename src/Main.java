import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase principal de la aplicación
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creación del administrador predeterminado
        Administrador admin = new Administrador("admin");

        // Loop principal de la aplicación
        while (true) {
            System.out.print("Introduce tu nombre de usuario: ");
            String nombreUsuario = sc.nextLine();

            Usuario usuarioActual = null;

            // Buscar el usuario
            if (nombreUsuario.equals("admin")) {
                usuarioActual = admin;
            } else {
                for (Usuario u : admin.usuarios) {
                    if (u.nombre.equals(nombreUsuario)) {
                        usuarioActual = u;
                        break;
                    }
                }
            }

            if (usuarioActual == null) {
                System.out.println("Usuario no encontrado.");
                continue;
            }

            switch (usuarioActual.rol) {
                case "Administrador":
                    administrar(admin, sc);
                    break;
                case "Gestor":
                    gestionar((Gestor) usuarioActual, admin.usuarios, sc);
                    break;
                case "Programador":
                    programar((Programador) usuarioActual, sc);
                    break;
                default:
                    System.out.println("Rol no válido.");
                    break;
            }
        }
    }

    // Funciones del Administrador
    public static void administrar(Administrador admin, Scanner sc) {
        while (true) {
            System.out.println("\nOpciones del Administrador:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("0. Salir");

            int opcion;
            try{
                opcion = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Introduzca un numero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del nuevo usuario: ");
                    String nombreNuevo = sc.nextLine();
                    System.out.print("Rol del nuevo usuario (Gestor/Programador): ");
                    String rol = sc.nextLine();
                    admin.crearUsuario(nombreNuevo, rol);
                    break;
                case 2:
                    System.out.print("Nombre del usuario a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    admin.eliminarUsuario(nombreEliminar);
                    break;
                case 3:
                    admin.listarUsuarios();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Funciones del Gestor
    public static void gestionar(Gestor gestor, ArrayList<Usuario> usuarios, Scanner sc) {
        while (true) {
            System.out.println("\nOpciones del Gestor:");
            System.out.println("1. Crear proyecto");
            System.out.println("2. Listar proyectos");
            System.out.println("3. Listar programadores");
            System.out.println("4. Asignar programador a proyecto");
            System.out.println("5. Listar programadores de un proyecto");
            System.out.println("6. Crear tarea en un proyecto");
            System.out.println("0. Salir");

            int opcion;
            try{
                opcion = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Introduzca un numero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyecto = sc.nextLine();
                    gestor.crearProyecto(nombreProyecto);
                    break;
                case 2:
                    gestor.listarProyectos();
                    break;
                case 3:
                    gestor.listarProgramadores(usuarios);
                    break;
                case 4:
                    System.out.print("Nombre del proyecto: ");
                    String proyectoNombre = sc.nextLine();
                    Proyecto proyecto = null;
                    for (Proyecto p : gestor.proyectos) {
                        if (p.nombre.equals(proyectoNombre)) {
                            proyecto = p;
                            break;
                        }
                    }
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre del programador: ");
                    String programadorNombre = sc.nextLine();
                    Programador programador = null;
                    for (Usuario u : usuarios) {
                        if (u instanceof Programador && u.nombre.equals(programadorNombre)) {
                            programador = (Programador) u;
                            break;
                        }
                    }
                    if (programador == null) {
                        System.out.println("Programador no encontrado.");
                        break;
                    }
                    proyecto.asignarProgramador(programador);
                    programador.asignarProyecto(proyecto);
                    break;
                case 5:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyectoListar = sc.nextLine();
                    Proyecto proyectoListar = null;
                    for (Proyecto p : gestor.proyectos) {
                        if (p.nombre.equals(nombreProyectoListar)) {
                            proyectoListar = p;
                            break;
                        }
                    }
                    if (proyectoListar == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.println("Programadores del proyecto:");
                    for (Programador p : proyectoListar.programadores) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyectoTarea = sc.nextLine();
                    Proyecto proyectoTarea = null;
                    for (Proyecto p : gestor.proyectos) {
                        if (p.nombre.equals(nombreProyectoTarea)) {
                            proyectoTarea = p;
                            break;
                        }
                    }
                    if (proyectoTarea == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre del programador: ");
                    String nombreProgramadorTarea = sc.nextLine();
                    Programador programadorTarea = null;
                    for (Programador p : proyectoTarea.programadores) {
                        if (p.nombre.equals(nombreProgramadorTarea)) {
                            programadorTarea = p;
                            break;
                        }
                    }
                    if (programadorTarea == null) {
                        System.out.println("Programador no encontrado en este proyecto.");
                        break;
                    }
                    System.out.print("Nombre de la tarea: ");
                    String nombreTarea = sc.nextLine();
                    proyectoTarea.asignarTarea(programadorTarea, new Tarea(nombreTarea));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Funciones del Programador
    public static void programar(Programador programador, Scanner sc) {
        while (true) {
            System.out.println("\nOpciones del Programador:");
            System.out.println("1. Consultar proyectos asignados");
            System.out.println("2. Consultar tareas asignadas en un proyecto");
            System.out.println("3. Marcar tarea como finalizada");
            System.out.println("0. Salir");

            int opcion;
            try{
                opcion = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Introduzca un numero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    programador.listarProyectos();
                    break;
                case 2:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyecto = sc.nextLine();
                    Proyecto proyecto = null;
                    for (Proyecto p : programador.proyectos) {
                        if (p.nombre.equals(nombreProyecto)) {
                            proyecto = p;
                            break;
                        }
                    }
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    programador.listarTareas(proyecto);
                    break;
                case 3:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyectoFinalizar = sc.nextLine();
                    Proyecto proyectoFinalizar = null;
                    for (Proyecto p : programador.proyectos) {
                        if (p.nombre.equals(nombreProyectoFinalizar)) {
                            proyectoFinalizar = p;
                            break;
                        }
                    }
                    if (proyectoFinalizar == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre de la tarea: ");
                    String nombreTareaFinalizar = sc.nextLine();
                    for (Tarea t : proyectoFinalizar.obtenerTareas(programador)) {
                        if (t.nombre.equals(nombreTareaFinalizar)) {
                            t.marcarFinalizada();
                            System.out.println("Tarea marcada como finalizada.");
                            break;
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
