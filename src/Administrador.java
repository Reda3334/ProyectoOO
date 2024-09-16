import java.util.ArrayList;

// Definición del Administrador
public class Administrador extends Usuario {
    ArrayList<Usuario> usuarios;

    Administrador(String nombre) {
        super(nombre, "Administrador");
        usuarios = new ArrayList<>();
    }

    public void crearUsuario(String nombre, String rol) {
        switch (rol) {
            case "Gestor":
                usuarios.add(new Gestor(nombre));
                break;
            case "Programador":
                usuarios.add(new Programador(nombre));
                break;
            default:
                System.out.println("Rol no válido.");
                break;
        }
    }

    public void eliminarUsuario(String nombre) {
        usuarios.removeIf(u -> u.nombre.equals(nombre));
    }

    public void listarUsuarios() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}
