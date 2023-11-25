import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ClienteTeste {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Davi", 1, 1);
    }

    @Test
    public void testAddVeiculo() {
        Veiculo veiculo = new Veiculo("ABC123", "Car");
        cliente.addVeiculo(veiculo);

        Veiculo[] veiculos = cliente.getVeiculo();
        assertEquals(1, veiculos.length);
        assertEquals(veiculo, veiculos[0]);
    }

    @Test
    public void testPossuiVeiculo() {
        Veiculo veiculo = new Veiculo("ABC123", "Car");
        cliente.addVeiculo(veiculo);

        Veiculo veiculoEncontrado = cliente.possuiVeiculo("ABC123");
        assertNotNull(veiculoEncontrado);
        assertEquals(veiculo, veiculoEncontrado);

        Veiculo veiculoNaoEncontrado = cliente.possuiVeiculo("XYZ789");
        assertNull(veiculoNaoEncontrado);
    }

    @Test
    public void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("ABC123", "Car");
        UsoDeVaga uso = new UsoDeVaga(LocalDate.now(), 10.0);
        veiculo.addUso(uso);
        cliente.addVeiculo(veiculo);

        int totalUsos = cliente.totalDeUsos();
        assertEquals(1, totalUsos);
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        Veiculo veiculo = new Veiculo("ABC123", "Car");
        UsoDeVaga uso = new UsoDeVaga(LocalDate.now(), 10.0);
        veiculo.addUso(uso);
        cliente.addVeiculo(veiculo);

        double arrecadacao = cliente.arrecadadoPorVeiculo("ABC123");
        assertEquals(10.0, arrecadacao);

        double arrecadacaoNaoEncontrada = cliente.arrecadadoPorVeiculo("XYZ789");
        assertEquals(0.0, arrecadacaoNaoEncontrada);
    }

    @Test
    public void testNumeroDeUsosMes() {
        Veiculo veiculo = new Veiculo("ABC123", "Car");
        UsoDeVaga uso1 = new UsoDeVaga(LocalDate.of(2023, 11, 1), 10.0);
        UsoDeVaga uso2 = new UsoDeVaga(LocalDate.of(2023, 11, 15), 15.0);
        veiculo.addUso(uso1);
        veiculo.addUso(uso2);
        cliente.addVeiculo(veiculo);

        float numeroDeUsos = cliente.NumeroDeUsosMes(11);
        assertEquals(2.0, numeroDeUsos);
    }

    @Test
    public void testArrecadadoTotal() {
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        Veiculo veiculo2 = new Veiculo("XYZ789", "Car");
        UsoDeVaga uso1 = new UsoDeVaga(LocalDate.of(2023, 11, 1), 10.0);
        UsoDeVaga uso2 = new UsoDeVaga(LocalDate.of(2023, 11, 15), 15.0);
        veiculo1.addUso(uso1);
        veiculo2.addUso(uso2);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        double totalArrecadado = cliente.arrecadadoTotal();
        assertEquals(25.0, totalArrecadado);
    }

    @Test
    public void testArrecadadoNoMes() {
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        Veiculo veiculo2 = new Veiculo("XYZ789", "Car");
        UsoDeVaga uso1 = new UsoDeVaga(LocalDate.of(2023, 11, 1), 10.0);
        UsoDeVaga uso2 = new UsoDeVaga(LocalDate.of(2023, 11, 15), 15.0);
        veiculo1.addUso(uso1);
        veiculo2.addUso(uso2);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        double arrecadadoNoMes = cliente.arrecadadoNoMes(11);
        assertEquals(25.0, arrecadadoNoMes);
    }

    @Test
    public void testRelatorioTotalDecrescente() {
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        Veiculo veiculo2 = new Veiculo("XYZ789", "Car");
        UsoDeVaga uso1 = new UsoDeVaga(LocalDate.of(2023, 11, 1), 10.0);
        UsoDeVaga uso2 = new UsoDeVaga(LocalDate.of(2023, 11, 15), 15.0);
        veiculo1.addUso(uso1);
        veiculo2.addUso(uso2);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        LinkedList<UsoDeVaga> relatorio = cliente.relatorioTotalDecrescente();
        assertNotNull(relatorio);
        assertEquals(2, relatorio.size());
        assertEquals(15.0, relatorio.getFirst().getValorPago());
    }
}
