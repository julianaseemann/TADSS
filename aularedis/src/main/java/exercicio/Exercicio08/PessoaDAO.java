package exercicio.Exercicio08;

import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class PessoaDAO {
        public static void main(String[] args) {

        Jedis redis = new Jedis("localhost", 6379);

        Scanner scanner = new Scanner(System.in);

        String opcao;

        do {

            System.out.println("\n=========CONTATOS=========");
            System.out.println("| Escolha uma opção:     |");
            System.out.println("| [1] - Incluir          |");
            System.out.println("| [2] - Listar           |");
            System.out.println("| [3] - Excluir          |");
            System.out.println("| [4] - Editar           |");
            System.out.println("| [0] - Sair             |");
            System.out.println("==========================");

            opcao = scanner.nextLine();

            switch (opcao) {

                case "1":


                    Pessoa pessoa = new Pessoa();
                    System.out.print("Nome: ");
                    pessoa.nome = scanner.nextLine();

                    System.out.print("Sobrenome: ");
                    pessoa.sobrenome = scanner.nextLine();

                    System.out.print("Telefone: ");
                    pessoa.telefone = scanner.nextLine();

                    System.out.print("Idade: ");
                    pessoa.idade = scanner.nextLine();

                    redis.hset("contatos:" + pessoa.nome, "nome", pessoa.nome);
                    redis.hset("contatos:" + pessoa.nome, "sobrenome", pessoa.sobrenome);
                    redis.hset("contatos:" + pessoa.nome, "telefone", pessoa.telefone);
                    redis.hset("contatos:" + pessoa.nome, "idade", pessoa.idade);

                    System.out.println("Contato cadastrado com sucesso.");

                    break;

                case "2":

                    Set<String> hkeys = redis.keys("contatos:*");

                    if (hkeys.isEmpty()) {
                        System.out.println("Nenhum contato encontrado.");
                    } else {

                        for (String key : hkeys) {
                            System.out.println("\nContato: " + key);
                            redis.hgetAll(key)
                                    .forEach((k, v) ->
                                            System.out.println(k + ": " + v)
                                    );
                        }
                    }

                    break;

                case "3":

                    System.out.print("Digite o nome do contato a ser excluído: ");
                    String nomeExcluir = "contatos:" + scanner.nextLine();

                    if (!redis.exists(nomeExcluir)) {
                        System.out.println("Contato não encontrado.");
                        break;
                    }
                    Long removido = redis.del("contatos:" + nomeExcluir);

                    if (removido > 0) {
                        System.out.println("Contato removido com sucesso.");
                    } else {
                        System.out.println("Contato não encontrado.");
                    }

                    break;

                case "4":

                    System.out.print("Digite o nome do contato a ser editado: ");

                    String nomeEditar = "contatos:" + scanner.nextLine();
                    if (!redis.exists(nomeEditar)) {
                        System.out.println("Contato não encontrado.");
                        break;
                    }

                    System.out.println("===O que você deseja editar?===");
                    System.out.println("| [1] - Nome                  |");
                    System.out.println("| [2] - Sobrenome             |");
                    System.out.println("| [3] - Telefone              |");
                    System.out.println("| [4] - Idade                 |");
                    System.out.println("===============================");

                    String campoEditar = scanner.nextLine();

                    switch (campoEditar) {

                        case "1":
                            System.out.print("Digite o novo nome: ");
                            String novoNome = scanner.nextLine();
                            redis.hset("contatos:" + nomeEditar, "nome", novoNome);
                            System.out.println("Contato atualizado.");
                            break;

                        case "2":
                            System.out.print("Digite o novo sobrenome: ");
                            String novoSobrenome = scanner.nextLine();
                            redis.hset("contatos:" + nomeEditar, "sobrenome", novoSobrenome);
                            System.out.println("Contato atualizado.");
                            break;

                        case "3":
                            System.out.print("Digite o novo telefone: ");
                            String novoTelefone = scanner.nextLine();
                            redis.hset("contatos:" + nomeEditar, "telefone", novoTelefone);
                            System.out.println("Contato atualizado.");
                            break;

                        case "4":
                            System.out.print("Digite a nova idade: ");
                            String novaIdade = scanner.nextLine();
                            redis.hset("contatos:" + nomeEditar, "idade", novaIdade);
                            System.out.println("Contato atualizado.");
                            break;

                        default:
                            System.out.println("Campo inválido.");
                            break;
                    }

                    break;

                case "0":
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
                    
            }

        } while (opcao != "0");

        scanner.close();
        redis.close();
    }
}
