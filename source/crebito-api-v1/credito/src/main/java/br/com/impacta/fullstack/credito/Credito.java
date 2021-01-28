package br.com.impacta.fullstack.credito;

import java.io.Serializable;
import java.math.BigDecimal;

public class Credito implements Serializable {

    private BigDecimal credito;

    public Credito() {
        super();
    }

    public Credito(BigDecimal credito){
        this.credito = credito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

}
