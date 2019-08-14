/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.controller;

import glasscat.as1.entity.ItemEntity;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author 86185
 */
@Named(value = "historicalController")
@RequestScoped
public class HistoricalController {
private List<ItemEntity> itemEntities;
    /**
     * Creates a new instance of HistoricalController
     */
    public HistoricalController() {
    }
    
}
