
import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

public class ScammerDetector {

    private final Map<String, Integer> palavrasChave = new HashMap<>();

    public ScammerDetector() {

        // ===== GOLPE =====
        palavrasChave.put("pix", 3);
        palavrasChave.put("codigo de verificacao", 3);
        palavrasChave.put("codigo de seguranca", 3);
        palavrasChave.put("confirme o numero enviado por sms", 3);
        palavrasChave.put("pagar taxa de liberacao", 3);
        palavrasChave.put("senha expirou", 3);
        palavrasChave.put("heranca", 3);
        palavrasChave.put("loteria", 3);
        palavrasChave.put("ganha de r$ 500 a r$ 2.000 por dia", 3);
        palavrasChave.put("clique no link", 3);
        palavrasChave.put("clique aqui", 3);

        // ===== SUSPEITOS =====
        palavrasChave.put("compra aprovada", 2);
        palavrasChave.put("conta bloqueada", 2);
        palavrasChave.put("transacao suspeita", 2);
        palavrasChave.put("cancele aqui", 2);
        palavrasChave.put("nao reconheco", 2);
        palavrasChave.put("atualizacao obrigatoria", 2);
        palavrasChave.put("atualize seus dados", 2);
        palavrasChave.put("sua conta sera suspensa", 2);
        palavrasChave.put("objeto retido", 2);
        palavrasChave.put("taxa de alfandega", 2);
        palavrasChave.put("taxa dos correios", 2);
        palavrasChave.put("tentativa de entrega falhou", 2);
        palavrasChave.put("atualize seu endereco", 2);
        palavrasChave.put("renda extra", 2);
        palavrasChave.put("apenas 10 a 20 minutos por dia", 2);
        palavrasChave.put("bloqueio", 2);
        palavrasChave.put("urgente", 2);
        palavrasChave.put("desconto de 90%", 2);
        palavrasChave.put("preco exclusivo", 2);

        // ===== AUTENTICO =====
        palavrasChave.put("dispositivo nao autorizado", 3);
        palavrasChave.put("promocao", 1);
        palavrasChave.put("sorteio", 1);
        palavrasChave.put("voce ganhou", 1);
        palavrasChave.put("premiado", 1);
        palavrasChave.put("parabens", 1);
        palavrasChave.put("ultimas unidades", 1);
        palavrasChave.put("trabalhe de casa", 1);
        palavrasChave.put("home office", 1);
        palavrasChave.put("tarefas simples", 1);
        palavrasChave.put("vencedor", 1);
    }

    private static final int LIMIAR_SUSPEITO = 4;

    public boolean analisarEmail(String textoEmail) {
        return calcularPontuacaoRisco(textoEmail) >= LIMIAR_SUSPEITO;
    }


    public int calcularPontuacaoRisco(String textoEmail) {
//        String textoMinusculo = textoEmail.toLowerCase();
        String textoFormatado = Normalizer.normalize(textoEmail,Normalizer.Form.NFD)
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