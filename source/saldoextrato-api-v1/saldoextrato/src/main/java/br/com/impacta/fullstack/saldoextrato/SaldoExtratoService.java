package br.com.impacta.fullstack.saldoextrato;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class SaldoExtratoService {

    @Value(value = "${CREDITO_API_URL}")
    private String CREDITO_API_URL;

    @Value(value = "${DEBITO_API_URL}")
    private String DEBITO_API_URL;

    public SaldoExtrato get(){
        RestTemplate restTemplate = new RestTemplate();
        //Get Credito
        ResponseEntity<Credito[]> creditoResponse = restTemplate.getForEntity(CREDITO_API_URL, Credito[].class);
        System.out.println("CREDITO_API_URL: " + CREDITO_API_URL);
        List<Credito> creditoList = Arrays.asList(creditoResponse.getBody());
        System.out.println("Creditos: " + creditoList);
        SaldoExtrato saldoExtrato = new SaldoExtrato();
        saldoExtrato.setCreditoList(creditoList);
        BigDecimal creditoSum = creditoList.stream().map(Credito::getCredito).reduce(BigDecimal.ZERO, BigDecimal::add);
        //Get Debito
        ResponseEntity<Debito[]> debitoResponse = restTemplate.getForEntity(DEBITO_API_URL, Debito[].class);
        System.out.println("DEBITO_API_URL: " + DEBITO_API_URL);
        List<Debito> debitoList = Arrays.asList(debitoResponse.getBody());
        System.out.println("Debitos: " + debitoList);
        saldoExtrato.setDebitoList(debitoList);
        BigDecimal debitoSum = debitoList.stream().map(Debito::getDebito).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("debitoSum: " + debitoSum);
        //Calcular saldo
        BigDecimal saldo = creditoSum.add(debitoSum);
        saldoExtrato.setSaldo(saldo);
        return saldoExtrato;
    }

}
