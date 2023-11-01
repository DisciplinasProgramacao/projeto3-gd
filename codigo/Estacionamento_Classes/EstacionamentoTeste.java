import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstacionamentoTeste {

    private Estacionamento estacionamento;


    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento A", 3, 4);
    }

    @Test
    public void testAddCliente() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        estacionamento.addCliente(cliente1);

        Cliente[] clientes = estacionamento.getClientes();
        assertEquals(1, clientes.length);
        assertEquals(cliente1, clientes[0]);
    }

    @Test
    public void testAddVeiculo() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);

        estacionamento.addVeiculo(veiculo1, "123");
        Veiculo[] veiculos = cliente1.getVeiculo();
        assertEquals(1, veiculos.length);
        assertEquals(veiculo1, veiculos[0]);
    }

    /**
     * 
     */
    @Test
    public void testEstacionar() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, "123");

        estacionamento.estacionar("ABC123");
        Vaga[] vagas = estacionamento.getVagas();
        boolean vagaOcupada = false;
        for (Vaga vaga : vagas) {
            if (!vaga.disponivel()) {
                vagaOcupada = true;
                break;
            }
        }
        assertTrue(vagaOcupada);
    }

    @Test
    public void testSair() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, "123");

        estacionamento.estacionar("ABC123");
        double valorArrecadado = estacionamento.sair("ABC123");

        assertEquals(0.0, valorArrecadado); // Assumindo que o valor retornado ao sair é 0.0 para o exemplo
    }

    @Test
    public void testTotalArrecadado() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, "123");

        estacionamento.estacionar("ABC123");
        double totalArrecadado = estacionamento.totalArrecadado();

        assertEquals(0.0, totalArrecadado); // Assumindo que o valor arrecadado é 0.0 para o exemplo
    }

    @Test
    public void testArrecadacaoNoMes() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, "123");

        estacionamento.estacionar("ABC123");
        estacionamento.sair("ABC123");

        double arrecadacao = estacionamento.arrecadacaoNoMes(10); // Supondo que o mês seja outubro (10)
        assertEquals(0.0, arrecadacao); // Como o veículo ficou apenas um dia, a arrecadação deve ser 0.0
    }

    @Test
    public void testValorMedioPorUso() {
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, "123");

        estacionamento.estacionar("ABC123");
        estacionamento.sair("ABC123");

        double valorMedio = estacionamento.valorMedioPorUso();
        assertEquals(0.0, valorMedio); // Como só houve um uso, o valor médio deve ser 0.0
    }
}
