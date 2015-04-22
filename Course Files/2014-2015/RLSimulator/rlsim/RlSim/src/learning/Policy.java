/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.HashMap;

/**
 *
 * @author alessandrobisiani
 */
public interface Policy {
    
    
    String next(HashMap stateRewardMap);

    
}
