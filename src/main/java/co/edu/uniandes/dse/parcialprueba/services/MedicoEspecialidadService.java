package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

    @Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private MedicoRepository medicoRepository;

    public EspecialidadEntity addEspecialidad(Long idMedico, Long idEspecialidad) throws EntityNotFoundException{

        log.info("Inicia proceso de asociarle una especialidad a un medico", idMedico);
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(idMedico);
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(idEspecialidad);

		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("No se encontro el medico");

		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontro la especialidad");

		especialidadEntity.get().getMedicos().add(medicoEntity.get());
		log.info("Termina proceso de asociar una especialidad a un medico", idMedico);
		return especialidadEntity.get();
    }

}
