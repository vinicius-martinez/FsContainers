package br.com.impacta.fullstack.saldoextrato;

import java.io.Serializable;
import java.math.BigDecimal;

public class Debito implements Serializable {

    private BigDecimal debito;

    public Debito() {}

    public Debito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

}
