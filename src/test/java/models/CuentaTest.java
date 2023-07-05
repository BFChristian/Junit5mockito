package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CuentaTest {
    //ACTIVAR REPORTES EN MAVEN: mvn clean verify surefire-report:report
    
    @Test
    void testPersonaCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal(110001.010));
        String esperado = "Andres";
        String realidad = cuenta.getPersona();
        assertEquals(esperado, realidad, "ERRRORRRR");
        assertTrue(true, "ERRRRRRRRRRROOOOR x2");
    }

    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Jose", new BigDecimal("115651.1312"));
        assertEquals(115651.1312 , cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta(){
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));

        assertNotEquals(cuenta, cuenta2);
    }
}
