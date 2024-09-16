// Definición de un Usuario
public class Usuario {
    String nombre;
    String rol;

    Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
