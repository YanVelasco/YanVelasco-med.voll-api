package br.com.yanvelasco.api.domain.consultas.validations;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import br.com.yanvelasco.api.infra.exceptions.MedicoDessativadoException;
import lombok.var;

public class ValidadorMedicoAtivo {

    @Autowired
    private MedicoRepository medicoRepository;

    public void execute(ConsultaDTO consultaDTO) {

        if (consultaDTO.idMedico() == null) {
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(consultaDTO.idMedico()).orElseThrow(
                () -> new MedicoDessativadoException("Médico desativado"));
    }
}
