package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        int dealerResult = dealer.getHand().getCardSum();

        playerMap.forEach((s, player) -> {
            int playerResult = player.getHand().getCardSum();
            // 기능추가 3
            // Ace + 10 = 21일 경우, 즉 카드를 받자마자 21일 경우 블랙잭
            if ((playerResult == 21) && (player.getHand().getCardList().size() == 2)) {
                player.blackjack();
            } else if (playerResult > 21) {
                player.lost();
            } else if(dealerResult > 21) {
                player.win();
            } else if (playerResult > dealerResult) {
                player.win();
            } else if (playerResult == dealerResult) {
                player.tie();
            } else {
                player.lost();
            }
        });

        return true;
    }


}
