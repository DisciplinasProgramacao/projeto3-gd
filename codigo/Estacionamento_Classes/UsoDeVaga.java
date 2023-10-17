import java.time.LocalDateTime;
import java.time.Duration;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private static final double VALOR_MANOBRISTA = 5.0;
    private static final double VALOR_LAVAGEM = 20.0;
    private static final double VALOR_POLIMENTO = 45.0;
    private static final int TEMPO_LAVAGEM_MIN = 60;
    private static final int TEMPO_POLIMENTO_MIN = 120;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    private boolean manobristaContratado;
    private boolean lavagemContratada;
    private boolean polimentoContratado;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.valorPago = 0.0;
        this.manobristaContratado = false;
        this.lavagemContratada = false;
        this.polimentoContratado = false;
    }

    public void contratarManobrista() {
        this.manobristaContratado = true;
    }

    public void contratarLavagem() {
        this.lavagemContratada = true;
    }

    public void contratarPolimento() {
        this.polimentoContratado = true;
        this.lavagemContratada = true; 
    }

    public int getMes() {
        return entrada.getMonthValue();
    }

    public double sair() {
        this.saida = LocalDateTime.now();
        Duration tempoEsta = Duration.between(entrada, saida);
        long minutos = tempoEsta.toMinutes();
        double aPagar = minutos * VALOR_FRACAO * FRACAO_USO;

        if (manobristaContratado) {
            aPagar += VALOR_MANOBRISTA;
        }
        if (lavagemContratada && minutos >= TEMPO_LAVAGEM_MIN) {
            aPagar += VALOR_LAVAGEM;
        }
        if (polimentoContratado && minutos >= TEMPO_POLIMENTO_MIN) {
            aPagar += VALOR_POLIMENTO;
        }

        if (aPagar > VALOR_MAXIMO) {
            this.valorPago = VALOR_MAXIMO;
        } else {
            this.valorPago = aPagar;
        }

        vaga.disponivel();
        return valorPago;
    }

    public double valorPago() {
        return valorPago;
    }
}
