package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadMedicoService {

    @Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private MedicoRepository medicoRepository;

    @Transactional
	public MedicoEntity addMedico(Long idEspecialidad, Long idMedico) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle un medico a una especialidad", idEspecialidad);
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(idMedico);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("no se encontro al medico");

		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(idEspecialidad);
		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontro la especialidad");

		especialidadEntity.get().getMedicos().add(medicoEntity.get());
		log.info("Termina proceso de asociarle un medico a la especialidad", idEspecialidad);
		return medicoEntity.get();
	}
}
