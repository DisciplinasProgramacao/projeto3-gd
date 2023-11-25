import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class UsoHoraTeste {

    @Test
    public void testSair() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoHora usoHoraTeste = new UsoHora(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoHoraTeste.setEntrada(entrada);
        LocalDateTime saida = entrada.plusMinutes(30);
        usoHoraTeste.setSaida(saida);

        assertEquals(15.0, usoHoraTeste.sair(), 0.001);
    }

    @Test
    public void testSairComValorMaximo() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoHora usoHoraTeste = new UsoHora(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoHoraTeste.setEntrada(entrada);
        LocalDateTime saida = entrada.plusMinutes(80);
        usoHoraTeste.setSaida(saida);

        assertEquals(50.0, usoHoraTeste.sair(), 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testSairVeiculoJaSaiu() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoHora usoHoraTeste = new UsoHora(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoHoraTeste.setEntrada(entrada);
        LocalDateTime saida = entrada.plusMinutes(30);
        usoHoraTeste.setSaida(saida);

        usoHoraTeste.sair();
    }

    @Test
    public void testSalvarUsoDeVaga() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoHora usoHoraTeste = new UsoHora(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoHoraTeste.setEntrada(entrada);
        LocalDateTime saida = entrada.plusMinutes(30);
        usoHoraTeste.setSaida(saida);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        usoHoraTeste.salvarUsoDeVaga("ABC123");

        System.setOut(System.out);

        String expectedOutput = "Uso de vaga salvo com sucesso!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
