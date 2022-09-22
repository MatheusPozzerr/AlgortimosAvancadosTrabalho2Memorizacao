import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Nenhum arquivo texto inserido. Leia o README.md");
            return;
        }
        String arquivo = args[0];
        String[][] matrizPercorrer = leTexto(arquivo);
        CalculaCaminhoRecursivoSemMemorizacao caminhoSemMemorizacao = new CalculaCaminhoRecursivoSemMemorizacao(matrizPercorrer);
        caminhoSemMemorizacao.calculaCaminhoRecursivo();
        CalculcaCaminhoRecursivoComMemorizacao calculcaCaminhoRecursivoComMemorizacao = new CalculcaCaminhoRecursivoComMemorizacao(matrizPercorrer);
        calculcaCaminhoRecursivoComMemorizacao.calculaCaminhoMemorizacao(); 

    }

    public static void printaMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " | ");
            }
        }
        System.out.println();
    }

    public static String[][] leTexto(String nomeArquivo) throws IOException {
        String[][] matriz = new String[0][0];
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String line = br.readLine();
            line.trim();
            int matrizLength = Integer.parseInt(line);
            boolean isFirstLine = false;
            int index = 0;
            while (line != null && index < matrizLength) {
                if (!isFirstLine) {
                    isFirstLine = true;
                    matriz = new String[matrizLength][matrizLength];
                    line = br.readLine();
                } else {
                    String[] splited = line.trim().split("\\s+");
                    for (int i = 0; i < matriz.length; i++) {
                        matriz[index][i] = splited[i];
                    }
                    index++;
                    line = br.readLine();
                }
            }
            br.close();
            return matriz;
        } catch (IOException e) {
            System.out.println(e);
        }
        return matriz;
    }
}
