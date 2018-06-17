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
        // Ace가 11로 반영된 카드 숫자
        int aceElevenCount = 0;
        for (Card card :cardList) {
            // Bugfix5 코드에 Bugfix4 코드 추가

            // 합이 10보다 작으면 Ace 11
            if(card.getRank() == 1 && tmp_sum <= 10) {
                tmp_sum += 11;
                aceElevenCount++;

                // 합이 10보다 크면 Ace 1
            }else if(card.getRank() == 1&& tmp_sum >= 11){
                tmp_sum += 1;
            }else if(card.getRank() < 11) {
                tmp_sum += card.getRank();
            }
            else if(card.getRank() >= 11) {
                tmp_sum += 10;
            }
        }

        // Bugfix4
        // Ace 11 중 1로 처리해야되는지 결정
        for(int y =0; y < aceElevenCount; aceElevenCount--){
            if(tmp_sum<=21){
                break;
                // 21을 넘어가면 Ace 숫자가 가능한 만큼 1로 처리
            } else if(tmp_sum>21 && aceElevenCount!=0){
                tmp_sum = tmp_sum-10;
            }
        }

        return tmp_sum;
    }

    public void reset() {
        cardList.clear();
    }
}
