import entity.Contrato;
import entity.Parcela;
import services.ContratoServico;
import services.PayPalService;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Digite os dados do contrato");
        System.out.println("Numero: ");
        int numero = scanner.nextInt();
        System.out.println("Data: ");
        Date data = sdf.parse(scanner.next());
        System.out.println("Valor: ");
        double valor = scanner.nextDouble();

        Contrato contrato = new Contrato(numero, data, valor);

        System.out.println("Numero de parcelas: ");
        int parcelas = scanner.nextInt();

        ContratoServico servico = new ContratoServico(new PayPalService());

        servico.processaContrato(contrato, parcelas);

        System.out.println("Parcelas");

        for (Parcela x : contrato.getParcelas()){
            System.out.println(x);
        }

        scanner.close();
    }
}
