import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PostagemTest {

    @Test
    void testCriacao() {
        Postagem p = new Postagem();
        p.setTexto("Abcde");
        p.setAutor("Antonio");
        Date d = new Date();
        p.setData(d);
        assertEquals("Abcde", p.getTexto());
        assertEquals("Antonio", p.getAutor());
        assertSame(d, p.getData());
    }

    @Test
    void testCurtidas() {
        Postagem p = new Postagem();
        assertEquals(0, p.getCurtidas());
        p.curtir();
        assertEquals(1, p.getCurtidas());
        p.descurtir();
        p.descurtir();
        assertEquals(0, p.getCurtidas());
        for (int i = 0; i < 1000; i++) {
            p.curtir();
        }
        assertEquals(1000, p.getCurtidas());
        for (int i = 0; i < 1010; i++) {
            p.descurtir();
        }
        assertEquals(0, p.getCurtidas());
    }
}
