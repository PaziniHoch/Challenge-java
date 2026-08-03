import java.text.Normalizer;
import java.util.Map;

public class ScammerDetector {

    private final Map<String, Integer> palavrasChave;

    private static final int LIMIAR_SUSPEITO = 4;

    public ScammerDetector() {
        this.palavrasChave = PalavrasChave.carregarPalavras();
    }

    public boolean analisarEmail(String textoEmail) {
        return calcularPontuacaoRisco(textoEmail) >= LIMIAR_SUSPEITO;
    }

    public int calcularPontuacaoRisco(String textoEmail) {

        String textoFormatado = Normalizer.normalize(textoEmail, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();

        int pontuacaoTotal = 0;

        for (Map.Entry<String, Integer> entrada : palavrasChave.entrySet()) {
            if (textoFormatado.contains(entrada.getKey())) {
                pontuacaoTotal += entrada.getValue();
            }
        }

        return pontuacaoTotal;
    }
}