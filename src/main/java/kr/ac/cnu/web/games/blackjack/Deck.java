package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoMoreCardException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Deck {
    @Getter
    private final int number;
    @Getter
    private final List<Card> cardList;

    public Deck(int number) {
        this.number = number;
        this.cardList = new ArrayList<Card>();
        createCards(number);
        Collections.shuffle(cardList);
    }

    private void createCards(int number) {
        // create card for single deck
        for (int j = 0; j < number; j++) {
            for (Suit suit : Suit.values()) {
                for (int i = 1 ; i < 14; i++) {
                    Card card = new Card(i, suit);
                    cardList.add(card);
                }
            }
        }
    }

    public Card drawCard() {
        if (cardList.size() == 0) {
            // TODO 실제 게임에서 이런 일이 절대로 일어나면 안되겠죠?
            // 그래서 보통 게임에서는 N 장의 카드가 남으면 모든 카드를 합쳐서 다시 셔플 합니다.
            // 코드에 그런 내용이 들어가야 함.
            throw new NoMoreCardException();

            // 기능추가 10 . 카드의 숫자가 10보다 작아지면
            // 새로운 하나의 덱을 섞어서 기존의 덱과 합친다.
        } else if(cardList.size()<10){
            Deck newdeck = new Deck(1);

            cardList.addAll(newdeck.cardList);
        }
        return cardList.remove(0);
    }

}
