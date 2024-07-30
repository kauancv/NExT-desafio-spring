package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.SeguroVeiculoDTO;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.entity.Seguro;
import coelho.kauan.domain.entity.Veiculo;
import coelho.kauan.domain.service.ClienteService;
import coelho.kauan.domain.service.SeguroService;
import coelho.kauan.domain.service.VeiculoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import java.security.BasicPermission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seguro")
public class SeguroController {
    private final SeguroService seguroService;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;

    public SeguroController(SeguroService seguroService, ClienteService clienteService, VeiculoService veiculoService) {
            this.seguroService = seguroService;
            this.clienteService = clienteService;
            this.veiculoService = veiculoService;

    }

    @PostMapping("/veiculo")
    public ResponseEntity<?> teste (@RequestBody SeguroVeiculoDTO seguroVeiculoDTO){
        Optional<Cliente> cliente = clienteService.buscarClientePorId(seguroVeiculoDTO.getId_cliente());
        Optional<Veiculo> veiculo = veiculoService.buscarVeiculo(seguroVeiculoDTO.getId_veiculo());
        Seguro seguro = new Seguro();

        if (cliente.isPresent() && veiculo.isPresent()){
            if (cliente.get().get_quantidades_veiculos() == 0){
                seguro.setObservacao("Inelegivel");
                seguro.setPontuacao_risco(0);
                seguro.setAnalise("NADA");
            }else {
                int pontuacao_risco = seguro.calculo_pontuacao_risco(seguroVeiculoDTO.getQuestoes_riscos(),cliente.get(),veiculo.get());
                seguro.setPontuacao_risco(pontuacao_risco);
                seguro.analise_veiculo();
                seguro.setObservacao("Testando Seguro");
            }
            LocalDate dataAtual = LocalDate.now();
            seguro.setData_criacao(dataAtual);
            seguro.setData_validade(dataAtual.plusDays(30));
            seguro.setTipo("Automovel");
            seguro.setCliente(cliente.get());
            seguroService.save(seguro);
            return ResponseEntity.ok(seguro);
        }else{
            return ResponseEntity.badRequest().body("Erro");
        }

    }

}
