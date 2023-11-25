import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsoTurnoTeste {

    @Test
    public void testSairDentroDoTurno() {
        Vaga vagaTeste = new Vaga(1, 1);
        Turnos turnoTeste = Turnos.MANHA;  
        UsoTurno usoTurnoTeste = new UsoTurno(vagaTeste, turnoTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoTurnoTeste.setEntrada(entrada);

        LocalDateTime saida = LocalDateTime.of(entrada.toLocalDate(), turnoTeste.getFim().minusHours(1));
        usoTurnoTeste.setSaida(saida);

        double expectedValorPago = 200.0;
        assertEquals(expectedValorPago, usoTurnoTeste.sair(), 0.001);
    }

    @Test
    public void testSairForaDoTurno() {
        Vaga vagaTeste = new Vaga(1, 1);
        Turnos turnoTeste = Turnos.MANHA;  
        UsoTurno usoTurnoTeste = new UsoTurno(vagaTeste, turnoTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoTurnoTeste.setEntrada(entrada);

        LocalDateTime saida = LocalDateTime.of(entrada.toLocalDate(), turnoTeste.getFim().plusHours(1));
        usoTurnoTeste.setSaida(saida);

        double expectedValorPago = 200.0 + (1 * 4.0);  
        assertEquals(expectedValorPago, usoTurnoTeste.sair(), 0.001);
    }

    @Test
    public void testSairDentroEForaDoTurno() {
        Vaga vagaTeste = new Vaga(1, 1);
        Turnos turnoTeste = Turnos.MANHA;  
        UsoTurno usoTurnoTeste = new UsoTurno(vagaTeste, turnoTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoTurnoTeste.setEntrada(entrada);

        LocalDateTime saida = LocalDateTime.of(entrada.toLocalDate(), turnoTeste.getFim().minusHours(1).plusMinutes(30));
        usoTurnoTeste.setSaida(saida);

        double expectedValorPago = 200.0 + (1 * 4.0);  
        assertEquals(expectedValorPago, usoTurnoTeste.sair(), 0.001);
    }

    @Test
    public void testSalvarUsoDeVaga() {
        Vaga vagaTeste = new Vaga(1, 1);
        Turnos turnoTeste = Turnos.MANHA;  
        UsoTurno usoTurnoTeste = new UsoTurno(vagaTeste, turnoTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoTurnoTeste.setEntrada(entrada);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        usoTurnoTeste.salvarUsoDeVaga("ABC123");

        System.setOut(System.out);

        String expectedOutput = "Uso de vaga salvo com sucesso!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
