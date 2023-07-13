package models;

import java.math.BigDecimal;

import exceptions.DineroInsuficienteException;

public class Cuenta {
    private String Persona;
    private BigDecimal saldo;
    
    public Cuenta(String persona, BigDecimal saldo) {
        Persona = persona;
        this.saldo = saldo;
    }
    public String getPersona() {
        return Persona;
    }
    public void setPersona(String persona) {
        Persona = persona;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero Insuficiente");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);
    }
}
