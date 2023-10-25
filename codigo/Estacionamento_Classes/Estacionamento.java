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
        criarCargaDados();
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

    private void criarCargaDados() {
        // Criar 3 estacionamentos
        Estacionamento estacionamento1 = new Estacionamento("Estacionamento 1", 5, 10);
        Estacionamento estacionamento2 = new Estacionamento("Estacionamento 2", 4, 8);
        Estacionamento estacionamento3 = new Estacionamento("Estacionamento 3", 6, 12);

        // Criar 6 clientes com um veículo cada
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        Cliente cliente3 = new Cliente("Cliente 3");
        Cliente cliente4 = new Cliente("Cliente 4");
        Cliente cliente5 = new Cliente("Cliente 5");
        Cliente cliente6 = new Cliente("Cliente 6");

        Veiculo veiculo1 = new Veiculo("ABC1234");
        Veiculo veiculo2 = new Veiculo("DEF5678");
        Veiculo veiculo3 = new Veiculo("GHI9101");
        Veiculo veiculo4 = new Veiculo("JKL2345");
        Veiculo veiculo5 = new Veiculo("MNO6789");
        Veiculo veiculo6 = new Veiculo("PQR1234");

        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);
        cliente3.addVeiculo(veiculo3);
        cliente4.addVeiculo(veiculo4);
        cliente5.addVeiculo(veiculo5);
        cliente6.addVeiculo(veiculo6);

        addCliente(cliente1);
        addCliente(cliente2);
        addCliente(cliente3);
        addCliente(cliente4);
        addCliente(cliente5);
        addCliente(cliente6);

        // Distribuir 50 usos de vagas para clientes e estacionamentos
        for (int i = 0; i < 50; i++) {
            estacionar(veiculo1.getPlaca());
            estacionar(veiculo2.getPlaca());
            estacionar(veiculo3.getPlaca());
            estacionar(veiculo4.getPlaca());
            estacionar(veiculo5.getPlaca());
            estacionar(veiculo6.getPlaca());
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

    private Cliente buscarClientePorId(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    private Vaga encontrarVagaLivre() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                return vaga;
            }
        }
        return null;
    }

    private Vaga encontrarVagaOcupada(String placa) {
        for (Vaga vaga : vagas) {
            if (vaga.getPlacaEstacionada() != null && vaga.getPlacaEstacionada().equals(placa)) {
                return vaga;
            }
        }
        return null;
    }

    private double calcularValorPago(String placa) {
        // Lógica de cálculo de valor a ser pago por uso da vaga
        return 10.0; // Exemplo de valor

