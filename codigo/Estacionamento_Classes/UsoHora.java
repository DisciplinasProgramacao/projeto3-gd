import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;

public class UsoHora extends UsoDeVaga {

    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    public UsoHora(Vaga vaga) {
        super(vaga);
    }

    @Override
    public double sair() {
        if (this.saida != null) {
            throw new IllegalStateException("O veículo já saiu da vaga.");
        }

        this.saida = LocalDateTime.now();
        Duration tempoEsta = Duration.between(entrada, saida);
        long minutos = tempoEsta.toMinutes();

        if (minutos < 0) {
            throw new IllegalStateException("A data de saída é anterior à data de entrada.");
        }

        double aPagar = minutos * VALOR_FRACAO;

        if (aPagar < 0) {
            throw new IllegalStateException("Erro no cálculo do valor a pagar.");
        }

        this.valorPago = (aPagar > VALOR_MAXIMO) ? VALOR_MAXIMO : aPagar;
        this.valorPago += getValorServicos();
        vaga.sair();
        return valorPago;
    }

    @Override
    public void salvarUsoDeVaga(String placa) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UsoVaga.txt", true))) {
            writer.printf("%s;%s;%s;%s;%s;%d;%s\n",
                    this.vaga.getId(),
                    this.entrada,
                    this.saida,
                    this.valorPago,
                    placa,
                    1,  // Código para indicar UsoHora
                    0);
            System.out.println("Uso de vaga salvo com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar uso de vaga.", e);
        }
    }
}
