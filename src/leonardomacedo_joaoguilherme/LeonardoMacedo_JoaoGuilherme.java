package leonardomacedo_joaoguilherme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LeonardoMacedo_JoaoGuilherme {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int escolha = 0;
        boolean validacao1 = false;

        /* Criação do arquivo "entrada.txt" */
        try {
            File arquivo = new File("entrada.txt");
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado: " + arquivo.getName());
            } else {
                System.out.println("O arquivo já existe.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
            e.printStackTrace();
        }

        while (!validacao1) {
            System.out.print("Você deseja inserir os valores em um arquivo (1) ou utilizar um arquivo já existente (2)? ");
            escolha = input.nextInt();
            if (escolha != 1 && escolha != 2) {
                System.out.println("Insira um valor válido...");
            } else {
                validacao1 = true;
            }
        }

        List<Produto> produtos = new ArrayList<>();

        if (escolha == 1) {
            /* Escrever no arquivo "entrada.txt" */
            try {
                FileWriter dados = new FileWriter("entrada.txt");
                int fim = 0, i = 1;
                int codigo = 0;
                double valor = 0;
                boolean validacao = false;
                String descricao = "", unidade = "", dadosConcatenados;

                /* Coleta de dados */
                do {
                    System.out.println("\nCOMO INSERIR ARQUIVOS:");
                    System.out.println("Inserir código, pressionar enter");
                    System.out.println("Inserir descrição, pressionar enter");
                    System.out.println("Inserir unidade, pressionar enter");
                    System.out.println("Inserir valor, pressionar enter\n");

                    System.out.println("\t\t" + i + " Conjunto de dados");

                    /* Validação se os dados do código estão de acordo(valor inteiro > 0) */
                    while (!validacao) {
                        System.out.print("\nInsira o valor do código (valor maior que zero): ");
                        try {
                            codigo = input.nextInt();
                            if (codigo > 0) {
                                validacao = true;
                            } else {
                                System.out.println("Valor inválido, o código do produto deve ser maior que zero!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido, o código do produto deve ser maior que zero!\n");
                            input.next(); // Limpa o buffer do scanner
                        }
                    }

                    /* Limpeza do buffer de saída */
                    input.nextLine();
                    /* Reversão da variável de validação */
                    validacao = false;

                    /* Validação se os dados da descrição estão de acordo(string >= 1) */
                    while (!validacao) {
                        System.out.print("\nInsira o valor da descrição (mínimo 1 caractere): ");
                        descricao = input.nextLine().trim(); // impede a inserção de valores vazios
                        if (descricao.length() < 1) {
                            System.out.println("A descrição deve ter pelo menos 1 caractere. Por favor, tente novamente.");
                        } else {
                            validacao = true;
                        }
                    }

                    /* Reversão da variável de validação */
                    validacao = false;

                    /* Validação se os dados da unidade estão de acordo(string >= 1) */
                    while (!validacao) {
                        System.out.print("\nInsira o valor da unidade (mínimo 1 caractere): ");
                        unidade = input.nextLine().trim(); // impede a inserção de valores vazios
                        if (unidade.length() < 1) {
                            System.out.println("A unidade deve ter pelo menos 1 caractere. Por favor, tente novamente.");
                        } else {
                            validacao = true;
                        }
                    }

                    /* Reversão da variável de validação */
                    validacao = false;

                    /* Validação se os dados do valor estão de acordo(double >= 0) */
                    while (!validacao) {
                        System.out.print("\nInsira o dado do valor (número com casas decimais maior ou igual a zero): ");
                        try {
                            valor = input.nextDouble();
                            if (valor >= 0) {
                                validacao = true;
                            } else {
                                System.out.println("Valor inválido, o valor do produto deve ser maior ou igual a zero!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido, o valor do produto deve ser maior ou igual a zero!\n");
                            input.next(); // Limpa o buffer do scanner
                        }
                    }

                    /* Reversão da variável de validação */
                    validacao = false;

                    /* Validação se o valor da variável 'fim' está de acordo(1 ou 2) */
                    while (!validacao) {
                        System.out.print("Você deseja inserir mais um grupo de dados no arquivo?(1-sim/2-não) ");
                        try {
                            fim = input.nextInt();
                            if (fim == 1 || fim == 2) {
                                validacao = true;
                            } else {
                                System.out.println("Valor inválido, insira um valor válido!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido, insira um valor válido!");
                            input.next(); // Limpa o buffer do scanner
                        }
                    }

                    // Criando o objeto Produto
                    Produto produto = new Produto(codigo, descricao, unidade, valor);
                    produtos.add(produto);

                    i++;
                    /* Reversão da variável de validação */
                    validacao = false;

                    dadosConcatenados = concatena(codigo, descricao, unidade, valor);
                    dados.write(dadosConcatenados + "\n");
                    System.out.println("Arquivos salvos!\n");
                } while (fim == 1);
                dados.close();
            } catch (IOException e) {
                System.out.println("Erro na entrada de dados!");
                e.printStackTrace();
            }
        } else {
            // Caso escolha seja 2, leitura do arquivo já existente
            produtos = leituraArquivo();
        }

        char campoOrdenacao, sentidoOrdenacao;

        /* Coleta a escolha de qual coluna será a ordenação */
        while (true) {
            System.out.print("Por qual campo você deseja ordenar os dados (Código-C/Descrição-D/Unidade-U/Valor-V)? ");
            campoOrdenacao = input.next().charAt(0);

            if (campoOrdenacao == 'C' || campoOrdenacao == 'D' || campoOrdenacao == 'U' || campoOrdenacao == 'V' || campoOrdenacao == 'c' || campoOrdenacao == 'd' || campoOrdenacao == 'u' || campoOrdenacao == 'v')
                break;
            else
                System.out.println("Valor inválido, o valor inserido deve ser ser algum dos solicitados\n");
        }

        /* Coleta a escolha de qual sentido será a ordenação */
        while (true) {
            System.out.print("Em qual sentido você deseja ordenar os dados (Ascendente-A/Descendente-D)? ");
            sentidoOrdenacao = input.next().charAt(0);

            if (sentidoOrdenacao == 'A' || sentidoOrdenacao == 'D' || sentidoOrdenacao == 'a' || sentidoOrdenacao == 'd')
                break;
            else
                System.out.println("Valor inválido, o valor inserido deve ser ser algum dos solicitados\n");
        }

        // Ordenação dos produtos conforme escolha do usuário
        switch (campoOrdenacao) {
            case 'C':
            case 'c':
                heapSort(produtos, new ComparadorCodigo(), sentidoOrdenacao);
                break;
            case 'D':
            case 'd':
                heapSort(produtos, new ComparadorDescricao(), sentidoOrdenacao);
                break;
            case 'U':
            case 'u':
                heapSort(produtos, new ComparadorUnidade(), sentidoOrdenacao);
                break;
            case 'V':
            case 'v':
                heapSort(produtos, new ComparadorValor(), sentidoOrdenacao);
                break;
        }

        // Escrever no arquivo de saída
        escreverArquivoSaida(produtos);
        input.close();
    }

    private static void escreverArquivoSaida(List<Produto> produtos) {
        try {
            FileWriter arquivoSaida = new FileWriter("LeonardoMacedo_JoãoGuilherme.txt");
            for (Produto produto : produtos) {
                arquivoSaida.write(produto.toString() + "\n");
            }
            arquivoSaida.close();
            System.out.println("\nDados ordenados escritos no arquivo LeonardoMacedo_JoãoGuilherme.txt com sucesso!\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de saída.");
            e.printStackTrace();
        }
    }

    private static List<Produto> leituraArquivo() {
        List<Produto> produtos = new ArrayList<>();
        try {
            File arquivo = new File("entrada.txt");
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split("    ");

                if (dados.length == 4) {
                    int codigo = Integer.parseInt(dados[0]);
                    String descricao = dados[1];
                    String unidade = dados[2];
                    double valor = Double.parseDouble(dados[3]);

                    Produto produto = new Produto(codigo, descricao, unidade, valor);
                    produtos.add(produto);
                } else {
                    System.out.println("Formato de linha inválido: " + linha);
                }
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        }

        return produtos;
    }

    private static String concatena(int codigo, String descricao, String unidade, double valor) {
        return codigo + "    " + descricao + "    " + unidade + "    " + valor;
    }

    private static void heapSort(List<Produto> arr, ComparadorProduto comparador, char ordem) {
        int n = arr.size();

        // Construir heap (reorganizar array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, comparador, ordem);

        // Extrair elementos um por um do heap
        for (int i = n - 1; i > 0; i--) {
            // Mover a raiz atual para o fim do array
            Produto temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            // chamar max heapify na heap reduzida
            heapify(arr, i, 0, comparador, ordem);
        }
    }

    private static void heapify(List<Produto> arr, int n, int i, ComparadorProduto comparador, char ordem) {
        int maior = i; // Inicializa o maior como raiz
        int esquerda = 2 * i + 1; // Esquerda = 2*i + 1
        int direita = 2 * i + 2; // Direita = 2*i + 2

        // Se o filho esquerdo é maior que a raiz
        if (esquerda < n && comparador.comparar(arr.get(esquerda), arr.get(maior), ordem) > 0)
            maior = esquerda;

        // Se o filho direito é maior que o maior até agora
        if (direita < n && comparador.comparar(arr.get(direita), arr.get(maior), ordem) > 0)
            maior = direita;

        // Se o maior não é a raiz
        if (maior != i) {
            Produto swap = arr.get(i);
            arr.set(i, arr.get(maior));
            arr.set(maior, swap);

            // Recursivamente heapify a subárvore afetada
            heapify(arr, n, maior, comparador, ordem);
        }
    }

    interface ComparadorProduto {
        int comparar(Produto a, Produto b, char ordem);
    }

    static class ComparadorCodigo implements ComparadorProduto {
        public int comparar(Produto a, Produto b, char ordem) {
            if (ordem == 'A' || ordem == 'a') {
                return Integer.compare(a.getCodigo(), b.getCodigo());
            } else {
                return Integer.compare(b.getCodigo(), a.getCodigo());
            }
        }
    }

    static class ComparadorDescricao implements ComparadorProduto {
        public int comparar(Produto a, Produto b, char ordem) {
            if (ordem == 'A' || ordem == 'a') {
                return a.getDescricao().compareTo(b.getDescricao());
            } else {
                return b.getDescricao().compareTo(a.getDescricao());
            }
        }
    }

    static class ComparadorUnidade implements ComparadorProduto {
        public int comparar(Produto a, Produto b, char ordem) {
            if (ordem == 'A' || ordem == 'a') {
                return a.getUnidade().compareTo(b.getUnidade());
            } else {
                return b.getUnidade().compareTo(a.getUnidade());
            }
        }
    }

    static class ComparadorValor implements ComparadorProduto {
        public int comparar(Produto a, Produto b, char ordem) {
            if (ordem == 'A' || ordem == 'a') {
                return Double.compare(a.getValor(), b.getValor());
            } else {
                return Double.compare(b.getValor(), a.getValor());
            }
        }
    }

    static class Produto {
        private int codigo;
        private String descricao;
        private String unidade;
        private double valor;

        public Produto(int codigo, String descricao, String unidade, double valor) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.unidade = unidade;
            this.valor = valor;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getUnidade() {
            return unidade;
        }

        public double getValor() {
            return valor;
        }

        public String toString() {
            return codigo + "    " + descricao + "    " + unidade + "    " + valor;
        }
    }
}
