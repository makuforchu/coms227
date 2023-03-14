package hw4;

import api.Card;

/**
 * author makuforchu Evaluator for a hand containing (at least) two cards of the
 * same rank. The number of cards required is two.
 * 
 * The name of this evaluator is "One Pair".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class OnePairEvaluator extends AbstractEvaluator {

	// private Hand hands;

	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 */
	public OnePairEvaluator(int ranking, int handSize) {
		// TODO: call appropriate superclass constructor and
		// perform other initialization
		super(ranking, handSize, 2, "One Pair");

	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		// TODO Auto-generated method stub

		return (mainCards.length == cardsRequired() && mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0);
	}

}
