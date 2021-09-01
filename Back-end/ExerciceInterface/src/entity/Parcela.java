package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Parcela {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private Date data;
    private Double valor;

    public Parcela(){}

    public Parcela(Date data, Double valor) {
        this.data = data;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return sdf.format(data) + " - " + String.format("%.2f", valor);
    }
}
