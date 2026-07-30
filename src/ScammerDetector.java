
import java.util.HashMap;
import java.util.Map;

import java.text.Normalizer;
import java.util.regex.Pattern;

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


    public static final int LIMIAR_SUSPEITO = 4;
    public static final int MAX_DISTANCIA_ERRO = 2;


    public boolean analisarEmail(String textoEmail) {
        return calcularPontuacaoRisco(textoEmail) >= LIMIAR_SUSPEITO;
    }

    public int calcularPontuacaoRisco(String textoEmail) {
        if (textoEmail == null || textoEmail.isEmpty()) {
            return 0;
        }

        String TextoTratado = preprocessarTexto(textoEmail);
        int pontuacaoTotal = 0;

        // Separamos o texto do e-mail em palavras individuais para o Levenshtein
        String[] palavrasDoEmail = TextoTratado.split("\\s+");

        for (Map.Entry<String, Integer> entrada : palavrasChave.entrySet()) {
            String termoAlvo = entrada.getKey();
            int peso = entrada.getValue();

            // Camada 1: Busca direta e exata (Se o texto limpo já tiver a frase, pontua direto)
            if (TextoTratado.contains(termoAlvo)) {
                pontuacaoTotal += peso;
                continue; // Pula para a próxima palavra-chave do mapa
            }

            // Camada 2: Inteligência Fuzzy (Levenshtein) para capturar erros e camuflagens
            String[] palavrasAlvo = termoAlvo.split(" ");
            int palavrasEncontradas = 0;

            for (String palavraAlvo : palavrasAlvo) {
                if (palavraAlvo.length() <= 3) {
                    // Palavras muito curtas (de, com, em) são ignoradas no erro ortográfico
                    continue;
                }

                for (String palavraEmail : palavrasDoEmail) {
                    int distancia = calcularDistanciaLevenshtein(palavraAlvo, palavraEmail);

                    if (distancia <= MAX_DISTANCIA_ERRO) {
                        palavrasEncontradas++;
                        break; // Encontrou aproximação, passa para a próxima palavra da frase alvo
                    }
                }
            }

            // Se o golpista errou algumas palavras, mas todas as palavras longas da frase suspeita estão lá
            int palavrasRelevantesAlvo = 0;
            for (String p : palavrasAlvo) {
                if (p.length() > 3) {
                    palavrasRelevantesAlvo++;
                }
            }

            if (palavrasRelevantesAlvo > 0 && palavrasEncontradas >= palavrasRelevantesAlvo) {
                pontuacaoTotal += peso;
            }
        }

        return pontuacaoTotal;
    }

    private int calcularDistanciaLevenshtein(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // CORREÇÃO: Inicialização segura da matriz para qualquer tamanho de palavra (inclusive tamanho 1)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Execução do cálculo de distância
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    // Certifique-se de que este método preprocessarTexto está na sua classe
    public String preprocessarTexto(String texto) {
        if (texto == null) return "";

        String resultado = texto.toLowerCase();

        // Remove o '@' se estiver no meio de letras (ex: expir@ou -> expirou)
        resultado = resultado.replaceAll("(?<=\\p{L})@(?=\\p{L})", "");

        // Se sobrou algum '@' isolado ou no fim, vira 'a'
        resultado = resultado.replace("@", "a");

        // Substitui outros leetspeaks visuais
        resultado = resultado.replace("0", "o")
                .replace("1", "i")
                .replace("!", "i")
                .replace("3", "e")
                .replace("4", "a")
                .replace("5", "s");

        // Remove acentos
        String nfdNormalizedString = Normalizer.normalize(resultado, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        resultado = pattern.matcher(nfdNormalizedString).replaceAll("");

        return resultado.trim();
    }
}


