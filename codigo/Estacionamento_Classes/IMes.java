public class IMes implements IUsuario{
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga, null, 2);
    }
}
