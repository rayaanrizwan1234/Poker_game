package comp1721.cwk2;

/**
 * Creates a deck of 52 cards 
 * 
 * Implements methods to manipulate the deck
 * 
 * @author Rayaan Rizwan
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck extends CardCollection{
    private Card card;
    /**
     * Constructor for the deck. 
     * Creates the actual deck using cards list from CardCollection
     */
    public Deck(){
        cards = new ArrayList<Card>();
        for(Card.Suit suit: Card.Suit.values()){
            for(Card.Rank rank: Card.Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }
    /**
     * @return The size of the deck
     */
    public int size(){
    return super.size();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    } 
    /**
     * If deck contains a specified card
     * @param Card 
     * @return returns true if the deck has the card
     */
    public boolean contains(Card card){
        return super.contains(card);
    }

    public void discard(){
        super.discard();
    }
    /**
     * Removes the first card from the deck
     * @return An object of card 
     */
    public Card deal(){
        if(cards.isEmpty()){
            throw new CardException("Deck is empty");
        }
        card = cards.get(0);
        cards.remove(0);
        return card;
    }
    /**
     * Shuffles the deck
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }
}
