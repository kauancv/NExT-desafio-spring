package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.CasaDTO;
import coelho.kauan.api.dto.RespostaCasaDTO;
import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.repository.CasaRepository;
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
            RespostaCasaDTO respostaCasaDTO = new RespostaCasaDTO(casa);
            clienteBuscado.get().addCasa(casa);
            casaService.save(casa);
            return ResponseEntity.ok(respostaCasaDTO);
    }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Not Found");
        }
}

     @GetMapping
     public ResponseEntity<?> listarCasas(){
        return ResponseEntity.ok().body(casaService.listarTodasCasas());
     }
}
