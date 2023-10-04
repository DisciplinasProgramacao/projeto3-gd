
[10:36, 04/10/2023] Pedro Silva: Tô subindo pro lab
[11:49, 04/10/2023] Davi Melo: import java.util.HashMap;
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
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}
