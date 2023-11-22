import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsoTurno extends UsoDeVaga {

    private static final double MENSALIDADE_TURNO = 200.0;
    private static final double VALOR_HORA_FORA_TURNO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Turnos turno;

    public UsoTurno(Vaga vaga, Turnos turno) {
        super(vaga);
        this.turno = turno;
    }

    @Override
    public double sair() {
        if (this.saida != null) {
            throw new IllegalStateException("O veículo já saiu da vaga.");
        }

        this.saida = LocalDateTime.now();
        boolean EntradaDentroDoTurno = turno.dentroDoTurno(entrada.toLocalTime());
        boolean SaidaDentroDoTurno = turno.dentroDoTurno(saida.toLocalTime());

        if (EntradaDentroDoTurno == true && SaidaDentroDoTurno == true) {
            this.valorPago = MENSALIDADE_TURNO;
        } else if(EntradaDentroDoTurno == true && SaidaDentroDoTurno == false) {
            double aPagar = getDiferencaHoras(saida.toLocalTime(), turno.getFim())*VALOR_HORA_FORA_TURNO;
            this.valorPago = (aPagar > VALOR_MAXIMO) ? VALOR_MAXIMO : aPagar;
        } else {
            double aPagar = getDiferencaHoras(saida.toLocalTime(), entrada.toLocalTime())*VALOR_HORA_FORA_TURNO;
            this.valorPago = (aPagar > VALOR_MAXIMO) ? VALOR_MAXIMO : aPagar;
        }
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
                    3,  // Código para indicar UsoTurno
                    turno.ordinal()+1);  // Usando o método ordinal para obter o valor numérico do enum
            System.out.println("Uso de vaga salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getDiferencaHoras(LocalTime ini, LocalTime fin){
        return ini.getHour()-fin.getHour();
    }

}