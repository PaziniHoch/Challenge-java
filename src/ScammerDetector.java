
import java.util.HashMap;
import java.util.Map;

public class ScammerDetector {

    private final Map<String, Integer> palavrasChave = new HashMap<>();

    public ScammerDetector() {

        // ===== GOLPE =====
        palavrasChave.put("chave pix cadastrada com sucesso", 3);
        palavrasChave.put("código de verificação", 3);
        palavrasChave.put("código de segurança", 3);
        palavrasChave.put("confirme o número enviado por sms", 3);
        palavrasChave.put("dispositivo não autorizado", 3);
        palavrasChave.put("pagar taxa de liberação", 3);
        palavrasChave.put("senha expirou", 3);
        palavrasChave.put("herança", 3);
        palavrasChave.put("loteria", 3);
        palavrasChave.put("ganha de r$ 500 a r$ 2.000 por dia", 3);
        palavrasChave.put("clique no link para resgatar", 3);

        // ===== SUSPEITOS =====
        palavrasChave.put("compra aprovada", 2);
        palavrasChave.put("conta bloqueada", 2);
        palavrasChave.put("transação suspeita", 2);
        palavrasChave.put("cancele aqui", 2);
        palavrasChave.put("não reconheço", 2);
        palavrasChave.put("atualização obrigatória", 2);
        palavrasChave.put("atualize seus dados", 2);
        palavrasChave.put("sua conta será suspensa", 2);
        palavrasChave.put("objeto retido", 2);
        palavrasChave.put("taxa de alfândega", 2);
        palavrasChave.put("taxa dos correios", 2);
        palavrasChave.put("tentativa de entrega falhou", 2);
        palavrasChave.put("atualize seu endereço", 2);
        palavrasChave.put("renda extra diária", 2);
        palavrasChave.put("apenas 10 a 20 minutos por dia", 2);
        palavrasChave.put("bloqueio", 2);
        palavrasChave.put("urgente", 2);
        palavrasChave.put("desconto de 90%", 2);
        palavrasChave.put("preço exclusivo para você", 2);

        // ===== AUTÊNTICO =====
        palavrasChave.put("promoção", 1);
        palavrasChave.put("sorteio", 1);
        palavrasChave.put("você ganhou", 1);
        palavrasChave.put("ganhou", 1);
        palavrasChave.put("premiado", 1);
        palavrasChave.put("parabéns", 1);
        palavrasChave.put("últimas unidades", 1);
        palavrasChave.put("clique aqui", 1);
        palavrasChave.put("trabalhe de casa", 1);
        palavrasChave.put("home office", 1);
        palavrasChave.put("tarefas simples", 1);
        palavrasChave.put("vencedor", 1);
    }

    // ======== DEFINIR LIMITE =======
    private static final int LIMIAR_SUSPEITO = 4;

    public boolean analisarEmail(String textoEmail) {
        return calcularPontuacaoRisco(textoEmail) >= LIMIAR_SUSPEITO;
    }

    // Retorna a pontuação total de risco (útil para debug ou exibir nível de risco)
    public int calcularPontuacaoRisco(String textoEmail) {
        String textoMinusculo = textoEmail.toLowerCase();
        int pontuacaoTotal = 0;

        for (Map.Entry<String, Integer> entrada : palavrasChave.entrySet()) {
            if (textoMinusculo.contains(entrada.getKey())) {
                pontuacaoTotal += entrada.getValue();
            }
        }

        return pontuacaoTotal;
    }
}