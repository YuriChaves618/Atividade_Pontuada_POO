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

     public void registrarDevolucao(boolean necessitaReparo) {

        if (!situacao.equals("Emprestado")) {
            throw new IllegalStateException(
                    "O equipamento não possui empréstimo em aberto!");
        }

        if (necessitaReparo) {
            situacao = "Em manutenção";
        } else {
            situacao = "Disponível";
        }
    }

      public void retirarPorEmprestimo() {

        if (!situacao.equals("Disponível")) {
            throw new IllegalStateException(
                    "O equipamento não está disponível!");
        }

        if (!verificarCondicoesEmprestimoManutencao()) {
            throw new IllegalStateException(
                    "O equipamento não atende às condições para empréstimo!");
        }

        situacao = "Emprestado";
    }


    public void iniciarManutencao() {
        if (situacao.equals("Emprestado")) {
            throw new IllegalStateException(
                    "O equipamento está emprestado!");
        }

        if (situacao.equals("Em manutenção")) {
            throw new IllegalStateException(
                    "O equipamento já está em manutenção!");
        }

        situacao = "Em manutenção";
    }

    public void encerrarManutencao() {
          if (!situacao.equals("Em manutenção")) {
            throw new IllegalStateException(
                    "O equipamento não está em manutenção!");
        }

        situacao = "Disponível";
    }
    }

}
