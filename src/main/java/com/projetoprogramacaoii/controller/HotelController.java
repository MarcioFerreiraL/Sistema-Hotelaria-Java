package com.projetoprogramacaoii.controller;

import com.projetoprogramacaoii.model.financeiro.Lancamento;
import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.model.reserva.FormaPagamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import com.projetoprogramacaoii.model.reserva.TipoQuarto;
import com.projetoprogramacaoii.repository.ClienteRepository;
import com.projetoprogramacaoii.repository.LancamentoRepository;
import com.projetoprogramacaoii.repository.QuartoRepository;
import com.projetoprogramacaoii.repository.ReservaRepository;
import com.projetoprogramacaoii.service.ClienteService;
import com.projetoprogramacaoii.service.FuncionarioService;
import com.projetoprogramacaoii.service.QuartoService;
import com.projetoprogramacaoii.service.ReservaService;
import com.projetoprogramacaoii.util.ValidacaoException;
import com.projetoprogramacaoii.view.MainView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelController {

    private final MainView view;
    private final ClienteService clienteService;
    private final QuartoService quartoService;
    private final ReservaService reservaService;

    // Construtor que inicializa a view e os serviços
    public HotelController(MainView view) {
        this.view = view;
        this.clienteService = new ClienteService();
        this.quartoService = new QuartoService();
        this.reservaService = new ReservaService();
    }
    /*
     * Inicia o loop principal da aplicação, exibindo o menu de login
     * e direcionando o fluxo com base na escolha do usuário.
     */
    
    public void iniciar() {
        int opcao;
        do {
            opcao = view.exibirMenuLogin();
            switch (opcao) {
                case 1:
                    executarMenuCliente();
                    break;
                case 2:
                    executarMenuAdmin();
                    break;
                case 0:
                    view.exibirMensagem("Saindo do sistema... Até logo!");
                    break;
                default:
                    view.exibirMensagem("Opção de perfil inválida. Tente novamente.");
            }
        } while (opcao != 0);
        view.fecharScanner();
    }
    /*
     * Gerencia o menu para clientes.
     */
    private void executarMenuCliente() {
        int opcao;
        do {
            opcao = view.exibirMenuCliente();
            switch (opcao) {
                case 1:
                    criarNovoCliente();
                    break;
                case 2:
                    listarQuartosDisponiveis();
                    break;
                case 3:
                    criarNovaReserva();
                    break;
                case 0:
                    view.exibirMensagem("Voltando ao menu principal...");
                    break;
                default:
                    view.exibirMensagem("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    /*
     * Gerencia o menu para administradores.
     */

    private void executarMenuAdmin() {
        int opcao;
        do {
            opcao = view.exibirMenuAdmin();
            switch (opcao) {
                case 1:
                    criarNovoCliente();
                    break;
                case 2:
                    criarNovoQuarto();
                    break;
                case 3:
                    criarNovaReserva();
                    break;
                case 4:
                    executarMenuRelatorios();
                    break;
                case 5:
                    executarMenuAlterarExcluir();
                    break;
                case 0:
                    view.exibirMensagem("Voltando ao menu principal...");
                    break;
                default:
                    view.exibirMensagem("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
  
    //Orquestra as operações de alteração de dados (cliente, quarto, etc.).

    private void executarMenuAlterarExcluir() {
        int opcao;
        do {
            opcao = view.exibirMenuAlterarExcluir();
            switch (opcao) {
                case 1:
                    alterarDados();
                    break;
                case 2:
                    excluirDados();
                    break;
                case 0:
                    view.exibirMensagem("Voltando ao menu do administrador...");
                    break;
                default:
                    view.exibirMensagem("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void alterarDados() {
        int opcao = view.exibirMenuQualEntidade();
        switch (opcao) {
            case 1:
                alterarCliente();
                break;
            case 2:
                alterarQuarto();
                break;
            case 3:
                alterarFuncionario();
                break;
            case 0:
                return;
            default:
                view.exibirMensagem("Opção inválida.");
        }
    }
 
    // Orquestra as operações de exclusão de dados.
    
    private void excluirDados() {
        int opcao = view.exibirMenuQualEntidade();
        switch (opcao) {
            case 1:
                excluirCliente();
                break;
            case 2:
                excluirQuarto();
                break;
            case 3:
                excluirFuncionario();
                break;
            case 0:
                return;
            default:
                view.exibirMensagem("Opção inválida.");
        }
    }

    private void alterarCliente() {
        view.exibirMensagem("\n--- ALTERAR CLIENTE ---");
        try {
            String cpf = view.lerString("Digite o CPF do cliente a ser alterado: ");
            int campo = view.exibirMenuAlterarCliente();
            if (campo == 0) return;

            String novoValor = view.lerString("Digite o novo valor: ");
            clienteService.alterarCliente(cpf, campo, novoValor);
            view.exibirMensagem("Cliente alterado com sucesso!");
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }

    private void alterarQuarto() {
        view.exibirMensagem("\n--- ALTERAR QUARTO ---");
        try {
            int numero = view.lerInt("Digite o número do quarto a ser alterado: ");
            int campo = view.exibirMenuAlterarQuarto();
            if (campo == 0) return;
            
            TipoQuarto novoTipo = view.lerTipoQuarto();
            quartoService.alterarQuarto(numero, novoTipo);
            view.exibirMensagem("Quarto alterado com sucesso!");
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }

    private void alterarFuncionario() {
        view.exibirMensagem("\n--- ALTERAR FUNCIONÁRIO ---");
        try {
            String cpf = view.lerString("Digite o CPF do funcionário a ser alterado: ");
            int campo = view.exibirMenuAlterarFuncionario();
            if (campo == 0) return;

            Object novoValor;
            if (campo == 1 || campo == 2) { // Nome ou Cargo
                novoValor = view.lerString("Digite o novo valor: ");
            } else if (campo == 3) { // Salário
                novoValor = view.lerDouble("Digite o novo salário: ");
            } else if (campo == 4) { // Tipo de Contrato
                novoValor = view.lerTipoContrato();
            } else {
                view.exibirMensagem("Opção de campo inválida.");
                return;
            }
            
            FuncionarioService.alterarFuncionario(cpf, campo, novoValor);
            view.exibirMensagem("Funcionário alterado com sucesso!");
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }

    private void excluirCliente() {
        view.exibirMensagem("\n--- EXCLUIR CLIENTE ---");
        try {
            String cpf = view.lerString("Digite o CPF do cliente a ser excluído: ");
            String confirmacao = view.lerString("Tem certeza que deseja excluir? (S/N): ");
            if (confirmacao.equalsIgnoreCase("S")) {
                clienteService.excluirCliente(cpf);
                view.exibirMensagem("Cliente excluído com sucesso!");
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }

    private void excluirQuarto() {
        view.exibirMensagem("\n--- EXCLUIR QUARTO ---");
        try {
            int numero = view.lerInt("Digite o número do quarto a ser excluído: ");
            String confirmacao = view.lerString("Tem certeza que deseja excluir? (S/N): ");
            if (confirmacao.equalsIgnoreCase("S")) {
                quartoService.excluirQuarto(numero);
                view.exibirMensagem("Quarto excluído com sucesso!");
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }

    private void excluirFuncionario() {
        view.exibirMensagem("\n--- EXCLUIR FUNCIONÁRIO ---");
        try {
            String cpf = view.lerString("Digite o CPF do funcionário a ser excluído: ");
            String confirmacao = view.lerString("Tem certeza que deseja excluir? (S/N): ");
            if (confirmacao.equalsIgnoreCase("S")) {
                FuncionarioService.excluirFuncionario(cpf);
                view.exibirMensagem("Funcionário excluído com sucesso!");
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }
    
    private void executarMenuRelatorios() {
        int opcao;
        do {
            opcao = view.exibirMenuRelatorios();
            switch (opcao) {
                case 1:
                    relatorioClientes();
                    break;
                case 2:
                    relatorioQuartos();
                    break;
                case 3:
                    relatorioReservas();
                    break;
                case 4:
                    relatorioLancamentos();
                    break;
                case 0:
                    view.exibirMensagem("Voltando ao menu principal do administrador...");
                    break;
                default:
                    view.exibirMensagem("Opção de relatório inválida.");
            }
        } while (opcao != 0);
    }
    
    private void relatorioClientes() {
        view.exibirMensagem("\n--- RELATÓRIO DE CLIENTES ---");
        try {
            // Chamada estática diretamente da classe ClienteRepository
            List<Cliente> clientes = ClienteRepository.ler();
            if (clientes.isEmpty()) {
                view.exibirMensagem("Nenhum cliente cadastrado.");
            } else {
                view.exibirListaClientes(clientes);
            }
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar relatório de clientes: " + e.getMessage());
        }
    }

    private void relatorioQuartos() {
        view.exibirMensagem("\n--- RELATÓRIO DE QUARTOS ---");
        try {
            // Chamada estática diretamente da classe QuartoRepository
            List<Quarto> quartos = QuartoRepository.ler();
            if (quartos.isEmpty()) {
                view.exibirMensagem("Nenhum quarto cadastrado.");
            } else {
                view.exibirListaQuartos(quartos);
            }
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar relatório de quartos: " + e.getMessage());
        }
    }
    
    private void relatorioReservas() {
        view.exibirMensagem("\n--- RELATÓRIO DE RESERVAS ---");
        try {
            // Chamada estática diretamente da classe ReservaRepository
            List<Reserva> reservas = ReservaRepository.ler();
            if (reservas.isEmpty()) {
                view.exibirMensagem("Nenhuma reserva encontrada.");
            } else {
                view.exibirListaReservas(reservas);
            }
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar relatório de reservas: " + e.getMessage());
        }
    }

    private void relatorioLancamentos() {
        view.exibirMensagem("\n--- RELATÓRIO FINANCEIRO ---");
        try {
            // Chamada estática diretamente da classe LancamentoRepository
            List<Lancamento> lancamentos = LancamentoRepository.ler();
            if (lancamentos.isEmpty()) {
                view.exibirMensagem("Nenhum lançamento financeiro encontrado.");
            } else {
                view.exibirListaLancamentos(lancamentos);
            }
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar relatório financeiro: " + e.getMessage());
        }
    }
    /**
     * Coleta os dados do usuário e chama o serviço para criar um novo cliente.
     * Trata exceções de validação.
     */
    private void criarNovoCliente() {
        view.exibirMensagem("\n--- CADASTRO DE CLIENTE ---");
        try {
            String nome = view.lerString("Digite o nome do cliente: ");
            String email = view.lerString("Digite o email: ");
            String senha = view.lerString("Digite a senha: ");
            String cpf = view.lerString("Digite o CPF: ");
            int anoNascimento = view.lerInt("Digite o ano de nascimento: ");

            clienteService.criarCliente(nome, email, senha, cpf, anoNascimento);
            view.exibirMensagem("Cliente criado com sucesso!");

        } catch (ValidacaoException e) {
            view.exibirMensagem("Erro de validação: " + e.getMessage());
        } catch (IOException e) {
            view.exibirMensagem("Erro de sistema ao salvar o cliente: " + e.getMessage());
        }
    }
    
    // Coleta os dados e chama o serviço para criar um novo quarto.
 
    private void criarNovoQuarto() {
        view.exibirMensagem("\n--- CADASTRO DE QUARTO ---");
        try {
            int numero = view.lerInt("Digite o número do quarto: ");
            TipoQuarto tipo = view.lerTipoQuarto();
            
            quartoService.criarQuarto(numero, tipo, false);
            view.exibirMensagem("Quarto criado com sucesso!");

        } catch (ValidacaoException e) {
            view.exibirMensagem("Erro de validação: " + e.getMessage());
        } catch (IOException e) {
            view.exibirMensagem("Erro de sistema ao salvar o quarto: " + e.getMessage());
        }
    }
    // Busca e exibe os quartos que não estão ocupados.
    private void listarQuartosDisponiveis() {
        try {
            List<Quarto> todosOsQuartos = QuartoRepository.ler();
            List<Quarto> quartosDisponiveis = todosOsQuartos.stream()
                .filter(quarto -> !quarto.isOcupado())
                .collect(Collectors.toList());
            
            view.exibirListaQuartos(quartosDisponiveis);

        } catch (IOException e) {
            view.exibirMensagem("Erro ao ler os dados dos quartos: " + e.getMessage());
        }
    }
    /*
     * Orquestra o complexo processo de criar uma reserva:
     * 1. Valida a existência do cliente.
     * 2. Mostra quartos disponíveis.
     * 3. Valida a escolha do quarto.
     * 4. Chama o serviço de reserva.
     * 5. Trata múltiplos tipos de exceção.
     */
    private void criarNovaReserva() {
        view.exibirMensagem("\n--- CRIAR NOVA RESERVA ---");
        try {
            String cpfCliente = view.lerString("Digite o CPF do cliente para a reserva: ");
            
            Cliente clienteDaReserva = ClienteRepository.ler().stream()
                .filter(c -> c.getCpf().equals(cpfCliente))
                .findFirst()
                .orElseThrow(() -> new ValidacaoException("Cliente não encontrado. Por favor, cadastre o cliente primeiro."));

            listarQuartosDisponiveis();
            int numQuarto = view.lerInt("\nDigite o número do quarto desejado: ");
            
            Quarto quartoEscolhido = QuartoRepository.ler().stream()
                 .filter(q -> q.getNumero() == numQuarto && !q.isOcupado())
                 .findFirst()
                 .orElseThrow(() -> new ValidacaoException("Quarto não encontrado, inválido ou já está ocupado!"));

            FormaPagamento formaPagamento = view.lerFormaPagamento(); 
            
            reservaService.criarReserva(clienteDaReserva, quartoEscolhido, formaPagamento); //
            view.exibirMensagem("Reserva criada com sucesso para o cliente " + clienteDaReserva.getNome() + "!");
            
        } catch (ValidacaoException e) {
            view.exibirMensagem("Erro ao criar reserva: " + e.getMessage());
        } catch (IOException e) {
            view.exibirMensagem("Ocorreu um erro de sistema: " + e.getMessage());
        } catch (Exception e) {
            view.exibirMensagem("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}