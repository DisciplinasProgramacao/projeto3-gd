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
        double aPagar = minutos * VALOR_FRACAO;

        this.valorPago = (aPagar > VALOR_MAXIMO) ? VALOR_MAXIMO : aPagar;

        vaga.sair();
        return valorPago;
    }
}
