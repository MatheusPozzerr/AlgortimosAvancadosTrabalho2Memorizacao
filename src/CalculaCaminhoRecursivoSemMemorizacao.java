import java.util.ArrayList;

public class CalculaCaminhoRecursivoSemMemorizacao {

    private String[][] matrizPercorrer;

    public CalculaCaminhoRecursivoSemMemorizacao(String[][] percurso) {
        this.matrizPercorrer = percurso;
    }

    public void calculaCaminhoRecursivo() {
        ArrayList<String> caminhoPercorrido = new ArrayList<>();
        int calculaValor = calculaCaminho(matrizPercorrer, matrizPercorrer.length - 1, 0);
        System.out.println("Valor de ouro acumulado: " + calculaValor);
    }

    public int calculaCaminho(String[][] percurso, int x, int y) {
        if (x == 0 && y == (percurso.length - 1)) {
            return verificaValor(percurso[x][y]);
        } else if (x == 0) {
            int qntdOuro = calculaCaminho(percurso, x, y + 1);
            return qntdOuro + verificaValor(percurso[x][y]);
        } else if (y == percurso.length - 1) {
            int qntdOuro = calculaCaminho(percurso, x - 1, y);
            return qntdOuro + verificaValor(percurso[x][y]);
        } else {
            int caminhoSelecionado = retornaCaminhoEscolhido(
                    calculaCaminho(percurso, x - 1, y + 1),
                    calculaCaminho(percurso, x - 1, y),
                    calculaCaminho(percurso, x, y + 1));
            return caminhoSelecionado + verificaValor(percurso[x][y]);
        }
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

    private int verificaValor(String valor) {
        if (valor.equals("x")) {
            return -999999;
        } else {
            return Integer.valueOf(valor).intValue();
        }
    }
}
