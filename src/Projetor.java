public class Projetor extends Equipamentos {
    private static int prazoEmprestimo = 2;

    private boolean caboHdmi;

    public Projetor(String codigoPatrimonio, String nome, String marca, String modelo) {
        super(codigoPatrimonio, nome, marca, modelo);
        caboHdmi = true;
    }

    public boolean statusCaboHdmi() {
        return caboHdmi;
    }

    public void setCaboHdmiDisponivel(boolean caboHdmiDisponivel) {
        this.caboHdmi = caboHdmi;
    }

    @Override
    public boolean verificarCondicoesEmprestimoManutencao() {
        return caboHdmi;
    }

    @Override
    public int consultarPrazoEmprestimo() {
        return prazoEmprestimo;
    }

    public static void alterarPrazoEmprestimo(int novoPrazo) {
        if (novoPrazo <= 0) {
            throw new IllegalArgumentException("O prazo de empréstimo deve ser um número inteiro positivo!");
        }
        prazoEmprestimo = novoPrazo;
    }
}
