package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.SeguroVeiculoDTO;
import coelho.kauan.domain.service.SeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.BasicPermission;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seguro")
public class SeguroController {
    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
            this.seguroService = seguroService;
    }

    @PostMapping("/veiculo")
    public ResponseEntity<?> teste (@RequestBody SeguroVeiculoDTO seguroVeiculoDTO){

        List<Boolean> teste = seguroVeiculoDTO.getQuestoes_riscos();
        int calculoBase = 0;
        for (Boolean t : teste){
            if (t){
                calculoBase++;
            }
        }
        System.out.println(calculoBase);
        return ResponseEntity.ok().body(seguroVeiculoDTO);
    }
}
