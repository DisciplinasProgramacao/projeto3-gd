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
        vaga.sair();
        return MENSALIDADE;
    }
}
