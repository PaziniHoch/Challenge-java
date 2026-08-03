import java.sql.SQLOutput;
import java.text.Normalizer;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ScammerDetector detector = new ScammerDetector();
        Scanner scan = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("             SCAMMER DETECTOR");
        System.out.println("=========================================");
        System.out.println("\nCole abaixo a mensagem ou e-mail que deseja verificar.");

        String emailSuspeito = scan.nextLine();
        System.out.println("Estamos verificando, aguarde...");

        int pontuacao = detector.calcularPontuacaoRisco(emailSuspeito);
        boolean suspeito = detector.analisarEmail(emailSuspeito);

        System.out.println("Pontuação de risco: " + pontuacao);
        System.out.println("Com base na pontuação atingida, avaliamos que " + (suspeito ? "este email é Suspeito de Golpe!" : "este email não é Suspeito."));


        String recomendacao;

        do {
            System.out.println("\nDeseja receber recomendações de segurança?");
            System.out.println("- Sim");
            System.out.println("- Não");
            System.out.print("Escolha uma opção: ");

            recomendacao = scan.nextLine().trim().toLowerCase();

            if (!recomendacao.equals("sim") &&
                !recomendacao.equals("nao") &&
                !recomendacao.equals("não")) {
            System.out.println("Resposta inválida. Digite apenas Sim ou Não.");

        }} while (!recomendacao.equals("sim") &&
            !recomendacao.equals("nao") &&
            !recomendacao.equals("não"));

        if (recomendacao.equals("sim")) {
            System.out.println("=========================================");
            System.out.println("      RECOMENDAÇÕES DE SEGURANÇA");
            System.out.println("=========================================");
            System.out.println("✔ Não clique em links suspeitos.");
            System.out.println("✔ Nunca informe códigos recebidos por SMS.");
            System.out.println("✔ Confirme a informação pelo site oficial.");
            System.out.println("✔ Verifique o remetente antes de responder.");
            System.out.println("✔ Em caso de dúvida, ignore a mensagem.");
            System.out.println("=========================================");
        } else {
            System.out.println("\nObrigado por utilizar o Scammer Detector!");
        }


    }
}
