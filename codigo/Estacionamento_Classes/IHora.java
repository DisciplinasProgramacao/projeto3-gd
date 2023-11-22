public class IHora implements IUsuario {
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga);
    }

}
