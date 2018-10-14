import java.util.ArrayList;

public class Dealer extends Speler {
    static boolean dealerBlackjack=false;
    static boolean spelerBlackjack=false;
    static int spelerScore;
    static int dealerScore;

    public static Card dealCard() {
        Card topCard = CardDeck.newDeck.remove(0);
        return topCard;
    }

    static void puntenDisplay(ArrayList<Card> spelerhand) {
        int totalValue = 0;
        int resValue = 0;

        for (Card c : spelerhand) {
            totalValue += c.value;
            resValue += c.resVal;
        }
        if (spelerhand.size() == 2 && totalValue == 21) {
            if (gameSys.dealer.hand.get(0).value >=10){
                System.out.println("Gefeliciteerd u heeft Blackjack!");
                System.out.println();
                System.out.println("De dealer kan ook een Blackjack hebben, spannend!");
                System.out.println();
                spelerBlackjack=true;
                spelerScore=totalValue;
                dealerPlay();
            } else {
                System.out.println("Gefeliciteerd u heeft Blackjack!");
                System.out.println("Aangezien de dealer een " + gameSys.dealer.hand.get(0).name + "heeft open liggen, kan hij nooit een Blackjack maken");
                System.out.println("U heeft gewonnen!");
                spelerBlackjack=true;
                spelerScore=totalValue;
                gameSys.endGame();
            }
        } else if (totalValue == 21) {
            System.out.println("Gefelciteerd u heeft 21 punten!");
            spelerScore=totalValue;
            dealerPlay();
        } else if (totalValue > 21 && resValue > 21) {
            System.out.println("Helaas u heeft meer dan 21 punten en heeft daarmee deze ronde verloren");
            gameSys.endGame();
        } else if (totalValue > 21 && resValue == 21) {
            System.out.println("Gefelciteerd u heeft 21 punten!");
            spelerScore=resValue;
            dealerPlay();
        } else if (totalValue > 21 && (resValue+10) <= 21) {
            if ((resValue+10)==21){
                System.out.println("Gefelciteerd u heeft 21 punten!");
                spelerScore=(resValue+10);
                dealerPlay();
            } else {
                System.out.println("U heeft " + (resValue + 10) + " punten");
                spelerScore=(resValue+10);
            }
        } else {
            System.out.println("U heeft " + totalValue + " punten");
            spelerScore=totalValue;
        }
    }

    static void toonKaarten(ArrayList<Card> spelerhand) {
        System.out.print("U heeft de volgende kaarten in uw hand: ");
        for (Card c : spelerhand) {
            System.out.print(c.name + ", ");
        }
        System.out.print("- ");
    }


    static void dealerKaarten() {
        String toonkaart = gameSys.dealer.hand.get(0).name;
        System.out.println("De dealer heeft één gesloten kaart en een " + toonkaart);
        System.out.println();
    }

    static void compareBlackJack(){
        if (spelerBlackjack && dealerBlackjack) {
            System.out.println("Zowel de dealer als jij hebben een BlackJack - Gelijkspel!");
        } else {
            System.out.println("De dealer heeft Blackjack en wint!");
        }
        gameSys.endGame();
    }


    static void compareScores() {
        if (spelerBlackjack) {
            System.out.println();
            System.out.println("Je hebt gewonnen met jouw BlackJack!");
        }else if (spelerScore>dealerScore){
            System.out.println();
            System.out.println("Gefeliciteerd je hebt gewonnen met " + spelerScore +" punten!");
        } else if (spelerScore==dealerScore) {
            System.out.println();
            System.out.println("Een gelijkspel! Zowel de dealer als jij eindigen met " + spelerScore + " punten!");
        } else if (dealerScore>21){
            System.out.println();
            System.out.println("De dealer heeft zijn hand overspeeld, jij wint deze ronde!");
        } else {
            System.out.println();
            System.out.println("Helaas, de dealer heeft " + dealerScore + " punten en wint deze ronde!");
        }
        gameSys.endGame();
    }

    static void dealerPlay() {
        ArrayList<Card> dealerHand = gameSys.dealer.hand;
        int dealerValue = 0;
        System.out.print("De dealer had de " + gameSys.dealer.hand.get(0).name + " reeds open en draait nu de gesloten kaart om en toont de " + gameSys.dealer.hand.get(1).name +" - ");
        for (Card c : dealerHand) {
            dealerValue += c.value;
        }
            if (spelerBlackjack && dealerValue != 21){
                System.out.println("De dealer heeft " + dealerValue + " punten");
                compareScores();
            } else if (dealerValue==22) {
                dealerScore = 12;
                dealerPlaysOn();
            }
            else if (dealerValue == 21) {
                dealerBlackjack = true;
                compareBlackJack();
            } else if (dealerValue >= 17){
                System.out.println("De dealer past");
                dealerScore=dealerValue;
                compareScores();
            } else {
                System.out.println("De dealer heeft " + dealerValue + " punten");
                dealerScore=dealerValue;
                dealerPlaysOn();
            }
    }
    static void dealerPlaysOn(){
        int totaldValue;
        int resdValue;
        while (dealerScore<17) {
            totaldValue = 0;
            resdValue = 0;
            ArrayList<Card> dealerHand = gameSys.dealer.hand;
            Card drawnCard = Dealer.dealCard();
            System.out.println();
            System.out.println("De dealer trekt de " + drawnCard.name);
            System.out.println();
            dealerHand.add(drawnCard);
            System.out.print("De dealer heeft de volgende kaarten in de hand: ");
            for (Card c : dealerHand) {
                totaldValue += c.value;
                resdValue += c.resVal;
                System.out.print(c.name + ", ");
            }
            System.out.print(" - ");

            if (totaldValue == 21) {
                System.out.println("De dealer heeft 21 punten!");
                dealerScore = totaldValue;
                compareScores();
            } else if (totaldValue > 21 && resdValue > 21) {
                System.out.println("De dealer heeft meer dan 21 punten u heeft deze ronde van Blackjack gewonnen!");
                gameSys.endGame();
            } else if (totaldValue > 21 && resdValue == 21) {
                System.out.println("De dealer heeft 21 punten!");
                dealerScore = resdValue;
                compareScores();
            } else if (totaldValue > 21 && (resdValue + 10) <= 21) {
                if ((resdValue + 10) == 21) {
                    System.out.println("De dealer heeft 21 punten!");
                    dealerScore = (resdValue + 10);
                    compareScores();
                } else {
                    System.out.println("De dealer heeft " + (resdValue + 10) + " punten");
                    dealerScore = (resdValue + 10);
                }
            } else {
                System.out.println("De dealer heeft " + totaldValue + " punten");
                dealerScore = totaldValue;
            }
        } if (dealerScore < 21 ) {
            System.out.println();
            System.out.println("De dealer past met " + dealerScore + " punten");
            compareScores();

        } else {
            System.out.println("De dealer heeft zijn hand overspeeld");
            compareScores();
        }
    }
}