import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("João", "123456");
        veiculo1 = new Veiculo("ABC123", 100.0);
        veiculo2 = new Veiculo("XYZ789", 150.0);
    }

    @Test
    public void testGetNome() {
        assertEquals("João", cliente.getNome());
    }

    @Test
    public void testGetId() {
        assertEquals("123456", cliente.getId());
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculo1);
        assertEquals(veiculo1, cliente.getVeiculo()[0]);
    }

    @Test
    public void testPossuiVeiculo() {
        cliente.addVeiculo(veiculo1);
        assertNotNull(cliente.possuiVeiculo("ABC123"));
        assertNull(cliente.possuiVeiculo("PLACA_INEXISTENTE"));
    }

    @Test
    public void testTotalDeUsos() {
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(0, cliente.totalDeUsos());
        veiculo1.aumentarTotalUsos();
        veiculo2.aumentarTotalUsos();
        assertEquals(2, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        cliente.addVeiculo(veiculo1);
        assertEquals(0.0, cliente.arrecadadoPorVeiculo("ABC123"));
        veiculo1.registrarUso();
        assertEquals(100.0, cliente.arrecadadoPorVeiculo("ABC123"));
    }

    @Test
    public void testArrecadadoTotal() {
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(0.0, cliente.arrecadadoTotal());
        veiculo1.registrarUso();
        veiculo2.registrarUso();
        assertEquals(250.0, cliente.arrecadadoTotal());
    }

    @Test
    public void testArrecadadoNoMes() {
        cliente.addVeiculo(veiculo1);
        veiculo1.registrarUso();
        veiculo1.registrarUso();
        assertEquals(0.0, cliente.arrecadadoNoMes(1));
        assertEquals(200.0, cliente.arrecadadoNoMes(2));
    }
}
