package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.SeguroInvalidezDTO;
import coelho.kauan.api.dto.SeguroMoradiaDTO;
import coelho.kauan.api.dto.SeguroVeiculoDTO;
import coelho.kauan.api.dto.SeguroVidaDTO;
import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.entity.Seguro;
import coelho.kauan.domain.entity.Veiculo;
import coelho.kauan.domain.service.CasaService;
import coelho.kauan.domain.service.ClienteService;
import coelho.kauan.domain.service.SeguroService;
import coelho.kauan.domain.service.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/seguro")
public class SeguroController {
    private final SeguroService seguroService;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final CasaService casaService;

    public SeguroController(SeguroService seguroService, ClienteService clienteService, VeiculoService veiculoService, CasaService casaService) {
            this.seguroService = seguroService;
            this.clienteService = clienteService;
            this.veiculoService = veiculoService;
        this.casaService = casaService;
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
                seguro.setAnalise("Não Possui nenhum veiculo!");
            }else {
                int pontuacao_risco = seguro.calculo_pontuacao_risco_veiculo(seguroVeiculoDTO.getQuestoes_riscos(),cliente.get(),veiculo.get());
                seguro.setPontuacao_risco(pontuacao_risco);
                seguro.analise_seguro();
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

    @PostMapping("/vida")
    public ResponseEntity<?> seguro_vida (@RequestBody SeguroVidaDTO seguroVidaDTO){
        Optional<Cliente> cliente = clienteService.buscarClientePorId(seguroVidaDTO.getId_cliente());
        Seguro seguro = new Seguro();
        if (cliente.isPresent()){

             if (cliente.get().getIdade() > 60){
                 seguro.setObservacao("Inelegivel");
                 seguro.setPontuacao_risco(0);
                 seguro.setAnalise("Não cumpri os requisitos do seguro, pois idade é maior que 60!");
             }else {
                 int pontuacao_risco = seguro.pontuacao_vida(seguroVidaDTO.getQuestoes_riscos(),cliente.get());
                 seguro.setPontuacao_risco(pontuacao_risco);
                 seguro.analise_seguro();
                 seguro.setObservacao("Testando Seguro");
             }

            LocalDate dataAtual = LocalDate.now();
            seguro.setData_criacao(dataAtual);
            seguro.setData_validade(dataAtual.plusDays(30));
            seguro.setTipo("Vida");
            seguro.setCliente(cliente.get());
            seguroService.save(seguro);
            return ResponseEntity.ok().body(seguro);
        }else {
            return ResponseEntity.badRequest().body("Erro");
        }
    }

    @PostMapping("/invalidez")
    public ResponseEntity<?> seguro_invalidez (@RequestBody SeguroInvalidezDTO seguroInvalidezDTO){
       Optional<Cliente> cliente = clienteService.buscarClientePorId(seguroInvalidezDTO.getId_cliente());
       Seguro seguro = new Seguro();
        if (cliente.isPresent()){
            Cliente clienteAux = cliente.get();
            if (clienteAux.getIdade()> 60 || clienteAux.getRenda() == 0) {
                seguro.setObservacao("Inelegivel");
                seguro.setPontuacao_risco(0);
                seguro.setAnalise("Não atende aos Criterios do Seguro");
            }else {
                int pontuacao_risco = seguro.pontuacao_invalidez(seguroInvalidezDTO.getQuestoes_riscos(),cliente.get());
                seguro.setPontuacao_risco(pontuacao_risco);
                seguro.analise_seguro();
                seguro.setObservacao("Testando Seguro");
            }
            LocalDate dataAtual = LocalDate.now();
            seguro.setData_criacao(dataAtual);
            seguro.setData_validade(dataAtual.plusDays(30));
            seguro.setTipo("Invalidez");
            seguro.setCliente(cliente.get());
            seguroService.save(seguro);
            return ResponseEntity.ok().body(seguro);
        }else {
            return ResponseEntity.badRequest().body("Erro");
        }
    }

    @PostMapping ("/moradia")
    public ResponseEntity<?> seguro_moradia (@RequestBody SeguroMoradiaDTO seguroMoradiaDTO){
        Optional<Cliente> cliente = clienteService.buscarClientePorId(seguroMoradiaDTO.getId_cliente());
        Optional<Casa> casa = casaService.buscarCasa(seguroMoradiaDTO.getId_casa());
        Seguro seguro = new Seguro();
        if (cliente.isPresent() && casa.isPresent()){
            Cliente clienteAux = cliente.get();
            if (clienteAux.getQuantidade_moradias() == 0){
                seguro.setObservacao("Inelegivel");
                seguro.setPontuacao_risco(0);
                seguro.setAnalise("Não possui Moradia");
            }else {
                int pontuacao_risco = seguro.pontuacao_moradia(seguroMoradiaDTO.getQuestoes_riscos(),cliente.get(),casa.get());
                seguro.setPontuacao_risco(pontuacao_risco);
                seguro.analise_seguro();
                seguro.setObservacao("Testando Seguro");
            }
            LocalDate dataAtual = LocalDate.now();
            seguro.setData_criacao(dataAtual);
            seguro.setData_validade(dataAtual.plusDays(30));
            seguro.setTipo("Moradia");
            seguro.setCliente(cliente.get());
            seguroService.save(seguro);
            return ResponseEntity.ok().body(seguro);
        }else {
            return ResponseEntity.badRequest().body("Erro");
        }
    }

}
