package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NotEnoughBalanceException;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {
        if(balance <= 0) {
            throw new NotEnoughBalanceException();
            // 기능추가 9
            // 현재금액이 베팅단위보다 적을경우 All in
        } else if(balance< bet){
            currentBet = balance;
            balance = 0;
        } else{
            balance -= bet;
            currentBet = bet;
        }

        isPlaying = true;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win() {
        balance += currentBet * 2;
        currentBet = 0;
    }

    public void tie() {
        balance += currentBet;
        currentBet = 0;
    }

    // 기능추가 3
    // 블랙잭일 경우 1.5배로 ( 2배의 1.5배)
    public void blackjack() {
        balance += currentBet * 3;
        //balance -= currentBet;
        currentBet = 0;
    }

    public void lost() {
        currentBet = 0;
    }

    public Card hitCard() {
        return hand.drawCard();
    }

    public void stand() {
        this.isPlaying = false;
         // currentBet=0;
    }

    public void double_down_bet(long bet) {
        System.out.println("player_double_down_bet");
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }

        // 금액을 빼간다고 가정하므로 다시 채워줌
        balance -= bet;

        currentBet = bet * 2;

        //isPlaying = true;
    }
}
