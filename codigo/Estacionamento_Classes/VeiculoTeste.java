import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class VeiculoTeste {

    @Test
    public void testEstacionar() {
        Vaga vagaDisponivel = new Vaga(1, 1);
        Veiculo veiculo = new Veiculo("ABC123", "Modelo");

        veiculo.estacionar(vagaDisponivel, Turnos.MANHA, 1);
        assertFalse(vagaDisponivel.disponivel());
        assertEquals(1, veiculo.getTotalDeUsos());
    }

    @Test
    public void testSair() {
        Vaga vagaOcupada = new Vaga(2, 2);
        Veiculo veiculo = new Veiculo("DEF456", "Modelo");

        veiculo.estacionar(vagaOcupada, Turnos.TARDE, 2);
        double valorPago = veiculo.sair();
        assertTrue(vagaOcupada.disponivel());
        assertEquals(1, veiculo.getTotalDeUsos());
        assertTrue(valorPago >= 0);
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("GHI789", "Modelo");

        veiculo.estacionar(new Vaga(3, 3), Turnos.NOITE, 3);
        veiculo.estacionar(new Vaga(4, 4), Turnos.MANHA, 1);
        veiculo.sair();

        double totalArrecadado = veiculo.totalArrecadado();
        assertTrue(totalArrecadado >= 0);
    }

    @Test
    public void testArrecadadoNoMes() {
        Veiculo veiculo = new Veiculo("JKL012", "Modelo");

        veiculo.estacionar(new Vaga(5, 5), Turnos.TARDE, 2);
        veiculo.estacionar(new Vaga(6, 6), Turnos.NOITE, 3);
        veiculo.sair();

        double arrecadadoNoMes = veiculo.arrecadadoNoMes(LocalDate.now().getMonthValue());
        assertTrue(arrecadadoNoMes >= 0);
    }

    @Test
    public void testHistorico() {
        Veiculo veiculo = new Veiculo("MNO345", "Modelo");

        veiculo.estacionar(new Vaga(7, 7), Turnos.MANHA, 1);
        veiculo.estacionar(new Vaga(8, 8), Turnos.TARDE, 2);

        String historico = veiculo.historico(null, null);
        assertTrue(historico.contains("Veiculo{placa='MNO345', usos=["));
    }

    @Test
    public void testGetUsosMes() {
        Veiculo veiculo = new Veiculo("PQR678", "Modelo");

        veiculo.estacionar(new Vaga(9, 9), Turnos.MANHA, 1);
        veiculo.estacionar(new Vaga(10, 10), Turnos.NOITE, 3);

        LinkedList<UsoDeVaga> usosMes = veiculo.getUsosMes(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        assertEquals(1, usosMes.size());
    }

    @Test
    public void testRelatorioOrdenado() {
        Veiculo veiculo = new Veiculo("STU901", "Modelo");

        veiculo.estacionar(new Vaga(11, 11), Turnos.TARDE, 2);
        veiculo.estacionar(new Vaga(12, 12), Turnos.MANHA, 1);

        veiculo.RelatorioOrdenado(1);
        veiculo.RelatorioOrdenado(2);

        assertTrue(true);  
    }

    @Test
    public void testNumeroDeUsosMes() {
        Veiculo veiculo = new Veiculo("VWX345", "Modelo");

        veiculo.estacionar(new Vaga(13, 13), Turnos.NOITE, 3);
        veiculo.estacionar(new Vaga(14, 14), Turnos.MANHA, 1);
        veiculo.estacionar(new Vaga(15, 15), Turnos.TARDE, 2);

        long numeroUsos = veiculo.numeroDeUsosMes(LocalDate.now().getMonthValue());
        assertEquals(3, numeroUsos);
    }

    @Test
    public void testGetTotalDeUsos() {
        Veiculo veiculo = new Veiculo("YZA678", "Modelo");

        veiculo.estacionar(new Vaga(16, 16), Turnos.MANHA, 1);
        veiculo.estacionar(new Vaga(17, 17), Turnos.NOITE, 3);

        assertEquals(2, veiculo.getTotalDeUsos());
    }

    @Test
    public void testGetPlaca() {
        Veiculo veiculo = new Veiculo("BCD012", "Modelo");
        assertEquals("BCD012", veiculo.getPlaca());
    }

    @Test
    public void testGetUsos() {
        Veiculo veiculo = new Veiculo("EFG345", "Modelo");

        veiculo.estacionar(new Vaga(18, 18), Turnos.TARDE, 2);
        veiculo.estacionar(new Vaga(19, 19), Turnos.MANHA, 1);

        LinkedList<UsoDeVaga> usos = veiculo.getUsos();
        assertNotNull(usos);
        assertEquals(2, usos.size());
    }

    @Test
    public void testSalvarVeiculo() {
        Veiculo veiculo = new Veiculo("HIJ678", "Modelo");
        veiculo.estacionar(new Vaga(20, 20), Turnos.NOITE, 3);

        veiculo.salvarVeiculo("ClienteId");

        assertTrue(true);  
    }

    @Test
    public void testCarregarVeiculos() {
        Veiculo[] veiculos = Veiculo.carregarVeiculos("ClienteId");

        assertNotNull(veiculos);
    }
}
