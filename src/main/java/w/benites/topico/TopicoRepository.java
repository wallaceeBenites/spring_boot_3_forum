package w.benites.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {    // Repository para herdar métodos que possuem SQL para executar operações CRUD

}
