/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mustafabayraktar
 */
public class Player {
    
    private int playerNumber;
    private List<Tile> tileList;
    private int countTile;
    private Tile jokerTile;
    private boolean hasOkey;
    private ArrayList<List<Tile>> gameList;

    public Player() {
    }
    

    public Player(int playerNumber, List<Tile> tileList) {
        this.playerNumber = playerNumber;
        this.tileList = tileList;
        gameList = new ArrayList<List<Tile>>();
        
    }

    public ArrayList<List<Tile>> getGameList() {
        return this.gameList;
    }

    public void setGameList(ArrayList<List<Tile>> gameList) {
        this.gameList = gameList;
    }
    
    public Player(int playerNumber, List<Tile> tileList, Tile jokerTile) {
        this.playerNumber = playerNumber;
        this.tileList = tileList;
        this.jokerTile = jokerTile;
    }

    public Player(int playerNumber, List<Tile> tile, int countTile, Tile jokerTile) {
        this.playerNumber = playerNumber;
        this.tileList = tileList;
        this.countTile = countTile;
        this.jokerTile = jokerTile;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public List<Tile> getTile() {
        return tileList;
    }

    public void setTile(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public int getCountTile() {
        return countTile;
    }

    public void setCountTile(int countTile) {
        this.countTile = countTile;
    }

    public Tile getJokerTile() {
        return jokerTile;
    }

    public void setJokerTile(Tile jokerTile) {
        this.jokerTile = jokerTile;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public void setTileList(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public boolean isHasOkey() {
        return hasOkey;
    }

    public void setHasOkey(boolean hasOkey) {
        this.hasOkey = hasOkey;
    }   
}
