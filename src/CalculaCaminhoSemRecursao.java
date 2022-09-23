import java.util.ArrayList;

public class CalculaCaminhoSemRecursao {
    private String[][] matrizPercorrer;
    private int[][] matrizQuantidadeOuro;

    public CalculaCaminhoSemRecursao(String[][] percurso) {
        this.matrizPercorrer = percurso;
    }

    public void calculaCaminhoNaoRecursio() {
        matrizQuantidadeOuro = new int[matrizPercorrer.length][matrizPercorrer.length];
        for (int i = 0; i < matrizPercorrer.length; i++) {
            for (int j = matrizPercorrer.length - 1; j >= 0; j--) {
                matrizQuantidadeOuro[i][j] = calculaCaminho(matrizPercorrer, i, j);
            }
        }
        ArrayList<String> caminhoFinal = calculaCaminhoFinal(matrizPercorrer.length - 1, 0);
        System.out.println("Valor de ouro acumulado: " + matrizQuantidadeOuro[matrizPercorrer.length - 1 ][0]);
        System.out.println("Caminho percorrido: " + String.join(",", caminhoFinal));
    }

    public static void printaMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " | ");
            }
        }
        System.out.println();
    }

    private int verificaValor(String valor) {
        if (valor.equals("x")) {
            return -999999;
        } else {
            return Integer.valueOf(valor).intValue();
        }
    }

    public int calculaCaminho(String[][] percurso, int x, int y) {
        if (x == 0 && y == (percurso.length - 1)) {
            return verificaValor(percurso[x][y]);
        } else if (x == 0) {
            int qntdOuro = matrizQuantidadeOuro[x][y + 1];
            return qntdOuro + verificaValor(percurso[x][y]);
        } else if (y == percurso.length - 1) {
            int qntdOuro = matrizQuantidadeOuro[x - 1][y];
            return qntdOuro + verificaValor(percurso[x][y]);
        } else {
            int caminhoSelecionado = retornaCaminhoEscolhido(
                    matrizQuantidadeOuro[x - 1][y + 1],
                    matrizQuantidadeOuro[x - 1][y],
                    matrizQuantidadeOuro[x][y + 1]);
            return caminhoSelecionado + verificaValor(percurso[x][y]);
        }
    }

    private ArrayList<String> calculaCaminhoFinal(int x, int y) {
        ArrayList<String> caminhoFinal = new ArrayList<>();
        while (x != 0 || y != matrizPercorrer.length - 1) {
            if (x == 0) {
                caminhoFinal.add("E");
                y++;
            }
            else if (y == matrizQuantidadeOuro.length - 1) {
                System.out.println("Entrei aqui por ultimo");
                caminhoFinal.add("N");
                x--;
            } else {
                int NE = matrizQuantidadeOuro[x - 1][y + 1];
                int N = matrizQuantidadeOuro[x - 1][y];
                int E = matrizQuantidadeOuro[x][y + 1];
                if (NE >= N && NE >= E) {
                    caminhoFinal.add("NE");
                    x--;
                    y++;
                }
                if (N >= NE && N >= E) {
                    caminhoFinal.add("N");
                    x--;
                } if(E > N && E > NE) {
                    caminhoFinal.add("E");
                    y++;
                }
            }
        }
        return caminhoFinal;
    }

    private int retornaCaminhoEscolhido(int NE,
            int N, int E) {
        if (NE >= N && NE >= E) {
            return NE;
        }
        if (N >= NE && N >= E) {
            return N;
        } else {
            return E;
        }
    }
}
