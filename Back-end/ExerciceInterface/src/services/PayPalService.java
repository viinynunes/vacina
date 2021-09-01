package services;

public class PayPalService implements ServicoPagamento {

    @Override
    public double taxaMensal(int meses, double base) {
        return base * 0.01 * meses;
    }

    @Override
    public double taxaDePagamento(double base) {
        return base * 0.02;
    }
}
