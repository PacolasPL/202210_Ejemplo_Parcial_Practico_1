package co.edu.uniandes.dse.parcialejemplo.controllers;

import java.util.List;
import org.modelmapper.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.parcialejemplo.dto.MedicoDTO;
import co.edu.uniandes.dse.parcialejemplo.dto.MedicoDetailDTO;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.services.MedicoService;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private ModelMapper modelMapper;

    //@PostMapping(value = "/medicos/{id}")
    //@ResponseStatus(code =  HttpStatus.OK)
    //public MedicoDetailDTO addMedico(@PathVariable("id") Long id){
       // MedicoEntity medicoEntity = medicoService.getMedico(id);
      //  return modelMapper.map(medicoEntity, new TypeToken<List<MedicoDetailDTO>>(){

        //}.getType());
        
    //}
    @GetMapping 
    @ResponseStatus(code =  HttpStatus.OK)
    public List<MedicoDetailDTO> findAll(){
        List<MedicoEntity> medicoEntity = medicoService.getMedicos();
        return modelMapper.map(medicoEntity, new TypeToken<List<MedicoDetailDTO>>(){

        }.getType());
        
    }

    @PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public MedicoDTO update(@PathVariable("id") Long id, @RequestBody MedicoDTO medicoDTO)
			throws EntityNotFoundException {
		MedicoEntity medicoEntity = medicoService.updateMedico(id, modelMapper.map(medicoDTO, MedicoEntity.class));
		return modelMapper.map(medicoEntity, MedicoDTO.class);
	}

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MedicoDTO create(@RequestBody MedicoDTO medicoDTO){
        MedicoEntity medicoEntity = medicoService.createMedico(modelMapper.map(medicoDTO, MedicoEntity.class)) ;
        return modelMapper.map(medicoEntity, MedicoDTO.class);
        
    }

    @GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public MedicoDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
		MedicoEntity medicoEntity = medicoService.getMedico(id);
		return modelMapper.map(medicoEntity, MedicoDetailDTO.class);
	}
    @DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
		medicoService.deleteMedico(id);
	}

    





}

