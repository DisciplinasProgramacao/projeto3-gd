import java.time.LocalTime;

public enum Turnos {
    MANHA, TARDE, NOITE;

    private String nome;
    private LocalTime inicio;
    private LocalTime fim;

    static {
        MANHA.nome = "Manhã";
        MANHA.inicio = LocalTime.of(8, 0);
        MANHA.fim = LocalTime.of(12, 0);

        TARDE.nome = "Tarde";
        TARDE.inicio = LocalTime.of(12, 1);
        TARDE.fim = LocalTime.of(18, 0);

        NOITE.nome = "Noite";
        NOITE.inicio = LocalTime.of(18, 1);
        NOITE.fim = LocalTime.of(23, 59);
    }

    public String getNome() {
        return nome;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public boolean dentroDoTurno(LocalTime time) {
        return !time.isBefore(inicio) && !time.isAfter(fim);
    }
}