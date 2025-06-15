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

    public HotelController(MainView view) {
        this.view = view;
        this.clienteService = new ClienteService();
        this.quartoService = new QuartoService();
        this.reservaService = new ReservaService();
    }

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
                case 0:
                    view.exibirMensagem("Voltando ao menu principal...");
                    break;
                default:
                    view.exibirMensagem("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
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
    
    // NOVO MÉTODO: Gera o relatório de clientes
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

    // NOVO MÉTODO: Gera o relatório de quartos
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

    // NOVO MÉTODO: Gera o relatório de reservas
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

            // A linha que solicitava a data foi removida.
            // Agora o sistema pergunta a forma de pagamento:
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