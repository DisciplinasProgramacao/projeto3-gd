import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum Servico {
    MANOBRISTA(5.0),
    LAVAGEM(20.0, 60),
    POLIMENTO(45.0, 120);

    private final double valor;
    private final int tempoMinimo;

    Servico(double valor, int tempoMinimo) {
        this.valor = valor;
        this.tempoMinimo = tempoMinimo;
    }

    Servico(double valor) {
        this(valor, 0);
    }

    public double getValor() {
        return valor;
    }

    public int getTempoMinimo() {
        return tempoMinimo;
    }
}

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    private List<Servico> servicosContratados;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.valorPago = 0.0;
        this.servicosContratados = new ArrayList<>();
    }

    public void contratarServico(Servico servico) {
        servicosContratados.add(servico);
        if (servico == Servico.POLIMENTO) {
            servicosContratados.add(Servico.LAVAGEM);
        }
    }

    public int getMes() {
        return entrada.getMonthValue();
    }

    public double sair() {
        this.saida = LocalDateTime.now();
        Duration tempoEsta = Duration.between(entrada, saida);
        long minutos = tempoEsta.toMinutes();
        double aPagar = minutos * VALOR_FRACAO * FRACAO_USO;

        for (Servico servico : servicosContratados) {
            if (minutos >= servico.getTempoMinimo()) {
                aPagar += servico.getValor();
            }
        }

        this.valorPago = (aPagar > VALOR_MAXIMO) ? VALOR_MAXIMO : aPagar;

        vaga.disponivel();
        return valorPago;
    }

    public double getValorPago() {
        return valorPago;
    }
}
