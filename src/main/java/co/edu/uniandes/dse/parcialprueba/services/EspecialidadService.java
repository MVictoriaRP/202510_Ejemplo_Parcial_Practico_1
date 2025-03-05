package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
	EspecialidadRepository especialidadRepository;

    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException{
        log.info("Inicia proceso de creacion del medico");
        if(especialidad.getNombre().isEmpty()){
            throw new IllegalOperationException("El nombre no debe estar vacio");
        }
        if(especialidad.getDescripcion().isEmpty()){
            throw new IllegalOperationException("La descripcion no debe estar vacio");
        }
        if(especialidad.getDescripcion().chars().count() <10){
            throw new IllegalOperationException("La descripcion debe tener al menos 10 caracteres");
        }
        return especialidadRepository.save(especialidad);
    }
}
