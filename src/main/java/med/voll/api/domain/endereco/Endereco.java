package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Endereco {
        private String logradouro;
        private String bairro;
        private String cep;
        private String cidade;
        private String uf;
        private String complemento;
        private String numero;

        public void atulizarDadosEndereco(EnderecoDTO enderecoDTO) {
                if (enderecoDTO.logradouro() != null) {
                        logradouro = enderecoDTO.logradouro();
                }
                if (enderecoDTO.bairro() != null) {
                        bairro = enderecoDTO.bairro();
                }
                if (enderecoDTO.cep() != null) {
                        cep = enderecoDTO.cep();
                }
                if (enderecoDTO.cidade() != null) {
                        cidade = enderecoDTO.cidade();
                }
                if (enderecoDTO.uf() != null) {
                        uf = enderecoDTO.uf();
                }
                if (enderecoDTO.complemento() != null) {
                        complemento = enderecoDTO.complemento();
                }
                if (enderecoDTO.numero() != null) {
                        numero = enderecoDTO.numero();
                }
        }
}
