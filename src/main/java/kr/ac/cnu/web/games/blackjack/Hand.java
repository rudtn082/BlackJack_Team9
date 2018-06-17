package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        int tmp_sum = 0;
        for (Card card :cardList) {
            if(card.getRank() < 11) {
                tmp_sum += card.getRank();
            }
            else if(card.getRank() >= 11) {
                tmp_sum += 10;
            }
        }
        return tmp_sum;
    }

    public void reset() {
        cardList.clear();
    }
}
