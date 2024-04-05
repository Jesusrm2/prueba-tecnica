package com.jesus.pruebatec.persona;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PersonaService {
    private final PersonaRespository personaRespository;
    @Transactional
    public PersonaResponse updatePersona(PersonaRequest personaRequest){
        Persona p = Persona.builder()
                .idPersona(personaRequest.idPersona)
                .nombres(personaRequest.getNombres())
                .apellidos(personaRequest.getApellidos())
                .identificacion(personaRequest.getIdentificacion())
                .fechaNcimiento(personaRequest.getFechaNcimiento())
                .build();

        personaRespository.updatePersona(p.idPersona, p.nombres, p.apellidos,p.identificacion,p.fechaNcimiento);
        return new PersonaResponse("Persona actualizada");
    }
    public PersonaDTO getPersona(Integer id) {
        Persona p= personaRespository.findById(id).orElse(null);

        if (p!=null)
        {
            PersonaDTO personaDTO = PersonaDTO.builder()
                    .idPersona(p.idPersona)
                    .nombres(p.nombres)
                    .apellidos(p.apellidos)
                    .identificacion(p.identificacion)
                    .fechaNcimiento(p.fechaNcimiento)
                    .build();
            return personaDTO;
        }
        return null;
    }
}
