package kr.co.polycube.backendtest.lotto.controller;

import kr.co.polycube.backendtest.lotto.dto.LottoOutput;
import kr.co.polycube.backendtest.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lottos")
@RequiredArgsConstructor
public class LottoController {
    private final LottoService lottoService;

    @PostMapping
    public ResponseEntity<LottoOutput> generateLotto() {
        LottoOutput lottoOutput = lottoService.generateLottoNumbers();
        return ResponseEntity.ok(lottoOutput);
    }
}
