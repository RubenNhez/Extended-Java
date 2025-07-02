import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String[] ranks = {"Royal Flush", "Straight Flush", "4-of-a-kind", "Full House", "Flush", "Straight",
            "3-of-a-kind", "2 Pair", "1 Pair", "High Card"};

    public void addCard(Card c){
        if(hand.size() < 5){
            hand.add(c);
        }
    }

    public String getHandRank(){
        if(hand.size() != 5){
            return "Incorrect hand size";
        }
        //sort the hand
        hand.sort(new SortCards());

        //royal flush (J,Q,K,A,10 all of the same suit)
        int counter = 0;
        int spadesroyal = 0;
        int diamondsroyal = 0;
        int clubsroyal = 0;
        int heartsroyal = 0;
        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getNumericValue() == 10 || hand.get(i).getNumericValue() == 11 || hand.get(i).getNumericValue() == 12 || hand.get(i).getNumericValue() == 13 || hand.get(i).getNumericValue() == 14 || hand.get(i).getNumericValue() == 15) {
                counter++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Spades")) {
                spadesroyal++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Diamonds")) {
                diamondsroyal++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Clubs")) {
                clubsroyal++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Hearts")) {
                heartsroyal++;
            }

            if(counter == 5 && spadesroyal == 5 ||counter == 5 && clubsroyal == 5 ||counter == 5 && diamondsroyal == 5 ||counter == 5 && heartsroyal == 5) {
                return ranks[0];

            }
        }

        //straight flush (5 cards in a row all of the same suit e.g. 3S, 4S, 5S, 6S, 7S)
        int spadesflush = 0;
        int diamondsflush = 0;
        int clubsflush = 0;
        int heartsflush = 0;

        for (int i = 0; i < hand.size(); i++) {
            if(Objects.equals(hand.get(i).getSuit(), "Spades")) {
                spadesflush++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Diamonds")) {
                diamondsflush++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Clubs")) {
                clubsflush++;
            }

            if(Objects.equals(hand.get(i).getSuit(), "Hearts")) {
                heartsflush++;
            }

            if (hand.get(1).getNumericValue() == hand.get(0).getNumericValue() + 1 && hand.get(2).getNumericValue() == hand.get(1).getNumericValue() + 1 && hand.get(3).getNumericValue() == hand.get(2).getNumericValue() + 1 && hand.get(4).getNumericValue() == hand.get(3).getNumericValue() + 1 && spadesflush==5) {
                    return ranks[1];
                }
            if(hand.get(1).getNumericValue() == hand.get(0).getNumericValue() + 1 && hand.get(2).getNumericValue() == hand.get(1).getNumericValue() + 1 && hand.get(3).getNumericValue() == hand.get(2).getNumericValue() + 1 && hand.get(4).getNumericValue() == hand.get(3).getNumericValue() + 1 && diamondsflush == 5) {
                return ranks[1];
            }
            if(hand.get(1).getNumericValue() == hand.get(0).getNumericValue() + 1 && hand.get(2).getNumericValue() == hand.get(1).getNumericValue() + 1 && hand.get(3).getNumericValue() == hand.get(2).getNumericValue() + 1 && hand.get(4).getNumericValue() == hand.get(3).getNumericValue() + 1 && clubsflush == 5) {
                return ranks[1];
            }
            if(hand.get(1).getNumericValue() == hand.get(0).getNumericValue() + 1 && hand.get(2).getNumericValue() == hand.get(1).getNumericValue() + 1 && hand.get(3).getNumericValue() == hand.get(2).getNumericValue() + 1 && hand.get(4).getNumericValue() == hand.get(3).getNumericValue() + 1 && heartsflush == 5) {
                return ranks[1];
            }


        }
        //All 6 done in a HashMap down bellow
        //4 of a kind (4 cards with the same value e.g. 9S, 9C, 9H, 9D, 7D)
        //full house (3 of a kind and a pair e.g. 7S, 7H, 7D, 4C, 4H)
        //flush (All cards are in the same suit e.g. 3H, 7H, 9H, JH, KH)
        //3 of a kind (3 cards with the same value and two others e.g. 7D, 7H, 7C, 2H, KS)
        //two pair (2 pairs of matched values e.g. 7D, 7H, 4S, 4C, 2D)
        //one pair ( a pair of cards with the same value e.g. 7D, 7H, 4S, 6H, 8H)


        HashMap<Integer , Integer> kindOf = new HashMap<>();
        HashMap<String, Integer> spades = new HashMap<>();
        HashMap<String, Integer> diamonds = new HashMap<>();
        HashMap<String, Integer> hearts = new HashMap<>();
        HashMap<String, Integer> clubs = new HashMap<>();

        for (Card card: hand){
            spades.put(card.getSuit(), 1);
            diamonds.put(card.getSuit(), 1);
            hearts.put(card.getSuit(), 1);
            clubs.put(card.getSuit(), 1);
            Integer key = card.getNumericValue();
            Integer keyValue = kindOf.get(key);

            if(keyValue == null){
                kindOf.put(key, 1);
            }else {
                kindOf.put(key, keyValue+1);
            }

        }
        int pairs = 0;
        int kind_3 = 0;
        int kind_4 = 0;

        for (Integer key: kindOf.keySet()) {
            //4 of a kind (4 cards with the same value e.g. 9S, 9C, 9H, 9D, 7D)

            if(kindOf.get(key) == 4 ){
                kind_4 = 1;
                //full house (3 of a kind and a pair e.g. 7S, 7H, 7D, 4C, 4H)

            }else if(kindOf.get(key) == 3) {
                kind_3 = 1;
            }else if(kindOf.get(key) == 2){
                pairs += 1;
            }
        }
        //4 of a kind (4 cards with the same value e.g. 9S, 9C, 9H, 9D, 7D)

        if(kind_4 == 1){
            return ranks[2];
        }
        //full house (3 of a kind and a pair e.g. 7S, 7H, 7D, 4C, 4H)

        if(kind_3 == 1 && pairs == 1){
            return ranks[3];
        }
        //flush (All cards are in the same suit e.g. 3H, 7H, 9H, JH, KH)

        if(spades.keySet().size() == 1){
            return ranks[4];
        }
        if(diamonds.keySet().size() == 1){
            return ranks[4];
        }
        if(hearts.keySet().size() == 1){
            return ranks[4];
        }
        if(clubs.keySet().size() == 1){
            return ranks[4];
        }
        //3 of a kind (3 cards with the same value and two others e.g. 7D, 7H, 7C, 2H, KS)

        if(kind_3 == 1){
            return ranks[6];
        }
        //two pair (2 pairs of matched values e.g. 7D, 7H, 4S, 4C, 2D)

        if(pairs == 2){
            return ranks[7];
        }
        //one pair ( a pair of cards with the same value e.g. 7D, 7H, 4S, 6H, 8H)

        if(pairs == 1){
            return ranks[8];
        }

        //straight (A run of values in different suits e.g. 3H, 4D, 5H, 6C, 7S)
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(1).getNumericValue() == hand.get(0).getNumericValue() + 1 && hand.get(2).getNumericValue() == hand.get(1).getNumericValue() + 1 && hand.get(3).getNumericValue() == hand.get(2).getNumericValue() + 1 && hand.get(4).getNumericValue() == hand.get(3).getNumericValue() + 1) {
                return ranks[5];
            }
        }


        //high card (None of the other hands match, the highest value of the card)

        return ranks[9];
    }

    public String toString() {
        String output = "";
        for(Card c: hand){

                if(c.getSuit().equals("Hearts") || c.getSuit().equals("Diamonds")) {
                    output += "\u001B[31m[ " + c.getValue() + " , " + c.getSuit() + " ] \u001B[0m";
                }
                else{
                    output += "[ " + c.getValue() + " , " + c.getSuit() + " ] ";
                }



        }

        return output;
    }
}
