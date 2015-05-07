/*
 */
package learning;

import java.util.HashMap;

/**
 * An action selection policy for use by learning.Learner.
 * It simply guarantees that implementing classes provide an implementation for 
 * String next(HashMap stateRewardMap, int episodeNumber).
 * @author Alessandro Bisiani
 * @version v1.0 - 1 May 2015
 */
public interface Policy{
    
    /**
     * Selects the next action based on available action-reward pairs and implementation specific parameters.
     * @param stateRewardMap    A HashMap of available next states mapped to their rewards.
     * @param episodeNumber     The number of episodes for which the experiment has run +1.
     * @return                  The selected action in the form of a String representing the state it leads to.
     */
    String next(HashMap stateRewardMap, int episodeNumber);

}
