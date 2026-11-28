public abstract class Equipamentos {

    private final String codigoPatrimonio;
    private final String nome;
    private  final String marca;
    private final String modelo;
    protected String situacao;

    public Equipamentos(String codigoPatrimonio, String nome, String marca, String modelo) {

        if (codigoPatrimonio == null || codigoPatrimonio.isBlank() || nome == null || nome.isBlank() || marca == null || marca.isBlank() || modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Informações vazias não são permitidas!");
        }

        this.codigoPatrimonio = codigoPatrimonio.trim();
        this.nome = nome.trim();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        situacao = "Disponível";
    }

    public String getCodigoPatrimonio(){
        return codigoPatrimonio;
    }

    public String getNome(){
        return nome;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public String getSituacao(){
        return situacao;
    }

    public abstract boolean verificarCondicoesEmprestimoManutencao();

    public abstract int consultarPrazoEmprestimo();

    public void iniciarManutencao() {
        situacao = "Em manutenção";
    }

    public void encerrarManutencao() {
        situacao = "Disponível";
    }

}
