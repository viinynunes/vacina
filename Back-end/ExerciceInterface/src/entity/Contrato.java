package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contrato {
    private Integer numero;
    private Date data;
    private Double valor;

    private List<Parcela> parcelas = new ArrayList<>();

    public Contrato(){}

    public Contrato(Integer numero, Date data, Double valor) {
        this.numero = numero;
        this.data = data;
        this.valor = valor;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void addParcela(Parcela parcela){
        parcelas.add(parcela);
   }

   public void removeParcela(Parcela parcela){
        parcelas.remove(parcela);
   }


}
