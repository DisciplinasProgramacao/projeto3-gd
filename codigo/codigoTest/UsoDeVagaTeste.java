import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaTeste {

    @Test
    void testContratarServico() {
        Vaga vaga = new Vaga(1, 1);
        UsoDeVaga uso = new UsoHora(vaga);
        uso.contratarServico(Servico.MANOBRISTA);
        assertTrue(uso.getValorServicos() > 0);
    }

    @Test
    void testSairHora() {
        Vaga vaga = new Vaga(2, 2);
        UsoDeVaga uso = new UsoHora(vaga);
        double valorPago = uso.sair();
        assertTrue(vaga.disponivel());
        assertTrue(valorPago >= 0);
    }

    @Test
    void testSairMensal() {
        Vaga vaga = new Vaga(3, 3);
        UsoDeVaga uso = new UsoMensal(vaga);
        double valorPago = uso.sair();
        assertTrue(vaga.disponivel());
        assertTrue(valorPago >= 0);
    }

    @Test
    void testSairTurno() {
        Vaga vaga = new Vaga(4, 4);
        UsoDeVaga uso = new UsoTurno(vaga, Turnos.NOITE);
        double valorPago = uso.sair();
        assertTrue(vaga.disponivel());
        assertTrue(valorPago >= 0);
    }

    @Test
    void testGetMes() {
        Vaga vaga = new Vaga(5, 5);
        UsoDeVaga uso = new UsoHora(vaga);
        assertEquals(LocalDate.now().getMonthValue(), uso.getMes());
    }

    @Test
    void testGetValorPago() {
        Vaga vaga = new Vaga(6, 6);
        UsoDeVaga uso = new UsoHora(vaga);
        double valorPago = uso.sair();
        assertEquals(valorPago, uso.getValorPago());
    }

    @Test
    void testGetEntrada() {
        Vaga vaga = new Vaga(7, 7);
        UsoDeVaga uso = new UsoHora(vaga);
        assertNotNull(uso.getEntrada());
    }

    @Test
    void testSalvarUsoDeVaga() {
        Vaga vaga = new Vaga(8, 8);
        UsoDeVaga uso = new UsoHora(vaga);
        uso.sair();
        uso.salvarUsoDeVaga("ABC123");

        File file = new File("UsoVaga.txt");
        assertTrue(file.exists());
    }

    @Test
    void testCarregarUsos() {
        Vaga[] vagas = new Vaga[]{new Vaga(9, 9)};
        UsoDeVaga[] usos = UsoDeVaga.carregarUsos("XYZ789", vagas);
        assertNotNull(usos);
    }
}
