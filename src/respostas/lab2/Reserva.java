import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {
    /*
    Crie a classe `Reserva`, que pertenceria a um sistema de reservas de hotel.
    A classe possui os atributos: número do quarto, nome do hóspede, valor por
    diária, data de check-in e data de check-out. Implemente métodos para
    reservar, cancelar a reserva, calcular o número de diárias entre as datas
    de reserva.
     */
    private double diaria;
    private Date checkin;
    private Date checkout;

    public Reserva(double valorDiaria) {
        this.diaria = valorDiaria;
    }

    public Reserva(int quarto, String hospede, double valorDiaria) {
        this(valorDiaria);
    }

    public void reservar(Date checkin, Date checkout) throws ReservaInvalidaException {
        if (this.isReservado())
            throw new ReservaInvalidaException();
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void cancelarReserva() throws ReservaInvalidaException {
        if (!this.isReservado())
            throw new ReservaInvalidaException();
        this.checkin = null;
        this.checkout = null;
    }

    public boolean isReservado() {
        return this.checkin != null && this.checkout != null;
    }

    public double getValorTotal() {
        int diarias = this.getDiarias();
        double total = this.getDiarias() * this.diaria;
        if (diarias >= 10) {
            return 0.9 * total;
        } else if (diarias >= 5) {
            return 0.95 * total;
        } else {
            return total;
        }
    }

    public int getDiarias() {
        long diffInMilliseconds = this.checkout.getTime() - this.checkin.getTime();
        return (int) TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
    }
}
