import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Resultado {

    // Método para salvar funcionários num arquivo de texto
    public static void salvarFuncionariosEmArquivo(List<Funcionario> funcionarios, String nomeArquivo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(String.format("%-20s %-15s %-15s %-15s%n", "Nome", "Data de Nascimento", "Novo Salário", "Cargo"));
            writer.write("--------------------------------------------------------------------------------\n");

            for (Funcionario funcionario : funcionarios) {
                String nome = funcionario.getNome();
                String dataNascimento = funcionario.getDataNascimento().format(formatter);
                String salario = String.format("%,.2f", funcionario.getSalario());
                String funcao = funcionario.getCargo();

                writer.write(String.format("%-20s %-15s %-15s %-15s%n", nome, dataNascimento, salario, funcao));
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        System.out.println();

        // Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("O funcionário Jõao foi removido!\n");

        // Imprimir todos os funcionários com todas as suas informações
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() +
                    "\nData de Nascimento: " + f.getDataNascimento().format(formatter) +
                    "\nSalário: " + String.format("%,.2f", f.getSalario()) +
                    "\nFunção: " + f.getCargo() + "\n");
        }


        // Atualizar salário com aumento de 10%
        funcionarios.replaceAll(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.1"));
            funcionario.setSalario(novoSalario);
            return funcionario;
        });


        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String cargo = f.getCargo();
            if (!funcionariosPorFuncao.containsKey(cargo)) {
                funcionariosPorFuncao.put(cargo, new ArrayList<>());
            }
            funcionariosPorFuncao.get(cargo).add(f);
        }

        // Exibir funcionários agrupados por função
        System.out.println("Quais os funcionários que ocupam o mesmo cargo?");
        for (String cargo : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + cargo);
            for (Funcionario f : funcionariosPorFuncao.get(cargo)) {
                System.out.println(f.getNome());
            }
            System.out.println();


        }

        // Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("Quais os funcionários que fazem aniversário no mês 10 e 12?");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome() +
                        ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
            }
        }
        System.out.println();


        // Imprimir funcionário com maior idade
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("Funcionário mais velho: " + maisVelho.getNome() + ", Idade: " + idade +"\n");

        }

        // Imprimir lista de funcionários por ordem alfabética
        System.out.println("Ordem alfabética dos funcionários");
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : funcionariosOrdenados) {
            System.out.println(funcionario.getNome());
        }

        // Imprimir total dos salários

        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println();
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios)+"\n");

        // Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("Quantos salários mínimos ganha cada funcionário?\n");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ", Salários Mínimos: " + salariosMinimos);
        });

        // Salvar funcionários num arquivo de texto
        salvarFuncionariosEmArquivo(funcionarios, "funcionarios.txt");
    }
}
