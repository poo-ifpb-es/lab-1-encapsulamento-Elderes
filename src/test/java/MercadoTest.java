import br.edu.ifpb.poo.mercado.Compra;
import br.edu.ifpb.poo.mercado.Item;
import br.edu.ifpb.poo.mercado.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class MercadoTest {
    @Test
    void testCriacao() {
        Compra c = new Compra();
        assertEquals(0, c.getTotal());

        Produto arroz = new Produto(1, "Arroz branco", 4.95, "kg");
        assertEquals(1, arroz.getCodigo());
        assertEquals("Arroz branco", arroz.getNome());
        assertEquals(4.95, arroz.getPreco());
        assertEquals("kg", arroz.getUnidade());

        Item i = new Item(arroz, 3);
        assertEquals(3, i.getQuantidade());
        assertEquals(14.85, i.getValor());
        assertEquals(arroz, i.getProduto());
    }

    @Test
    void testInsercao() {
        Compra c = new Compra();
        Produto arroz = new Produto(1, "Arroz branco", 4.95, "un");
        Produto feijao = new Produto(2, "Feijão carioca", 5.65, "un");
        Produto carne = new Produto(3, "Carne bov diant", 28.95, "kg");
        Produto mussarela = new Produto(4, "Mussarela", 44.55, "kg");

        c.adicionarItem(arroz, 2);
        c.adicionarItem(feijao, 3);
        c.adicionarItem(carne, 2.5);
        c.adicionarItem(mussarela, 0.65);

        assertEquals(7, c.contarItens());
        assertInstanceOf(Item.class, c.getItem(0));
    }

    @Test
    void testRemocao() {
        Compra c = new Compra();
        Produto arroz = new Produto(1, "Arroz branco", 4.95, "un");
        Produto feijao = new Produto(2, "Feijão carioca", 5.65, "un");
        Produto carne = new Produto(3, "Carne bov diant", 28.95, "kg");
        Produto mussarela = new Produto(4, "Mussarela", 44.55, "kg");

        c.adicionarItem(arroz, 2);
        c.adicionarItem(feijao, 3);
        c.adicionarItem(carne, 2.5);
        c.adicionarItem(mussarela, 0.65);

        assertEquals(7, c.contarItens());
        c.removerItem(2);
        assertEquals(4, c.contarItens());
        assertEquals(carne, c.getItem(1).getProduto());
    }

    @Test
    void testCalculo() {
        Compra c = new Compra();
        Produto arroz = new Produto(1, "Arroz branco", 4.95, "un");
        Produto feijao = new Produto(2, "Feijão carioca", 5.65, "un");
        Produto carne = new Produto(3, "Carne bov diant", 28.95, "kg");
        Produto mussarela = new Produto(4, "Mussarela", 44.55, "kg");
        Produto picanha = new Produto(5, "Picanha", 70.99, "kg");
        Produto cupim = new Produto(6, "Cupim", 33.9, "kg");

        c.adicionarItem(arroz, 2);
        c.adicionarItem(feijao, 3);
        c.adicionarItem(carne, 2.5);
        c.adicionarItem(mussarela, 0.65);
        c.adicionarItem(picanha, 0.576);
        c.adicionarItem(cupim, 0.484);

        assertEquals(9.9, c.getItem(0).getValor());
        assertEquals(16.95, c.getItem(1).getValor());
        assertEquals(72.37, c.getItem(2).getValor());
        assertEquals(28.96, c.getItem(3).getValor());
        assertEquals(40.89, c.getItem(4).getValor());
        assertEquals(16.41, c.getItem(5).getValor());

        assertEquals(9, c.contarItens());
        assertEquals(185.48, c.getTotal());
    }
}
