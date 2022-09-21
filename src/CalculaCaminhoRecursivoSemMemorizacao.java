import java.util.ArrayList;

public class CalculaCaminhoRecursivoSemMemorizacao {

    private String[][] matrizPercorrer;

    public CalculaCaminhoRecursivoSemMemorizacao(String[][] percurso){
        this.matrizPercorrer = percurso;
    }

    public void calculaCaminhoRecursivo(){
        ArrayList<String> caminhoPercorrido = new ArrayList<>();
        CaminhoPercorridoQuantidadeOuro calculaValor = calculaCaminho(matrizPercorrer, matrizPercorrer.length - 1, 0,
                caminhoPercorrido);
        System.out.println("Valor de ouro acumulado: " + calculaValor.valor);
        System.out.println("Caminho percorrido: " + String.join(",", calculaValor.caminhoPercorrido));
    }

    public static CaminhoPercorridoQuantidadeOuro calculaCaminho(String[][] percurso, int x, int y,
            ArrayList<String> caminhoPercorrido) {
        if (x == 0 && y == (percurso.length - 1)) {
            return new CaminhoPercorridoQuantidadeOuro(Integer.parseInt(percurso[x][y]), caminhoPercorrido);
        } else if (x == 0) {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(caminhoPercorrido);
            novoCaminhoPercorrido.add("E");
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = calculaCaminho(percurso, x, y + 1,
                    novoCaminhoPercorrido);
            return new CaminhoPercorridoQuantidadeOuro(caminhoSelecionado.valor + verificaValor(percurso[x][y]),
                    caminhoSelecionado.caminhoPercorrido);
        } else if (y == percurso.length - 1) {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(caminhoPercorrido);
            novoCaminhoPercorrido.add("N");
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = calculaCaminho(percurso, x - 1, y,
                    novoCaminhoPercorrido);
            return new CaminhoPercorridoQuantidadeOuro((caminhoSelecionado.valor + verificaValor(percurso[x][y])),
                    caminhoSelecionado.caminhoPercorrido);
        } else {
            ArrayList<String> novoCaminhoPercorridoNE = new ArrayList<>(caminhoPercorrido);
            novoCaminhoPercorridoNE.add("NE");
            ArrayList<String> novoCaminhoPercorridoN = new ArrayList<>(caminhoPercorrido);
            novoCaminhoPercorridoN.add("N");
            ArrayList<String> novoCaminhoPercorridoE = new ArrayList<>(caminhoPercorrido);
            novoCaminhoPercorridoE.add("E");
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = retornaCaminhoEscolhido(
                    calculaCaminho(percurso, x - 1, y + 1, novoCaminhoPercorridoNE),
                    calculaCaminho(percurso, x - 1, y, novoCaminhoPercorridoN),
                    calculaCaminho(percurso, x, y + 1, novoCaminhoPercorridoE));
            return new CaminhoPercorridoQuantidadeOuro(caminhoSelecionado.valor + verificaValor(percurso[x][y]),
                    caminhoSelecionado.caminhoPercorrido);
        }
    }

    public static CaminhoPercorridoQuantidadeOuro retornaCaminhoEscolhido(CaminhoPercorridoQuantidadeOuro NE,
            CaminhoPercorridoQuantidadeOuro N, CaminhoPercorridoQuantidadeOuro E) {
        if (NE.valor >= N.valor && NE.valor >= E.valor) {
            return NE;
        }
        if (N.valor >= NE.valor && N.valor >= E.valor) {
            return N;
        } else {
            return E;
        }
    }

    public static int verificaValor(String valor) {
        if (valor.equals("x")) {
            return -999999;
        } else {
            return Integer.valueOf(valor).intValue();
        }
    }
}
