import java.time.LocalDate;

public  abstract class Usuario {
   private final String nome;
   private final int matricula;
    private boolean ativo;
    private LocalDate ultimaAtlualizacao;
    private String dataUltimaAtualizacao;
    
    public Usuario(String nome, int matricula,String dataUltimaAtualizacao) {
        this.nome = nome;
        this.matricula = matricula;
        this.ativo = true;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }   
   public abstract int getLimiteEmprestimos();

   public void alterarStatus(String dataUltimaAtualizacao){
    if (this.ativo) {
            this.ativo = false;
        } else {
            this.ativo = true;
        }
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
   }
   public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean estaAtivo() {
        if(ativo){
            return true;
        }else{
            return false;
        }
        
    }
       public String getSituacao() {
        if (ativo) {
            return "Ativa";
        } else {
            return "Inativa";
        }
    }

    public String getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }
    public void exibirDados() {
    System.out.println("Nome: " + nome);
    System.out.println("Matricula: " + matricula);
    System.out.println("Situacao: " + getSituacao());
    System.out.println("Ultima atualizacao: " + dataUltimaAtualizacao);
    System.out.println("Limite de emprestimos: " + getLimiteEmprestimos());
}
}
