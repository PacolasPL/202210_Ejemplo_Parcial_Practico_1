package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;
    
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico){

        log.info("Inicia proceso de creación de un medico");
        return repository.save(medico);
    }
    
    @Transactional
    public List<MedicoEntity> getMedicos(){
        
        log.info("Inicia el proceso de encontrar a todos los metodos.");
        return repository.findAll();
    }
    @Transactional
    public MedicoEntity getMedico(Long id){
        
        log.info("Inicia el proceso de encontrar a todos los metodos.");
        return repository.getById(id);
    }
    @Transactional
    public MedicoEntity updateMedico(Long medicoId, MedicoEntity medico) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el autor con id = {0}", medicoId);
		Optional<MedicoEntity> medicoEntity = repository.findById(medicoId);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("Valio huevo");
		log.info("Termina proceso de actualizar el autor con id = {0}", medicoId);
		medico.setId(medicoId);
		return repository.save(medico);
	}

    @Transactional
	public void deleteMedico(Long medicoID) throws IllegalOperationException, EntityNotFoundException {
		log.info("Inicia proceso de borrar el autor con id = {0}", medicoID);
		Optional<MedicoEntity> medicoEntity = repository.findById(medicoID);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("No existe ese medico");
		repository.deleteById(medicoID);
		log.info("Termina proceso de borrar el autor con id = {0}", medicoID);
	}
}
