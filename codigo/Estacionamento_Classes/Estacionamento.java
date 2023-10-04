import java.util.HashMap;
import java.util.Map;

public class Estacionamento {

    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private Map<String, Double> arrecadacaoPorPlaca;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new Cliente[0];
        this.vagas = new Vaga[0];
        this.arrecadacaoPorPlaca = new HashMap<>();
        gerarVagas();
    }

	public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = buscarClientePorId(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

	public void addCliente(Cliente cliente) {
        Cliente[] novosClientes = new Cliente[clientes.length + 1];
        System.arraycopy(clientes, 0, novosClientes, 0, clientes.length);
        novosClientes[clientes.length] = cliente;
        clientes = novosClientes;
    }

	private void gerarVagas() {
        char fila = 'A';
        int numeroVaga = 1;
        int totalVagas = quantFileiras * vagasPorFileira;
        vagas = new Vaga[totalVagas];
        for (int i = 0; i < totalVagas; i++) {
            vagas[i] = new Vaga(String.format("%c%02d", fila, numeroVaga++));
            if (numeroVaga > vagasPorFileira) {
                numeroVaga = 1;
                fila++;
            }
        }
    }

	public void estacionar(String placa) {
        Vaga vagaLivre = encontrarVagaLivre();
        if (vagaLivre != null) {
            vagaLivre.ocupar();
            arrecadacaoPorPlaca.put(placa, 0.0);
        }
    }

	public double sair(String placa) {
        Vaga vagaOcupada = encontrarVagaOcupada(placa);
        if (vagaOcupada != null) {
            vagaOcupada.liberar();
            double valorPago = calcularValorPago(placa);
            arrecadacaoPorPlaca.put(placa, arrecadacaoPorPlaca.get(placa) + valorPago);
            return valorPago;
        }
        return 0.0;
    }

	public double totalArrecadado() {
        double total = 0.0;
        for (double valor : arrecadacaoPorPlaca.values()) {
            total += valor;
        }
        return total;
    }

	public double arrecadacaoNoMes(int mes) {
        double totalMes = 0.0;
        for (Map.Entry<String, Double> entry : arrecadacaoPorPlaca.entrySet()) {
            String chave = entry.getKey();
            int mesEntrada = Integer.parseInt(chave.substring(4, 6));
            if (mesEntrada == mes) {
                totalMes += entry.getValue();
            }
        }
        return totalMes;
    }

	public double valorMedioPorUso() {
        if (arrecadacaoPorPlaca.size() == 0) {
            return 0.0;
        }
        return totalArrecadado() / arrecadacaoPorPlaca.size();
    }

	public String top5Clientes(int mes) {
        // Falta colocar a implementacao
        return "Top 5 clientes do mês " + mes;
    }

}
