import java.util.ArrayList;
import java.util.Collections;

public class CalculcaCaminhoRecursivoComMemorizacao {

    private CaminhoPercorridoQuantidadeOuro[][] matrizMelhorCaminho;
    private String[][] matrizPercorrer;

    public CalculcaCaminhoRecursivoComMemorizacao(String[][] percurso) {
        this.matrizPercorrer = percurso;
        this.matrizMelhorCaminho = new CaminhoPercorridoQuantidadeOuro[matrizPercorrer.length][matrizPercorrer.length];
    }

    public void calculaCaminhoMemorizacao() {
        ArrayList<String> caminhoPercorrido = new ArrayList<>();
        CaminhoPercorridoQuantidadeOuro calculaValor = calculaCaminho(matrizPercorrer, matrizPercorrer.length - 1, 0,
                caminhoPercorrido);
        System.out.println("Valor de ouro acumulado: " + calculaValor.valor);
        System.out.println("Tsteee" + matrizMelhorCaminho[3][2].caminhoPercorrido.toString());
        Collections.reverse(calculaValor.caminhoPercorrido);
        System.out.println("Caminho percorrido: " + String.join(",", calculaValor.caminhoPercorrido));
    }

    public CaminhoPercorridoQuantidadeOuro verificaCaminhoOtimizado(int x, int y) {
        if (this.matrizMelhorCaminho[x][y] == null) {
            return null;
        } else {
            return this.matrizMelhorCaminho[x][y];
        }
    }

    public CaminhoPercorridoQuantidadeOuro calculaCaminho(String[][] percurso, int x, int y,
            ArrayList<String> caminhoPercorrido) {
        if (verificaCaminhoOtimizado(x, y) != null) {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>();
            CaminhoPercorridoQuantidadeOuro melhorCaminho = verificaCaminhoOtimizado(x, y);
            System.out.println(melhorCaminho.caminhoPercorrido.toString()+ "VAPOVAPOVPAO " + x + " " + y);
            for (String caminho : melhorCaminho.caminhoPercorrido) {
                novoCaminhoPercorrido.add(caminho);
            }
            System.out.println(novoCaminhoPercorrido.toString()+ "KKKK " + x + " " + y);
            return new CaminhoPercorridoQuantidadeOuro(melhorCaminho.valor, novoCaminhoPercorrido);
        }
        if (x == 0 && y == (percurso.length - 1)) {
            return new CaminhoPercorridoQuantidadeOuro(Integer.parseInt(percurso[x][y]), caminhoPercorrido);
        } else if (x == 0) {
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = calculaCaminho(percurso, x, y + 1,
                    caminhoPercorrido);
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(caminhoSelecionado.caminhoPercorrido);
            novoCaminhoPercorrido.add("E");
            if (verificaCaminhoOtimizado(x, y) == null) {
                adicionaMelhorCaminho(x, y,
                        new CaminhoPercorridoQuantidadeOuro((caminhoSelecionado.valor + verificaValor(percurso[x][y])),
                                novoCaminhoPercorrido));
            }
            return new CaminhoPercorridoQuantidadeOuro(caminhoSelecionado.valor + verificaValor(percurso[x][y]),
                    novoCaminhoPercorrido);
        } else if (y == percurso.length - 1) {
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = calculaCaminho(percurso, x - 1, y,
                    caminhoPercorrido);
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(caminhoSelecionado.caminhoPercorrido);
            novoCaminhoPercorrido.add("N");
            if (verificaCaminhoOtimizado(x, y) == null) {
                adicionaMelhorCaminho(x, y,
                        new CaminhoPercorridoQuantidadeOuro((caminhoSelecionado.valor + verificaValor(percurso[x][y])),
                                novoCaminhoPercorrido));
            }
            return new CaminhoPercorridoQuantidadeOuro((caminhoSelecionado.valor + verificaValor(percurso[x][y])),
                    novoCaminhoPercorrido);
        } else {
            CaminhoPercorridoQuantidadeOuro caminhoSelecionado = retornaCaminhoEscolhido(
                    calculaCaminho(percurso, x - 1, y + 1, caminhoPercorrido),
                    calculaCaminho(percurso, x - 1, y, caminhoPercorrido),
                    calculaCaminho(percurso, x, y + 1, caminhoPercorrido));
            CaminhoPercorridoQuantidadeOuro caminhoFinal = new CaminhoPercorridoQuantidadeOuro(
                    caminhoSelecionado.valor + verificaValor(percurso[x][y]),
                    caminhoSelecionado.caminhoPercorrido);
            if (verificaCaminhoOtimizado(x, y) == null) {
                adicionaMelhorCaminho(x, y, caminhoFinal);
            }
            return caminhoFinal;
        }
    }

    public void adicionaMelhorCaminho(int x, int y, CaminhoPercorridoQuantidadeOuro caminhoFinal) {
        matrizMelhorCaminho[x][y] = caminhoFinal;
    }

    private CaminhoPercorridoQuantidadeOuro retornaCaminhoEscolhido(CaminhoPercorridoQuantidadeOuro NE,
            CaminhoPercorridoQuantidadeOuro N, CaminhoPercorridoQuantidadeOuro E) {
        if (NE.valor >= N.valor && NE.valor >= E.valor) {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(NE.caminhoPercorrido);
            novoCaminhoPercorrido.add("NE");
            NE.caminhoPercorrido = novoCaminhoPercorrido;
            return NE;
        }
        if (N.valor >= NE.valor && N.valor >= E.valor) {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(N.caminhoPercorrido);
            novoCaminhoPercorrido.add("N");
            N.caminhoPercorrido = novoCaminhoPercorrido;
            return N;
        } else {
            ArrayList<String> novoCaminhoPercorrido = new ArrayList<>(E.caminhoPercorrido);
            novoCaminhoPercorrido.add("E");
            E.caminhoPercorrido = novoCaminhoPercorrido;
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
