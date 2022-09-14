package comp1721.cwk2;

/**
 * PokerHand classes creats a hand with 5 cards 
 * @author Rayaan Rizwan
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp1721.cwk2.Card.Rank;

import java.util.Collections;

public class PokerHand extends CardCollection {
    /**
     * Intialize private variables 
     */
    public static final int FULL_SIZE = 5;
    private Card card;         
    private  StringBuilder abbreviated = new StringBuilder();
    

    public PokerHand(){
        cards = new ArrayList<Card>();
    }
    /**
     * Hand constructor which creates hand with 5 cards
     * cards represents the hand
     */
    public PokerHand(String addCards){
        cards = new ArrayList<Card>();
        // Splits input by spaces
        String [] inputCards = addCards.split(" ", 0);
        // checks the amount of cards inputter
        int lengthOfCardsInputted = inputCards.length;
        if(lengthOfCardsInputted > 5){
            throw new CardException("Length of Cards inputted exceeds hand limit");
        }
        // loops through input creating Card object
        for(String cardStr: inputCards){
             card = new Card(cardStr);
             add(card);
        }
    }
    /**
     * Represents the hand as a string 
     * @return String representation of the hand 
     */
    @Override
    public String toString(){

        for(int i = 0; i < cards.size(); i++){
            abbreviated.append(cards.get(i)+" ");
        }
        if(abbreviated.length() > 0){
            return abbreviated.substring(0, abbreviated.length() - 1);
        }
        return abbreviated.toString();
    }

    /**
     * Validates the card trying to be added to the hand and is then added
     */
    public void add(Card card){
        if(super.contains(card)){
            throw new CardException("Duplicate Card");
        }
        if(cards.size() == FULL_SIZE){
            throw new CardException("Hand is full");
        }
        cards.add(card);
    }


    public int size(){
        return super.size();
     }

    public void discard(){
        if(cards.isEmpty()){
            throw new CardException("Hand is empty");
        }
          super.discard();
    }
    /**
     * This method empties the hand and discards it to the specified deck 
     * @param deck
     */
    public void discardTo(Deck deck){

        if(deck.size() >= 52 && cards.size() != 0){
            throw new CardException("Deck is full");
        }
        
        for(int i = 0; i < cards.size(); i++){
            deck.add(cards.get(i));
        }
        discard();
    }
    /**
     * Checks if the hand is a pair. And only returns true when the other methods with higher precedence returns false
     * @return Returns true if the hand is a pair and false if not
     */
    public boolean isPair(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getRank() == cards.get(y).getRank()){
                    pairs++;
                }
            }
         }
         if(pairs == 2 && !isTwoPairs() && !isFlush() && !isFullHouse() && !isThreeOfAKind() && !isFourOfAKind() && !isStraight()){
             return true;
         }
        return false;
    }
    /**
     * Checks if the hand has two pairs
     * @return True if it contains two pair and false if not
     */
    public boolean isTwoPairs(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getRank() == cards.get(y).getRank()){
                    pairs++;
                }
            }
         }
         if(pairs == 4 && !isFlush() && !isFullHouse() && !isThreeOfAKind() && !isFourOfAKind() && !isStraight()){
             return true;
         }
        return false;
    }
    /**
     * Checks if the hand contains a three of a kind
     * @return True if it contains Three of a kind and false if not
     */
    public boolean isThreeOfAKind(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getRank() == cards.get(y).getRank()){
                    pairs++;
                }
            }
         }
         if(pairs == 6 && !isFlush() && !isFullHouse() && !isFourOfAKind() && !isStraight()){
             return true;
         }
        return false;
    }
    /**
     * Checks if the hand contains a four of a kind
     * @return True if it does and false if not
     */
    public boolean isFourOfAKind(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getRank() == cards.get(y).getRank()){
                    pairs++;
                }
            }
         }
         if(pairs == 12){
             return true;
         }
         return false;
    }
    /**
     * Checks if the hand contains a full house
     * @return True if it does and false if not 
     */
    public boolean isFullHouse(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getRank() == cards.get(y).getRank()){
                    pairs++;
                }
            }
         }
         if(pairs == 8 && !isFourOfAKind()){
             return true;
         }
         return false;
    }
    /**
     * Checks if hand contains a flush
     * @return True if it does and false if not
     */
    public boolean isFlush(){
        if(cards.size() != 5){
            return false;
        }
        int pairs = 0;
         for(int x = 0; x < cards.size(); x++){
            for(int y = 0; y < cards.size(); y++){
                if(x == y){
                    continue;
                }
                if(cards.get(x).getSuit() == cards.get(y).getSuit()){
                    pairs++;
                }
            }
         }
         if(pairs == 20 && !isFullHouse() && !isFourOfAKind()){
             return true;
         }
         return false;
    }
    /**
     * Checks if hand contains a straight 
     * @return True if it does and False if it does not
     */
    public boolean isStraight(){
        if(cards.size() != 5){
            return false;
        }
        // PokerHand hand = new PokerHand("AD KH JC TD QS");
        ArrayList<Rank> rankArray = new ArrayList<Rank>();
        ArrayList<Integer> posArray = new ArrayList<Integer>();
        // int[] posArray = new int[5];
        for(Card.Rank rank: Card.Rank.values()){
            rankArray.add(rank);
        }
        for (int i = 0; i < cards.size(); i++){
            int position = rankArray.indexOf(cards.get(i).getRank());
            posArray.add(position);
        }
        Collections.sort(posArray);
        for(int i = 0; i < posArray.size(); i++){
            if(i == posArray.size()-1){
                break;
            }
            int position1 = posArray.get(i);
            int position2 = posArray.get(i+1);
            if(i == 0 && position1 == 0){
               if(position2 == 9 || position2 == 1){
                   continue;
               }
            } else if (position1 != position2-1){
                return false;
            }
        }
        if(!isFlush() && !isFullHouse() && !isFourOfAKind()){
            return true;
        }
        return false;
    }
}