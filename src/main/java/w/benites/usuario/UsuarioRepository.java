package w.benites.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import w.benites.topico.Topico;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {


}
