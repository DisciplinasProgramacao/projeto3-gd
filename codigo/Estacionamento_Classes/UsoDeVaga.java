import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsoDeVaga {
    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public enum Servico {
        MANOBRISTA(5.0, 0),
        LAVAGEM(20.0, 60),
        POLIMENTO(45.0, 120);

        private double valor;
        private int tempoMinimo;

        Servico(double valor, int tempoMinimo) {
            this.valor = valor;
            this.tempoMinimo = tempoMinimo;
        }

        public double getValor() {
            return valor;
        }

        public int getTempoMinimo() {
            return tempoMinimo;
        }
    }

    private List<Servico> servicosContratados;

    public UsoDeVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }

        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.valorPago = 0.0;
        this.servicosContratados = new ArrayList<>();
    }

    public void contratarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço inválido.");
        }
        servicosContratados.add(servico);
    }

    public double sair() {
        if (saida != null) {
            throw new IllegalStateException("O veículo já saiu da vaga.");
        }

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
        if (saida == null) {
            throw new IllegalStateException("O veículo ainda não saiu da vaga.");
        }

        return valorPago;
    }
    }
