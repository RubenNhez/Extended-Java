public class CardMain {
    public static void main(String[] args) {
        Deck d = new Deck(true);
        System.out.println(d);

        Hand h = new Hand();
        for(int i = 0; i<5; i++) {
            h.addCard(d.deal());
        }
//        h.addCard(d.dealSpecificCard("7","Spades"));
//        h.addCard(d.dealSpecificCard("8","Diamonds"));
//        h.addCard(d.dealSpecificCard("5","Clubs"));
//        h.addCard(d.dealSpecificCard("J","Hearts"));
//        h.addCard(d.dealSpecificCard("K","Spades"));

        System.out.println(h);
        System.out.println(h.getHandRank());


    }
}
