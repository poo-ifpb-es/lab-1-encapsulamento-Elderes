import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    void testReservar() {
        Calendar checkin = Calendar.getInstance();
        checkin.set(2023, Calendar.DECEMBER, 10);
        Calendar checkout = Calendar.getInstance();
        checkout.set(2023, Calendar.DECEMBER, 12);

        Reserva r = new Reserva(120.0);
        assertDoesNotThrow(() -> r.reservar(checkin.getTime(), checkout.getTime()));

        assertEquals(2, r.getDiarias());
        assertTrue(r.isReservado());

        // Testar reserva quando o quarto já está reservado
        assertThrows(ReservaInvalidaException.class, () ->
            r.reservar(checkin.getTime(), checkout.getTime())
        );

        // Testar exceção caso checkout seja antes de checkin
        assertThrows(ReservaInvalidaException.class, () ->
            r.reservar(checkout.getTime(), checkin.getTime())
        );
    }

    @Test
    void testCancelarReserva() {
        Reserva r = new Reserva(120.0);

        assertThrows(ReservaInvalidaException.class, r::cancelarReserva);
    }

    @Test
    void testGetValorTotal(){
        // Teste reserva sem desconto
        Calendar checkin = Calendar.getInstance();
        checkin.set(2023, Calendar.DECEMBER, 10);
        Calendar checkout = Calendar.getInstance();
        checkout.set(2023, Calendar.DECEMBER, 12);

        Reserva r = new Reserva(120.0);
        assertDoesNotThrow(() -> r.reservar(checkin.getTime(), checkout.getTime()));

        assertEquals(240.0, r.getValorTotal());

        // Teste reserva com desconto de 5%
        Calendar checkin1 = Calendar.getInstance();
        checkin1.set(2023, Calendar.DECEMBER, 10);
        Calendar checkout1 = Calendar.getInstance();
        checkout1.set(2023, Calendar.DECEMBER, 19);

        Reserva r1 = new Reserva(120.0);
        assertDoesNotThrow(() ->
                r1.reservar(checkin1.getTime(), checkout1.getTime()));

        assertEquals(1026.0, r1.getValorTotal());

        // Teste reserva com desconto de 10%
        Calendar checkin2 = Calendar.getInstance();
        checkin2.set(2023, Calendar.DECEMBER, 10);
        Calendar checkout2 = Calendar.getInstance();
        checkout2.set(2023, Calendar.DECEMBER, 21);

        Reserva r2 = new Reserva(120.0);
        assertDoesNotThrow(() -> r2.reservar(checkin2.getTime(), checkout2.getTime()));

        assertEquals(1188.0, r2.getValorTotal());
    }
}
