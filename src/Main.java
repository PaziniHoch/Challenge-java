import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ScammerDetector detector = new ScammerDetector();
        Scanner scan = new Scanner(System.in);


        System.out.println("Digite a mensagem suspeita que deseja verificar:");
        String emailSuspeito = scan.nextLine();
        System.out.println("Estamos verificando, aguarde...");

        int pontuacao = detector.calcularPontuacaoRisco(emailSuspeito);
        boolean suspeito = detector.analisarEmail(emailSuspeito);
        String Visual = detector.preprocessarTexto(emailSuspeito);
        System.out.println(Visual);

        System.out.println("Pontuação de risco: " + pontuacao);
        System.out.println("Com base na pontuação atingida, avaliamos que " + (suspeito ? "tem grandes chances de ser golpe!" : "tem poucas chances de ser golpe."));
    }
}
