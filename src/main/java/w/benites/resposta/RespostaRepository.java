package w.benites.resposta;

import org.springframework.data.jpa.repository.JpaRepository;
import w.benites.usuario.Usuario;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
