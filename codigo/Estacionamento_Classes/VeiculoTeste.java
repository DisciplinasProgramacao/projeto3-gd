import static org.junit.Assert.*;
import org.junit.Test;

public class VeiculoTeste {

    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga(1, 1);
        veiculo.estacionar(vaga);
        assertTrue(veiculo.totalDeUsos() == 1);
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("XYZ789");
        Vaga vaga1 = new Vaga(2, 5);
        Vaga vaga2 = new Vaga(3, 10);

        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga2);

        double valor1 = veiculo.sair();  
        double valor2 = veiculo.sair();  

        double valorTotalEsperado = valor1 + valor2;

        assertEquals(valorTotalEsperado, veiculo.totalArrecadado(), 0.01);
    }
}
