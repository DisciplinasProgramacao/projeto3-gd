import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VagaTeste {

    @Test
    public void testEstacionar() {
        Vaga vagaDisponivel = new Vaga(1, 1);
        assertTrue(vagaDisponivel.estacionar());
        assertFalse(vagaDisponivel.disponivel());

        Vaga vagaOcupada = new Vaga(2, 2);
        vagaOcupada.estacionar();
        assertFalse(vagaOcupada.estacionar());
        assertFalse(vagaOcupada.disponivel());
    }

    @Test
    public void testSair() {
        Vaga vagaOcupada = new Vaga(3, 3);
        vagaOcupada.estacionar();
        assertTrue(vagaOcupada.sair());
        assertTrue(vagaOcupada.disponivel());

        Vaga vagaDisponivel = new Vaga(4, 4);
        assertFalse(vagaDisponivel.sair());
        assertTrue(vagaDisponivel.disponivel());
    }

    @Test
    public void testDisponivel() {
        Vaga vagaDisponivel = new Vaga(5, 5);
        assertTrue(vagaDisponivel.disponivel());

        Vaga vagaOcupada = new Vaga(6, 6);
        vagaOcupada.estacionar();
        assertFalse(vagaOcupada.disponivel());
    }

    @Test
    public void testEquals() {
        Vaga vaga1 = new Vaga(7, 7);
        Vaga vaga2 = new Vaga(7, 7);
        Vaga vaga3 = new Vaga(8, 8);

        assertTrue(vaga1.equals(vaga2));
        assertFalse(vaga1.equals(vaga3));
        assertFalse(vaga1.equals("Não é uma Vaga"));
    }

    @Test
    public void testToString() {
        Vaga vagaDisponivel = new Vaga(9, 9);
        assertEquals("Vaga J09 disponível.", vagaDisponivel.toString());

        Vaga vagaOcupada = new Vaga(10, 10);
        vagaOcupada.estacionar();
        assertEquals("Vaga K10 ocupada.", vagaOcupada.toString());
    }
}
