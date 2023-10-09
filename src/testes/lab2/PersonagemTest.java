import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonagemTest {

    @Test
    void testCriacao() {
        Personagem p1 = new Personagem("Fulano");
        assertEquals(0, p1.getExperiencia());
        assertEquals(100, p1.getSaude());
    }

    @Test
    void testSofrerDanoERecuperarSaude() {
        Personagem p1 = new Personagem("Fulano");
        p1.sofrerDano();
        assertEquals(90, p1.getSaude());
        p1.sofrerDano();
        assertEquals(80, p1.getSaude());

        p1.ganharExperiencia();
        p1.sofrerDano();
        assertEquals(71, p1.getSaude());
        assertEquals(100, p1.getExperiencia());

        for (int i = 0; i < 9; i++) {
            p1.ganharExperiencia();
        }
        assertEquals(1000, p1.getExperiencia());
        p1.ganharExperiencia();
        assertEquals(1000, p1.getExperiencia());

        p1.sofrerDano();
        assertEquals(71, p1.getSaude());

        p1.recuperarSaude();
        assertEquals(100, p1.getSaude());

        p1.recuperarSaude();
        assertEquals(100, p1.getSaude());
    }
}
