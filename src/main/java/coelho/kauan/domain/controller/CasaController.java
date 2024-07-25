package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.CasaDTO;
import coelho.kauan.api.dto.CasaPutDTO;
import coelho.kauan.api.dto.RespostaCasaDTO;
import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.service.CasaService;
import coelho.kauan.domain.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/casas")
public class CasaController {
    private final CasaService casaService;
    private final ClienteService clienteService;

    public CasaController (CasaService casaService, ClienteService clienteService){
        this.casaService = casaService;
        this.clienteService = clienteService;

    }

    @PostMapping()
    public ResponseEntity<?> cadastrarCasa (@RequestBody CasaDTO casaDTO){
        Optional<Cliente> clienteBuscado = clienteService.buscarClientePorId(casaDTO.getId_cliente());

        if (clienteBuscado.isPresent()){
            Casa casa = new Casa(casaDTO);
            casa.setCliente(clienteBuscado.get());
            // RespostaCasaDTO respostaCasaDTO = new RespostaCasaDTO(casa);
            clienteBuscado.get().addCasa(casa);
            casaService.save(casa);
            return ResponseEntity.ok(casa);
    }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Not Found");
        }
}

     @GetMapping
     public List<Casa> listarCasas(@RequestParam (value = "cep",required = false)String cep){
        if (cep != null && !cep.isEmpty()){
            return casaService.buscarViaCep(cep);
        }else
            return casaService.listarTodasCasas();
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> removerCasa (@PathVariable Long id){
     Optional<Casa>  casa = casaService.buscarCasa(id);
       if (casa.isPresent()){
           casaService.deletarCasa(id);
           return ResponseEntity.status(HttpStatus.OK).body(casa.get());
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Casa Not Found");
       }
     }

     @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCasa (@PathVariable Long id,@RequestBody CasaPutDTO casaPutDTO){
         System.out.println(casaPutDTO.toString());
        Optional<Casa> casa = casaService.buscarCasa(id);
        Optional<Cliente> cliente = clienteService.buscarClientePorId(casaPutDTO.getId_cliente());


        if (casa.isPresent() && cliente.isPresent()) {
            Casa casaAtual = casa.get();
            casaAtual.setStatus(casaPutDTO.getStatus());
            casaAtual.setCliente(cliente.get());
            casaService.save(casaAtual);
            return ResponseEntity.status(HttpStatus.OK).body(casaAtual);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao Atualizar Casa");
        }
     }

}
