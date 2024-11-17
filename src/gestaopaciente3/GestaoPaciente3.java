package gestaopaciente3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe para gerenciar os pacientes
class ControlaPaciente {
    private ArrayList<Paciente> pacientes;
    private final String arquivoPacientes = "pacientes.txt"; // Nome do arquivo

    // Inicializando a lista
    public ControlaPaciente() {
        this.pacientes = new ArrayList<>();
        carregarPaciente();
    }

    // Adicionar Paciente
    public void adicionarPaciente(int id, double altura, double peso) {
        Paciente paciente = new Paciente(id, altura, peso);
        pacientes.add(paciente);
        salvarPacientes();
        System.out.println("Paciente adicionado com sucesso!");
    }

    // Listar Pacientes
    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            System.out.println("Lista de Pacientes:");
            for (Paciente paciente : pacientes) {
                paciente.listarPaciente();
            }
        }
    }

    // Alterar Paciente
    public void alterarPaciente(int id) {
        Paciente paciente = buscarPacientePorId(id);
        if (paciente != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Novo valor da altura: ");
            double novaAltura = scanner.nextDouble();
            System.out.print("Novo valor do peso: ");
            double novoPeso = scanner.nextDouble();
            
            paciente.setAltura(novaAltura);
            paciente.setPeso(novoPeso);
            salvarPacientes();
            System.out.println("Paciente alterado com sucesso!");
        } else {
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    // Excluir Paciente
    public void excluirPaciente(int id) {
        Paciente paciente = buscarPacientePorId(id);
        if (paciente != null) {
            pacientes.remove(paciente);
            salvarPacientes();
            System.out.println("Paciente excluído com sucesso!");
        } else {
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    // Buscar paciente por ID
    private Paciente buscarPacientePorId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    // Salvar pacientes no arquivo
    private void salvarPacientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPacientes))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getId() + ";" + paciente.getAltura() + ";" + paciente.getPeso());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    // Carregar pacientes do arquivo
    private void carregarPaciente() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPacientes))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                double altura = Double.parseDouble(dados[1]);
                double peso = Double.parseDouble(dados[2]);
                pacientes.add(new Paciente(id, altura, peso));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pacientes não encontrado. Será criado ao salvar novos pacientes.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar pacientes: " + e.getMessage());
        }
    }
}

// Classe principal para interação com o usuário
class GestaoPaciente3 {
    public static void main(String[] args) {
        ControlaPaciente controlaPaciente = new ControlaPaciente();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Alterar Paciente");
            System.out.println("4. Excluir Paciente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("ID do Paciente: ");
                    int id = scanner.nextInt();
                    System.out.print("Altura do Paciente: ");
                    double altura = scanner.nextDouble();
                    System.out.print("Peso do Paciente: ");
                    double peso = scanner.nextDouble();
                    controlaPaciente.adicionarPaciente(id, altura, peso);
                    break;
                case 2:
                    controlaPaciente.listarPacientes();
                    break;
                case 3:
                    System.out.print("ID do Paciente para alterar: ");
                    int idAlterar = scanner.nextInt();
                    controlaPaciente.alterarPaciente(idAlterar);
                    break;
                case 4:
                    System.out.print("ID do Paciente para excluir: ");
                    int idExcluir = scanner.nextInt();
                    controlaPaciente.excluirPaciente(idExcluir);
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
