package models;

import org.junit.jupiter.api.Test;

import exceptions.DineroInsuficienteException;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CuentaTest {
    //ACTIVAR REPORTES EN MAVEN: mvn clean verify surefire-report:report
    
    @Test
    void testPersonaCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal(110001.010));
        String esperado = "Andres";
        String realidad = cuenta.getPersona();
        assertNotNull(realidad);
        assertEquals(esperado, realidad, "ERRRORRRR");
        assertTrue(true, "ERRRRRRRRRRROOOOR x2");
    }

    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Jose", new BigDecimal("115651.1312"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(115651.1312 , cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta(){
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));

        assertNotEquals(cuenta, cuenta2);
        //assertEquals(cuenta2, cuenta);
    }

    @Test
    void testDebitoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }
}

