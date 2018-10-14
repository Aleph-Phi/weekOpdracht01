import java.util.Scanner;

public class gameSys {
    static Scanner sc = new Scanner(System.in);
    static Speler speler1 = new Speler();
    static Dealer dealer = new Dealer();

    static void startMenu() {
        System.out.println();
        System.out.println("Toets 'n' om een nieuw spel Blackjack te starten");
        System.out.println("Toets 'q' om het spel af te sluiten");
        System.out.println();
        char userInput = sc.next().charAt(0);
        char convInput = Character.toLowerCase(userInput);
        if (convInput == 'n') {

            CardDeck.cardGen();
            speler1.hand.add(Dealer.dealCard());
            dealer.hand.add(Dealer.dealCard());
            speler1.hand.add(Dealer.dealCard());
            dealer.hand.add(Dealer.dealCard());

            Dealer.dealerKaarten();

            Dealer.toonKaarten(speler1.hand);
            Dealer.puntenDisplay(speler1.hand);
            gameMenu();

        } else if (convInput == 'q') {
            System.out.println();
            System.out.println("Bedankt voor het spelen van Blackjack");
            System.exit(0);
        } else {
            System.out.println("Het is mij onduidelijk wat u wilt");
            startMenu();
        }
    }

    static void gameMenu() {

        while (true) {
            System.out.println();
            System.out.println("Toets 'k' voor een nieuwe kaart");
            System.out.println("Toets 'p' om te passen");
            System.out.println("Toets 'q' om het spel te stoppen");
            System.out.println();
            char userInput = sc.next().charAt(0);
            char convInput = Character.toLowerCase(userInput);
            if (convInput == 'k') {
                Card getrokkenKaart = Dealer.dealCard();
                speler1.hand.add(getrokkenKaart);
                System.out.println("Je trekt de " + getrokkenKaart.name);
                System.out.println();

                Dealer.toonKaarten(speler1.hand);
                Dealer.puntenDisplay(speler1.hand);
                gameMenu();
            } else if (convInput == 'q') {
                endGame();
            } else if (convInput == 'p') {
                Dealer.dealerPlay();
            } else {
                System.out.println("Helaas ik herken die invoer niet, probeer het nogmaals");
            }
        }
    }

    static void endGame() {
        speler1.hand.clear();
        dealer.hand.clear();
        Dealer.spelerBlackjack = false;
        Dealer.dealerBlackjack = false;
        System.out.println();
        System.out.println("Wilt u een nieuwe ronde Blackjack starten?");
        startMenu();
    }
}