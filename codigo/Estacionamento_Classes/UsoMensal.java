import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class UsoMensal extends UsoDeVaga {

    private static final double MENSALIDADE = 500.0;

    public UsoMensal(Vaga vaga) {
        super(vaga);
    }

    @Override
    public double sair() {
        this.saida = LocalDateTime.now();
        this.valorPago = MENSALIDADE;
        this.valorPago += getValorServicos();
        vaga.sair();
        return valorPago;
    }
    @Override
    public void salvarUsoDeVaga(String placa) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UsoVaga.txt", true))) {
            writer.printf("%s;%s;%s;%s;%s;%d;%s\n",
                    this.vaga.getId(),
                    this.entrada,
                    this.saida,
                    this.valorPago,
                    placa,
                    2,  // Código para indicar UsoMensal
                    0);
            System.out.println("Uso de vaga salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar uso de vaga: " + e.getMessage());
        }
    }
}
