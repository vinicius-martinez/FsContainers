package br.com.impacta.fullstack.credito;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CreditoService {

    private static final BigDecimal minValue = new BigDecimal(BigInteger.ONE);
    private static final BigDecimal maxValue = new BigDecimal(BigInteger.TEN);

    public List<Credito> list(){
        int numberOfCredit = new Random().nextInt(10) + 1;
        List<Credito> creditoList = new ArrayList<Credito>(10);
        for (int i = 0; i < numberOfCredit; i++) {
            BigDecimal randomValue = minValue.add(new BigDecimal(Math.random()).multiply(maxValue.subtract(minValue))).setScale(1, BigDecimal.ROUND_HALF_UP);
            Credito credito = new Credito(randomValue);
            creditoList.add(credito);
        }
        System.out.println("creditoList: " + creditoList);
        return creditoList;
    }
}
