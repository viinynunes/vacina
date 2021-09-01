package services;

public interface ServicoPagamento {

    double taxaDePagamento(double base);

    double taxaMensal(int meses, double base);


}
