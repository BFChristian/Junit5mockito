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
        assertNotNull(realidad, "La cuenta no puede ser nula");
        assertEquals(esperado, realidad, "el nombre de la cuenta no es el que se esperaba");
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

    @Test
    void testTransferirDineroCuentas(){
        Cuenta cuenta1 = new Cuenta("Jhon Show", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres Iniesta", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Banco de sol");
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));

        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }

    @Test
    void testRelacionBancoCuentas(){
        Cuenta cuenta1 = new Cuenta("Jhon Show", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres Iniesta", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.setNombre("Banco del sol");
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));

        //AssertAll: podemos hacer varios hacer varios assert a la vez y ver el resultado en de todos en paralelo
        assertAll(()->{assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());},
                  ()->{assertEquals("3000", cuenta1.getSaldo().toPlainString());},
                  ()->{assertEquals(2, banco.getCuentas().size());},
                  ()->{assertEquals("Banco del sol", cuenta1.getBanco().getNombre());},
                  ()->{assertEquals("Andres Iniesta", banco.getCuentas().stream()
                    .filter(c -> c.getPersona().equals("Andres Iniesta"))
                    .findFirst()
                    .get().getPersona());},
                  ()->{assertTrue(banco.getCuentas().stream()
                    //.filter(c -> c.getPersona().equals("Andres Iniesta"))                  ESTA ES UNA OPCION, LA MEJOR ES USANDO "ANYMATCH"
                    //.findFirst().isPresent()); 
                    .anyMatch(c -> c.getPersona().equals("Andres Iniesta")));}
                );
    }
}

