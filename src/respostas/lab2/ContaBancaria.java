public class ContaBancaria {

    private double saldo;
    private double limite;

    public ContaBancaria() throws SaldoNegativoException, LimiteNegativoException {
        this(0, 0);
    }

    public ContaBancaria(double saldo) throws SaldoNegativoException, LimiteNegativoException {
        this(saldo, 0);
    }

    public ContaBancaria(double saldo, double limiteChequeEspecial) throws SaldoNegativoException, LimiteNegativoException {
        if (saldo < 0)
            throw new SaldoNegativoException();
        if (limiteChequeEspecial < 0)
            throw new LimiteNegativoException();
        this.saldo = saldo;
        this.limite = limiteChequeEspecial;
    }

    public void depositar(double valor) throws SaldoNegativoException {
        if (valor < 0)
            throw new SaldoNegativoException();
        this.saldo += valor;
    }

    public void sacar(double valor) throws SaldoIndisponivelException {
        if (valor > this.getSaldo() + this.getLimiteChequeEspecial())
            throw new SaldoIndisponivelException();
        this.saldo -= valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double getLimiteChequeEspecial() {
        return this.limite;
    }
}
