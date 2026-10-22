package br.cefet.acolhimed.repository;

import java.util.Optional;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cefet.acolhimed.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, String> {

	boolean existsByCrm(String crm);

	boolean existsByEmail(String email);

	Optional<Medico> findByEmail(String email);

	Optional<Medico> findByCpf(String cpf);

	@Query("""
			SELECT medico, AVG(avaliacao.nota)
			FROM Avaliacao avaliacao
			JOIN avaliacao.consulta.medico medico
			GROUP BY medico
			HAVING AVG(avaliacao.nota) >= 4.0
			ORDER BY AVG(avaliacao.nota) DESC
			""")
	List<Object[]> buscarMedicosBemAvaliados();

}
