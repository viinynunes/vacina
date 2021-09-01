package services;

import entity.Contrato;
import entity.Parcela;

import java.util.Calendar;
import java.util.Date;

public class ContratoServico {

    private ServicoPagamento servicoPagamento;

    public ContratoServico(ServicoPagamento servicoPagamento) {
        this.servicoPagamento = servicoPagamento;
    }

    public void processaContrato(Contrato contrato, int meses){
        double valorBase = contrato.getValor() / meses;

        for (int i = 0; i < meses; i++){
            Date date = addMeses(contrato.getData(), i);
            double valorMes  = valorBase + servicoPagamento.taxaMensal(i, valorBase);
            double valorTotal = valorMes + servicoPagamento.taxaDePagamento(valorMes);

            contrato.addParcela(new Parcela(date, valorTotal));
        }

    }

    private Date addMeses(Date data, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
}
