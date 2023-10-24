import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente cliente1;
    private Cliente cliente2;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 2, 3);
        cliente1 = new Cliente("João", "12345");
        cliente2 = new Cliente("Maria", "67890");
        veiculo1 = new Veiculo("ABC123");
        veiculo2 = new Veiculo("DEF456");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
    }

    @Test
    public void testEstacionar() {
        estacionamento.estacionar(veiculo1.getPlaca());
        Vaga[] vagas = estacionamento.getVagas();
        boolean vagaOcupada = false;
        for (Vaga vaga : vagas) {
            if (vaga.isOcupada()) {
                vagaOcupada = true;
                break;
            }
        }
        assertTrue(vagaOcupada, "A vaga não foi ocupada corretamente.");
    }

    @Test
    public void testSair() {
        estacionamento.estacionar(veiculo1.getPlaca());
        double valorPago = estacionamento.sair(veiculo1.getPlaca());
        assertEquals(0.0, valorPago, "O valor pago está incorreto.");
    }

    @Test
    public void testTotalArrecadado() {
        estacionamento.estacionar(veiculo1.getPlaca());
        estacionamento.estacionar(veiculo2.getPlaca());
        estacionamento.sair(veiculo1.getPlaca());
        estacionamento.sair(veiculo2.getPlaca());
        double totalArrecadado = estacionamento.totalArrecadado();
        assertEquals(0.0, totalArrecadado, "O total arrecadado está incorreto.");
    }

    @Test
    public void testArrecadacaoNoMes() {
        estacionamento.estacionar(veiculo1.getPlaca());
        estacionamento.estacionar(veiculo2.getPlaca());
        estacionamento.sair(veiculo1.getPlaca());
        estacionamento.sair(veiculo2.getPlaca());
        double arrecadacaoMes = estacionamento.arrecadacaoNoMes(10);
        assertEquals(0.0, arrecadacaoMes, "A arrecadação do mês está incorreta.");
    }

    @Test
    public void testValorMedioPorUso() {
        double valorMedio = estacionamento.valorMedioPorUso();
        assertEquals(0.0, valorMedio, "O valor médio por uso está incorreto.");
    }

    @Test
    public void testTop5Clientes() {
        String top5 = estacionamento.top5Clientes(10);
        assertEquals("Top 5 clientes do mês 10", top5, "A implementação do top 5 está incompleta.");
    }
}

