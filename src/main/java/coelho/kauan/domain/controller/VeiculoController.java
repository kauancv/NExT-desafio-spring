package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.VeiculoDTO;
import coelho.kauan.api.dto.VeiculoPutDTO;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.entity.Veiculo;
import coelho.kauan.domain.service.ClienteService;
import coelho.kauan.domain.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    public VeiculoController(VeiculoService veiculoService,ClienteService clienteService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody VeiculoDTO veiculoDTO){
        Veiculo veiculo = new Veiculo(veiculoDTO);
         veiculoService.salvar(veiculo);
         return ResponseEntity.ok().body(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody VeiculoPutDTO veiculoPutDTO){
        Optional<Cliente> clienteBuscado = clienteService.buscarClientePorId(veiculoPutDTO.getId_cliente());
        Optional<Veiculo> veiculoBuscado = veiculoService.buscarVeiculo(id);
         if (clienteBuscado.isPresent() && veiculoBuscado.isPresent()){
             Cliente cliente = clienteBuscado.get();
             cliente.addVeiculo(veiculoBuscado.get());
             veiculoBuscado.get().setCliente(cliente);
             veiculoService.salvar(veiculoBuscado.get());
             return ResponseEntity.ok().body(veiculoBuscado.get());
         }else {
             return ResponseEntity.badRequest().body("erro ao associar um veiculo ao um cliente/dono");
         }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Veiculo> veiculo = veiculoService.buscarVeiculo(id);
        if (veiculo.isPresent()){
            veiculoService.deletarVeiculo(id);
            return ResponseEntity.ok().body(veiculo.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não Encontrado");
        }

    }


}