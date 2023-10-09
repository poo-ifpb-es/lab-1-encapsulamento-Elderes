import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {

    @Test
    void testCriacao() throws SaldoNegativoException, LimiteNegativoException {
        ContaBancaria c1 = new ContaBancaria();
        assertEquals(0, c1.getSaldo());

        ContaBancaria c2 = new ContaBancaria(50.0);
        assertEquals(50.0, c2.getSaldo());

        ContaBancaria c3 = new ContaBancaria(50.0, 100.0);
        assertEquals(50.0, c3.getSaldo());
        assertEquals(100.0, c3.getLimiteChequeEspecial());

        assertThrows(SaldoNegativoException.class, () -> {
            new ContaBancaria(-50.0);
        });

        assertThrows(LimiteNegativoException.class, () -> {
            new ContaBancaria(50.0, -1.0);
        });
    }

    @Test
    void testDepositar() throws SaldoNegativoException, LimiteNegativoException {
        ContaBancaria c1 = new ContaBancaria();
        assertDoesNotThrow(() -> c1.depositar(90.0));

        assertEquals(90.0, c1.getSaldo());

        assertThrows(SaldoNegativoException.class, () -> {
            c1.depositar(-90.0);
        });
    }

    @Test
    void testSacar() throws SaldoNegativoException, LimiteNegativoException {
        // Saque normal
        ContaBancaria c1 = new ContaBancaria(90.0);
        assertDoesNotThrow(() -> c1.sacar(90.0));
        assertEquals(0, c1.getSaldo());

        // Saque do cheque especial
        ContaBancaria c2 = new ContaBancaria(0, 100.0);
        assertDoesNotThrow(() -> c2.sacar(90.0));
        assertEquals(-90.0, c2.getSaldo());
        assertDoesNotThrow(() -> c2.sacar(10.0));
        assertEquals(-100.0, c2.getSaldo());

        // Saque além do cheque especial
        assertThrows(SaldoIndisponivelException.class, () -> {
            c2.sacar(90.0);
        });

        // Saque além do saldo disponível com limite
        ContaBancaria c3 = new ContaBancaria(100.0, 100.0);
        assertDoesNotThrow(() -> c3.sacar(110.0));
        assertEquals(-10.0, c3.getSaldo());

        ContaBancaria c4 = new ContaBancaria(100.0, 100.0);
        assertThrows(SaldoIndisponivelException.class, () -> {
            c4.sacar(210.0);
        });
    }

    @Test
    void testDepositarEmSaldoNegativo() throws SaldoNegativoException, LimiteNegativoException {
        ContaBancaria c2 = new ContaBancaria(0, 100.0);
        assertDoesNotThrow(() -> c2.sacar(90.0));
        c2.depositar(110.0);

        assertEquals(20.0, c2.getSaldo());
    }
}
