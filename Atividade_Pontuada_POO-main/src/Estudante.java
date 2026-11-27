public class Estudante extends Usuario{
    private static int limiteEmprestimos = 2;

    public Estudante(String nome, int matricula) {
        super(nome, matricula);
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
}
