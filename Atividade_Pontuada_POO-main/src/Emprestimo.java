import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private final Usuario usuario;
    private final Equipamentos equipamento;
    private final LocalDate dataRetirada;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataEfetivaDevolucao;

    public Emprestimo(Usuario usuario, Equipamentos equipamento, LocalDate dataRetirada) {
        if (usuario == null || equipamento == null || dataRetirada == null) {
            throw new IllegalArgumentException("Usuário, equipamento e data da retirada são obrigatórios!");
        }

        if (!usuario.estaAtivo()) {
            throw new IllegalArgumentException("Somente usuários com cadastro ativo podem fazer empréstimos!");
        }

        this.usuario = usuario;
        this.equipamento = equipamento;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataRetirada.plusDays(equipamento.consultarPrazoEmprestimo());
        this.dataEfetivaDevolucao = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Equipamentos getEquipamento() {
        return equipamento;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }

    public boolean estaEmAberto() {
        return dataEfetivaDevolucao == null;
    }

    public void registrarDevolucao(LocalDate dataEfetiva) {
        if (dataEfetiva == null) {
            throw new IllegalArgumentException("A data efetiva de devolução é obrigatória!");
        }

        if (!estaEmAberto()) {
            throw new IllegalStateException("Este empréstimo já foi devolvido!");
        }

        if (dataEfetiva.isBefore(dataRetirada)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data da retirada!");
        }

        dataEfetivaDevolucao = dataEfetiva;
    }

    public int calcularDiasAtraso() {
        if (estaEmAberto() || !dataEfetivaDevolucao.isAfter(dataPrevistaDevolucao)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataEfetivaDevolucao);
    }
}
