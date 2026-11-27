public class Professor extends Usuario {

    private static int limiteEmprestimos = 3;

    private String departamento;

    public Professor(String nome, int matricula) {
        super(nome, matricula);
        this.departamento = "Departamento de Ensino e Extensão";
    }

    public String getDepartamento() {
        return departamento;
    }

    public void atualizarDepartamento(String novoDepartamento) {
        if (novoDepartamento == null || novoDepartamento.isBlank()) {
            throw new IllegalArgumentException("O departamento não pode ser vazio!");
        }
        departamento = novoDepartamento.trim();
    }

    @Override
    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public static void alterarLimiteEmprestimos(int novoLimite) {
        if (novoLimite <= limiteEmprestimos) {
            throw new IllegalArgumentException(
                    "O novo limite deve ser maior que o limite atual (" + limiteEmprestimos + ")!");
        }
        limiteEmprestimos = novoLimite;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Departamento: " + departamento);
    }
}