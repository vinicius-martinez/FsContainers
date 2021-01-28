package br.com.impacta.fullstack.saldoextrato;

import java.math.BigDecimal;
import java.util.List;

public class SaldoExtrato {

    private List<Credito> creditoList;
    private List<Debito> debitoList;
    private BigDecimal saldo;

    public List<Debito> getDebitoList() {
        return debitoList;
    }

    public void setDebitoList(List<Debito> debitoList) {
        this.debitoList = debitoList;
    }

    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
