package hw4;

import api.Card;

/**
 * author makuforchu Evaluator for a hand in which the rank of each card is a
 * prime number. The number of cards required is equal to the hand size.
 * 
 * The name of this evaluator is "All Primes".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator {
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 */
	public AllPrimesEvaluator(int ranking, int handSize) {
		// TODO: call appropriate superclass constructor and
		// perform other initialization
		super(ranking, handSize, handSize, "All Primes");
	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		// TODO Auto-generated method stub
		// Check from 2 to n-1

//		boolean results = true;
//		for (int i = 0; i < mainCards.length; i++)
//			if (mainCards[i].getRank() != 2 && mainCards[i].getRank() != 3 && mainCards[i].getRank() != 5
//					&& mainCards[i].getRank() != 7 || mainCards[i].getRank() <= 1) {
//				results = false;
//			}
//		return results;
		boolean result = false;
		if (mainCards.length == cardsRequired()) {
			result = true;
			for (int i = 0; i < mainCards.length; i++) {
				int rank = mainCards[i].getRank();
				int count = 0;
				for (int j = 2; j < rank / 2; j++) {
					count = rank % j;
					if (count == 0) {
						result = false;
						break;

					}
				}
			}
		}
		return result;

	}
}
