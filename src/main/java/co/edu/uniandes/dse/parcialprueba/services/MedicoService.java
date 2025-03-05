package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

    @Autowired
	MedicoRepository medicoRepository;

    public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException{
        log.info("Inicia proceso de creacion del medico");
        if(medico.getNombre().isEmpty()){
            throw new IllegalOperationException("El nombre no debe estar vacio");
        }
        if(medico.getApellido().isEmpty()){
            throw new IllegalOperationException("El apellido no debe estar vacio");
        }
        if(medico.getRegistroMedico().isEmpty()){
            throw new IllegalOperationException("El registro medico no debe estar vacio");
        }
        if(medico.getRegistroMedico().startsWith("RM")==false){
            throw new IllegalOperationException("El registro medico debe comenzar con RM");
        }
        return medicoRepository.save(medico);
    }
}
