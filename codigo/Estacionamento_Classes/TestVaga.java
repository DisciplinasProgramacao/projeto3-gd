import java.time.format.DateTimeFormatter;

public class UsoDeVagaTeste {
    public static void main(String[] args) {
        // Criando uma vaga
        Vaga vaga = new Vaga(1, true); // Suponha que a classe Vaga tenha um construtor apropriado

        // Criando uma instância de UsoDeVaga
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);

        // Contratando manobrista, lavagem e polimento
        usoDeVaga.contratarManobrista();
        usoDeVaga.contratarLavagem();
        usoDeVaga.contratarPolimento();

        // Saindo da vaga e calculando o valor a pagar
        double valorPago = usoDeVaga.sair();

        // Obtendo o valor pago e imprimindo
        System.out.println("Valor a ser pago: " + valorPago);

        // Obtendo o valor pago diretamente do método e imprimindo
        System.out.println("Valor pago obtido: " + usoDeVaga.valorPago());

        // Obtendo o mês de entrada e imprimindo
        System.out.println("Mês de entrada: " + usoDeVaga.getMes());

        // Obtendo a data e hora de entrada formatada e imprimindo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Data e hora de entrada: " + usoDeVaga.getEntrada().format(formatter));

        // Supondo que a classe Vaga tenha o método disponivel()
        usoDeVaga.getVaga().disponivel();
    }
}
