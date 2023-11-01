import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Enum que define os serviços disponíveis.
 */
public enum Servico {
    MANOBRISTA(5.0, 0),
    LAVAGEM(20.0, 60),
    POLIMENTO(45.0, 120);

    private double valor;
    private int tempoMinimo;

    /**
     * Construtor do enum Servico.
     *
     * @param valor      O valor do serviço.
     * @param tempoMinimo O tempo mínimo necessário para o serviço.
     */
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

/**
 * Classe principal que representa o uso de uma vaga de estacionamento.
 */
public class UsoDeVaga {
    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    private List<Servico> servicosContratados;

    /**
     * Construtor da classe UsoDeVaga.
     *
     * @param vaga A vaga de estacionamento utilizada.
     * @throws IllegalArgumentException Se a vaga for nula.
     */
    public UsoDeVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }

        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.valorPago = 0.0;
        this.servicosContratados = new ArrayList<>();
        this.vaga.estacionar();
    }

    /**
     * Método para contratar um serviço.
     *
     * @param servico O serviço a ser contratado.
     * @throws IllegalArgumentException Se o serviço for nulo.
     */
    public void contratarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço inválido.");
        }
        servicosContratados.add(servico);
    }

    /**
     * Método para calcular o valor a pagar e registrar a saída.
     *
     * @return O valor a ser pago.
     * @throws IllegalStateException Se o veículo já saiu da vaga.
     */
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

        vaga.sair();
        return valorPago;
    }

    /**
     * Método para obter o mês de entrada.
     *
     * @return O número do mês da entrada.
     */
    public int getMes() {
        return entrada.getMonthValue();
    }

    /**
     * Método para obter o valor pago.
     *
     * @return O valor pago.
     * @throws IllegalStateException Se o veículo ainda não saiu da vaga.
     */
    public double getValorPago() {
        if (saida == null) {
            throw new IllegalStateException("O veículo ainda não saiu da vaga.");
        }

        return valorPago;
    }

    /**
     * Método para obter a data de entrada.
     *
     * @return A data de entrada.
     */
    public LocalDate getEntrada() {
        return entrada.toLocalDate();
    }

    /**
     * Método para salvar o uso da vaga em um arquivo.
     *
     * @param placa A placa do veículo.
     */
    public void salvarUsoDeVaga(String placa) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UsoVaga.txt", true)) {
            writer.printf(this.vaga.getId() + ";" + this.entrada + ";" + this.saida + ";" + this.valorPago + ";" + placa + "\n");
            System.out.println("Uso de vaga salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático para carregar usos de vaga a partir de um arquivo.
     *
     * @param placa A placa do veículo a ser carregado.
     * @param vagas O array de vagas disponíveis.
     * @return Um array de objetos UsoDeVaga carregados.
     */
    public static UsoDeVaga[] carregarUsos(String placa, Vaga[] vagas) {
        LinkedList<UsoDeVaga> usos = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("UsoVaga.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String vagaID = parts[0];
                    LocalDateTime entrada = LocalDateTime.parse(parts[1]);
                    LocalDateTime saida = LocalDateTime.parse(parts[2]);
                    double valorPago = Double.parseDouble(parts[3]);
                    String plac = parts[4];

                    if (plac.equals(placa)) {
                        for (int i = 0; i < vagas.length; i++) {
                            if (vagaID.equals(vagas[i].getId())) {
                                UsoDeVaga uso = new UsoDeVaga(vagas[i]);
                                uso.entrada = entrada;
                                uso.saida = saida;
                                uso.valorPago = valorPago;
                                usos.add(uso);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsoDeVaga[] u = new UsoDeVaga[usos.size()];
        usos.toArray(u);
        return u;
    }
}
