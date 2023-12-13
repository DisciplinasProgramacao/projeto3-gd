public class ITurno implements IUsuario {
private Turnos turno;
private String nome = "TURNISTA ";
    public ITurno(int turno){
        switch (turno){
            case 1:
                turno = Turnos.MANHA.ordinal();
                nome = "TURNISTA MANHA";
                break;
            case 2:
                turno = Turnos.TARDE.ordinal();
                nome = "TURNISTA TARDE";
                break;
            case 3:
                turno = Turnos.NOITE.ordinal();
                nome = "TURNISTA NOITE";
        }
    }
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga, turno, 3);
    }

    public String getNome(){return nome;}
}
