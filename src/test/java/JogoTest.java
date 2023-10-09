import br.edu.ifpb.poo.jogo.Arma;
import br.edu.ifpb.poo.jogo.ArmaLancavel;
import br.edu.ifpb.poo.jogo.ArmaPerfurante;
import br.edu.ifpb.poo.jogo.ArmaRecarregavel;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {
    @Test
    void testCriacao() {
        ArmaLancavel armaLancavel = new ArmaLancavel("Lanca", 50, 30);
        ArmaPerfurante armaPerfurante = new ArmaPerfurante("Faca", 25);
        ArmaRecarregavel armaRecarregavel = new ArmaRecarregavel("Arco-e-flecha", 15, 15, 10);

        assertEquals("Lanca", armaLancavel.getNome());
        assertEquals("Faca", armaPerfurante.getNome());
        assertEquals("Arco-e-flecha", armaRecarregavel.getNome());
        assertEquals(50, armaLancavel.getDano());
        assertEquals(25, armaPerfurante.getDano());
        assertEquals(15, armaRecarregavel.getDano());
    }

    @Test
    void testHeranca() {
        assertDoesNotThrow(() -> {
            Arma a1 = new ArmaLancavel("Lanca", 50, 30);
            assertInstanceOf(Arma.class, a1);
            assertEquals("Lanca", a1.getNome());
            assertEquals(50, a1.getDano());

            Arma a2 = new ArmaPerfurante("Faca", 25);
            assertInstanceOf(Arma.class, a2);
            assertEquals("Faca", a2.getNome());
            assertEquals(25, a2.getDano());

            Arma a3 = new ArmaRecarregavel("Arco-e-flecha", 15, 15, 10);
            assertInstanceOf(Arma.class, a3);
            assertInstanceOf(ArmaLancavel.class, a3);
            assertEquals("Arco-e-flecha", a3.getNome());
            assertEquals(15, a3.getDano());
        });
    }

    @Test
    void testArmaLancavel() {
        ArmaLancavel armaLancavel = new ArmaLancavel("Lanca", 50, 20);

        armaLancavel.setRandomGenerator(new Random(1));
        armaLancavel.setChanceDeAcerto(30);
        assertEquals(30, armaLancavel.getChanceDeAcerto());
        assertEquals(0, armaLancavel.disparar());
        assertEquals(0, armaLancavel.disparar());
        assertEquals(0, armaLancavel.disparar());
        assertEquals(50, armaLancavel.disparar());

        armaLancavel.setRandomGenerator(new Random(2));
        assertEquals(50, armaLancavel.disparar());
    }

    @Test
    void testArmaPerfurante() {
        ArmaPerfurante armaPerfurante = new ArmaPerfurante("Faca", 25);

        assertEquals(25, armaPerfurante.disparar());
    }

    @Test
    void testArmaRecarregavel() {
        ArmaRecarregavel armaRecarregavel = new ArmaRecarregavel("Arco-e-flecha", 15, 15, 3);
        armaRecarregavel.setRandomGenerator(new Random(1));

        assertEquals(0, armaRecarregavel.disparar());
        assertEquals(2, armaRecarregavel.getProjeteis());
        assertEquals(0, armaRecarregavel.disparar());
        assertEquals(1, armaRecarregavel.getProjeteis());
        assertEquals(0, armaRecarregavel.disparar());
        assertEquals(0, armaRecarregavel.getProjeteis());
        assertEquals(0, armaRecarregavel.disparar());
        assertEquals(0, armaRecarregavel.disparar());
        assertEquals(0, armaRecarregavel.disparar());
    }
}
