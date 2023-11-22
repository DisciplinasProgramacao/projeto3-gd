public class ITurno implements IUsuario {
private Turnos turno;
    public ITurno(int turno){
        switch (turno){
            case 1:
                turno = Turnos.MANHA.ordinal();
                break;
            case 2:
                turno = Turnos.TARDE.ordinal();
                break;
            case 3:
                turno = Turnos.NOITE.ordinal();
        }
    }
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga, turno, 3);
    }
}
