package br.com.impacta.fullstack.saldoextrato;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping(("/api/v1/saldoextrato"))
public class SaldoExtratoController {

    private final SaldoExtratoService saldoExtratoService;

    public SaldoExtratoController(SaldoExtratoService saldoExtratoService) {
        this.saldoExtratoService = saldoExtratoService;
    }

    @GetMapping
    public SaldoExtrato get() throws UnknownHostException {
        System.out.println("Hostname: " + InetAddress.getLocalHost().getHostName());
        SaldoExtrato saldoExtrato = saldoExtratoService.get();
        return saldoExtrato;
    }

}
