import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class UsoMensalTeste {

    @Test
    public void testSair() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoMensal usoMensalTeste = new UsoMensal(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoMensalTeste.setEntrada(entrada);

        assertEquals(500.0, usoMensalTeste.sair(), 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testSairVeiculoJaSaiu() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoMensal usoMensalTeste = new UsoMensal(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoMensalTeste.setEntrada(entrada);

        usoMensalTeste.sair();
    }

    @Test
    public void testSalvarUsoDeVaga() {
        Vaga vagaTeste = new Vaga(1, 1);
        UsoMensal usoMensalTeste = new UsoMensal(vagaTeste);
        LocalDateTime entrada = LocalDateTime.now();
        usoMensalTeste.setEntrada(entrada);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        usoMensalTeste.salvarUsoDeVaga("ABC123");

        System.setOut(System.out);

        String expectedOutput = "Uso de vaga salvo com sucesso!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
