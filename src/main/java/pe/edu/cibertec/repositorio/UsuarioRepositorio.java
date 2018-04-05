package pe.edu.cibertec.repositorio;

import pe.edu.cibertec.dominio.Usuario;

/**
 *
 * @author Roger P.
 */
public interface UsuarioRepositorio {
    Usuario buscar(Long id);
    void crear (Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminar(Usuario usuario);
    default void eliminar(Long id) {
        eliminar(buscar(id));
    }
}
