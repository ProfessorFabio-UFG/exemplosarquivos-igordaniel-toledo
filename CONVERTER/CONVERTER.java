import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CONVERTER {

    public static void main(String[] args) {

        File diretorio = new File("meus_arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdir();
            System.out.println("Diretório '" + diretorio.getName() + "' criado com sucesso.");
        }

        String nomeArquivoOriginal = diretorio.getName() + "/dados_ufg.txt";
        String nomeArquivoCopia = diretorio.getName() + "/copia_dados_ufg.txt";
        File arquivoOriginal = new File(nomeArquivoOriginal);


        System.out.println("📝 Escrevendo no arquivo de exemplo: " + nomeArquivoOriginal);
        try (PrintWriter escritor = new PrintWriter(new FileWriter(arquivoOriginal))) {
            escritor.println("Universidade Federal de Goiás - UFG");
            escritor.println("Instituto de Informática");
            escritor.println("Programação Orientada a Objetos");
            escritor.flush();
        } catch (IOException e) {
            System.err.println(" Erro ao criar o arquivo: " + e.getMessage());
            return; 
        }


        CONVERTER solucao = new CONVERTER();

        System.out.println("\n--- Parte 1: Lendo byte a byte e copiando o arquivo ---");
        solucao.copiarArquivoByteAByte(nomeArquivoOriginal, nomeArquivoCopia);

        System.out.println("\n\n--- Parte 2: Lendo o arquivo original linha a linha ---");
        solucao.lerArquivoLinhaALinha(nomeArquivoOriginal);



        System.out.println("\n\n--- Finalização: Listando e limpando os arquivos gerados ---");
        File arquivoCopia = new File(nomeArquivoCopia);

        System.out.println("Arquivos no diretório '" + diretorio.getName() + "':");
        for (String nome : diretorio.list()) {
            System.out.println(" -> " + nome);
        }


        if (arquivoOriginal.exists()) {
            arquivoOriginal.delete();
            System.out.println("Arquivo '" + arquivoOriginal.getName() + "' excluído.");
        }
        if (arquivoCopia.exists()) {
            arquivoCopia.delete();
            System.out.println("Arquivo '" + arquivoCopia.getName() + "' excluído.");
        }
        diretorio.delete(); 
        System.out.println("Diretório '" + diretorio.getName() + "' excluído.");
    }


    public void copiarArquivoByteAByte(String origem, String destino) {
       
        try (FileInputStream leitor = new FileInputStream(origem);
             FileOutputStream escritor = new FileOutputStream(destino)) {

            int byteLido; 
            while ((byteLido = leitor.read()) != -1) {
               
                System.out.print((char) byteLido);

                escritor.write(byteLido);
            }
            System.out.print("\nCópia byte a byte concluída com sucesso.");

        } catch (IOException e) {
            System.err.println("Ocorreu um erro na operação byte a byte: " + e.getMessage());
        }
    }

    public void lerArquivoLinhaALinha(String origem) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(origem))) {
            String linha;

          
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro na leitura linha a linha: " + e.getMessage());
        }
    }
}
