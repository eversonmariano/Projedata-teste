import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String cargo;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String cargo) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }
}
