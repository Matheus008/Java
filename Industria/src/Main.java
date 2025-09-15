import model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Funcionario> listaDeFuncionarios = new ArrayList<>();

        listaDeFuncionarios.add(new Funcionario(LocalDate.of(2000, 10, 18), "Maria", new BigDecimal("2009.44"), "Operador"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1990, 5, 12), "João", new BigDecimal("2284.38"), "Operador"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1961, 5, 2), "Caio", new BigDecimal("9836.14"), "Coordenador"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1988, 10, 14), "Miguel", new BigDecimal("19119.88"), "Diretor"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1995, 1, 5), "Alice", new BigDecimal("2234.68"), "Recepcionista"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1999, 11, 19), "Heitor", new BigDecimal("1582.72"), "Operador"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1993, 3, 31), "Artur", new BigDecimal("4071.84"), "Contador"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1994, 7, 8), "Laura", new BigDecimal("301745"), "Gerente"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(2003, 5, 24), "Heloísa", new BigDecimal("1606.85"), "Eletricista"));
        listaDeFuncionarios.add(new Funcionario(LocalDate.of(1996, 9, 2), "Helena", new BigDecimal("2799.93"), "Gerente"));

        listaDeFuncionarios = listaDeFuncionarios.stream().filter(nome -> !Objects.equals(nome.getNome(), "João")).toList();

        for (Funcionario funcionario : listaDeFuncionarios) {
            LocalDate dataAntiga = funcionario.getDataNascimento();
            DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = formatadorData.format(dataAntiga);

            System.out.println("Data formatada: " + dataFormatada);

            DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
            simbolos.setDecimalSeparator(',');
            simbolos.setGroupingSeparator('.');

            DecimalFormat formatadorValor = new DecimalFormat("#,##0.00", simbolos);
            String valorFormatado = formatadorValor.format(funcionario.getSalario());

            System.out.println("Valor Formatado:" + valorFormatado);

            funcionario.setSalario(new BigDecimal(String.valueOf(funcionario.getSalario().multiply(BigDecimal.valueOf(1.10)))));

            System.out.println("Valor com mais 10%: " + funcionario.getSalario());
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });

        listaDeFuncionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> {
                    System.out.println("Aniversariantes do mes 10 e 12: " + f.getNome());
                });

        Optional<Funcionario> maisVelho = listaDeFuncionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));


        maisVelho.ifPresent(f -> System.out.println("Funcionário mais velho: \n Nome: " + f.getNome() + ", Idade: " + f.getDataNascimento()));

        System.out.println("Ordem alfabetica: ");
        listaDeFuncionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> {
                    System.out.println("Nome: " + f.getNome());
                });

        BigDecimal total = listaDeFuncionarios.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);;

        System.out.println("Salario total: R$" + total);


        // Não Consegui fazer: Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
    }
}