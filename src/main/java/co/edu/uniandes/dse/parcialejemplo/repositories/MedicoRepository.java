package co.edu.uniandes.dse.parcialejemplo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long>  {
    
    Optional<MedicoEntity> findById(Long id);
    
}
