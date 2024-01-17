package med.voll.api.domain.medico.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.domain.medico.dto.CriarMedicoDTO;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medico")
public class MedicoController {
    
    @PostMapping
    public void postMethodName(@RequestBody  @Valid CriarMedicoDTO criarMedicoDTO) {
        System.out.println(criarMedicoDTO);
    }
    

}
