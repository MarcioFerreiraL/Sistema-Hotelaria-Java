package com.projetoprogramacaoii.view;

import com.projetoprogramacaoii.model.financeiro.Lancamento;
import com.projetoprogramacaoii.model.financeiro.TipoLancamento;
import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.model.reserva.FormaPagamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import com.projetoprogramacaoii.model.reserva.TipoQuarto;
import com.projetoprogramacaoii.model.rh.TipoContrato;
import java.util.List;
import java.util.Scanner;

public class MainView {

    private final Scanner scanner;

    public MainView() {
        this.scanner = new Scanner(System.in);
    }

     // Exibe o menu principal de login e retorna a opção escolhida pelo usuário.
    public int exibirMenuLogin() {
        System.out.println("\n--- BEM-VINDO AO JAVA'S HOTEL ---");
        System.out.println("Selecione seu perfil:");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador");
        System.out.println("0. Sair do Sistema");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }
    // Exibe o menu de opções para o cliente.
    public int exibirMenuCliente() {
        System.out.println("\n--- MENU DO CLIENTE ---");
        System.out.println("1. Fazer meu cadastro");
        System.out.println("2. Ver quartos disponíveis");
        System.out.println("3. Criar uma reserva");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }
    // Exibe o menu de opções para o administrador.
    public int exibirMenuAdmin() {
        System.out.println("\n--- MENU DO ADMINISTRADOR ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Quarto");
        System.out.println("3. Criar uma Reserva");
        System.out.println("4. Ver Relatórios");
        System.out.println("5. Alterar/Excluir Dados");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }
    // Exibe o menu de relatorios.
    public int exibirMenuRelatorios() {
        System.out.println("\n--- MENU DE RELATÓRIOS ---");
        System.out.println("1. Listar todos os Clientes");
        System.out.println("2. Listar todos os Quartos");
        System.out.println("3. Listar todas as Reservas");
        System.out.println("4. Listar Lançamentos Financeiros");
        System.out.println("0. Voltar");
        return lerInt("Escolha uma opção de relatório: ");
    }
 // Apresenta uma lista de clientes de forma formatada.
    public void exibirListaClientes(List<Cliente> clientes) {
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-30s | %-24s | %s%n", "Nome", "Email", "CPF");
        System.out.println("----------------------------------------------------------------------");
        for (Cliente cliente : clientes) {
            System.out.printf("%-30s | %-35s | %s%n", cliente.getNome(), cliente.getEmail(), cliente.getCpf());
        }
        System.out.println("----------------------------------------------------------------------");
    }
 // Apresenta uma lista de reservas de forma formatada.
    public void exibirListaReservas(List<Reserva> reservas) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-30s | %-8s | %-10s%n", "ID Reserva", "Cliente", "Quarto", "Pagamento");
        System.out.println("--------------------------------------------------------------------------");
        for (Reserva reserva : reservas) {
            System.out.printf("%-15s | %-30s | %-8d | %-10s%n",
                    reserva.getId(),
                    reserva.getCliente().getNome(),
                    reserva.getQuarto().getNumero(),
                    reserva.getFormaPagamento());
        }
        System.out.println("--------------------------------------------------------------------------");
    }
 // Apresenta uma lista de lancamentos de forma formatada.
    public void exibirListaLancamentos(List<Lancamento> lancamentos) {
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-10s | %-70s%n", "Valor (R$)", "Tipo", "Descrição");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        double total = 0;
        for (Lancamento lancamento : lancamentos) {
            System.out.printf("%-15.2f | %-10s | %-70s%n",
                    lancamento.getValor(),
                    lancamento.getTipoLancamento(),
                    lancamento.getDescricao());
            if(lancamento.getTipoLancamento() == TipoLancamento.RECEITA) {
                total += lancamento.getValor();
            } else {
                total -= lancamento.getValor();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("Saldo Total: R$ %.2f%n", total);
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }
    // Apresenta uma lista de clientes de forma formatada.
    public void exibirListaQuartos(List<Quarto> quartos) {
        System.out.println("\n--- LISTA DE QUARTOS ---");
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s | %-15s | %s%n", "Número", "Tipo", "Status");
        System.out.println("-----------------------------------------");
        
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto encontrado.");
            return;
        } else {
	        for (Quarto quarto : quartos) {
	            String status = quarto.isOcupado() ? "Ocupado" : "Disponível";
	            System.out.printf("%-10d | %-15s | %s%n", quarto.getNumero(), quarto.getTipo(), status);
	        }
	        System.out.println("-----------------------------------------");
        }
    }
    
    public int exibirMenuAlterarExcluir() {
        System.out.println("\n--- ALTERAR/EXCLUIR DADOS ---");
        System.out.println("1. Alterar Dados");
        System.out.println("2. Excluir Dados");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuQualEntidade() {
        System.out.println("\nSelecione o tipo de dado:");
        System.out.println("1. Cliente");
        System.out.println("2. Quarto");
        System.out.println("3. Funcionário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuAlterarCliente() {
        System.out.println("\nQual campo do cliente deseja alterar?");
        System.out.println("1. Nome");
        System.out.println("2. Email");
        System.out.println("3. Senha");
        System.out.println("4. Ano de Nascimento");
        System.out.println("0. Cancelar");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuAlterarFuncionario() {
        System.out.println("\nQual campo do funcionário deseja alterar?");
        System.out.println("1. Nome");
        System.out.println("2. Cargo");
        System.out.println("3. Salário");
        System.out.println("4. Tipo de Contrato");
        System.out.println("0. Cancelar");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int exibirMenuAlterarQuarto() {
        System.out.println("\nQual campo do quarto deseja alterar?");
        System.out.println("1. Tipo do Quarto");
        System.out.println("0. Cancelar");
        System.out.print("Escolha uma opção: ");
        return lerOpcao();
    }

    public int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Por favor, insira um número.");
            return -1; // Retorna um valor inválido para o loop continuar
        }
    }
    /*
     * Lê uma string do console.
     */
    public String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    /*
     * Lê um valor inteiro do console, tratando erros de formato.
     * Continua pedindo a entrada até que um número válido seja inserido..
     */
    public int lerInt(String prompt) {
        while (true) { // Loop até que uma entrada válida seja fornecida
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro válido.");
            }
        }
    }

    public double lerDouble(String prompt) {
        while (true) { // Loop até que uma entrada válida seja fornecida
            System.out.print(prompt);
            try {
                // Substitui vírgula por ponto
                String input = scanner.nextLine().replace(',', '.');
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um valor numérico válido (ex: 150.50).");
            }
        }
    }

    /*
     * Lê um enum do tipo TipoQuarto do console.
     */
    public TipoQuarto lerTipoQuarto() {
        while(true) {
            System.out.println("Tipos de Quarto:");
            for (TipoQuarto tq : TipoQuarto.values()) { // .values() retorna um array
                System.out.println(tq.ordinal() + ". " + tq.name());
            }
            int escolha = lerInt("Escolha o tipo: ");
            if (escolha >= 0 && escolha < TipoQuarto.values().length) {
                return TipoQuarto.values()[escolha];
            }
            System.out.println("Opção inválida.");
        }
    }
    
    public FormaPagamento lerFormaPagamento() {
        while(true) {
            System.out.println("Formas de Pagamento:");
            for (FormaPagamento fp : FormaPagamento.values()) {
                System.out.println(fp.ordinal() + ". " + fp.name());
            }
            int escolha = lerInt("Escolha a forma de pagamento: ");
            if (escolha >= 0 && escolha < FormaPagamento.values().length) {
                return FormaPagamento.values()[escolha];
            }
            System.out.println("Opção inválida.");
        }
    }
    
    public TipoContrato lerTipoContrato() {
        while(true) {
            System.out.println("Tipos de Contrato:");
            for (TipoContrato tc : TipoContrato.values()) {
                System.out.println(tc.ordinal() + ". " + tc.name());
            }
            int escolha = lerInt("Escolha o tipo de contrato: ");
            if (escolha >= 0 && escolha < TipoContrato.values().length) {
                return TipoContrato.values()[escolha];
            }
            System.out.println("Opção inválida.");
        }
    }
    // Exibe uma mensagem genérica no console
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
    /*
     * Fecha o recurso Scanner para evitar vazamento de memória.
     * Deve ser chamado ao final da execução do programa.
     */
    public void fecharScanner() {
        scanner.close();
    }
}