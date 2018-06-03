/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author mustafabayraktar
 */
public class Okey {

    /**
     * @param args the command line arguments
     */
    
    private static List<Tile> tileList;
    private static List<Tile> sameColorSortList;
    private static List<Tile> sameNumberSortList;
    private static List<Tile> sortedList;
    private static String[] color= {"Red","Black","Blue","Yellow"};
    private static boolean hasJoker = true;
    private static Tile[] okeyTile;
    private static List<Tile> randomTileList;
    private static int key=0;
    
    public static void main(String[] args) {
        
        randomTileList = new ArrayList<Tile>();  
        createAndSuffleTiles();
        
        Random rand = new Random();
        int randomNumber = rand.nextInt(104) + 1;
        int okeyNumber = 0;
        Tile tile = randomTileList.get(randomNumber);
        
        if(tile.getNumber() == 13)
            okeyNumber = 1;
        else
            okeyNumber=tile.getNumber() + 1;
        
        tile.setIsPointer(true);
        randomTileList.add(tile);
        
        if(tile.getCountOf().equals("first")){
            Tile newTile = new Tile(tile.getColor(),tile.getNumber(),"second");
            newTile.setIsPointer(true);
        randomTileList.add(newTile);
        }
        else{
        Tile newTile = new Tile(tile.getColor(),tile.getNumber(),"first");
            newTile.setIsPointer(true);
        randomTileList.add(newTile); 
        }
        
      
        Tile tile1 = new Tile(tile.getColor(),okeyNumber,"first"); 
        randomTileList.remove(tile1);
        tile1.setIsOkey(true);
        randomTileList.add(tile1);
        
        Tile tile2 = new Tile(tile.getColor(),okeyNumber,"second");
        randomTileList.remove(tile2);
        tile2.setIsOkey(true);
        randomTileList.add(tile2);
        
        okeyTile = new Tile[2];
        
        okeyTile[0] = tile1;
        okeyTile[1] = tile2;
        
        Collections.shuffle(randomTileList);
        shareTiles();
   
    }
    
    private static void shareTiles()
    {
        Random rand = new Random();
        int firstplayerNumber = rand.nextInt(4) + 1;     
        Player playerArrray[] = new Player[4];
        for(int i=1;i<5;i++)
        {
            
        tileList = new ArrayList<Tile>(); 
        
        Player player = new Player(i,tileList);
        int count = 0;  
        for(int j=randomTileList.size()-1;j>=0;j--)
        {
           count++;
           if(randomTileList.get(j).isIsOkey())
               player.setHasOkey(true);
           tileList.add(randomTileList.get(j)); 
           randomTileList.remove(j);
           
           if(count==14 && i!=firstplayerNumber)
           break;
           else if(count==15)
           break;   
               
        }
        
        int ind = i - 1;
        playerArrray[ind] = player;          
        
        }
        startGame(playerArrray);
        
    }
 
    private static void createAndSuffleTiles()
    {
        
        
        for(int i=0;i<4;i++)
        {
            for(int j=1;j<14;j++)
            {
               randomTileList.add(new Tile(color[i],j,"first"));
               randomTileList.add(new Tile(color[i],j,"second"));
            }
        }
        
        Collections.shuffle(randomTileList);
       
    }
    
    private static void startGame(Player[] player)
    {
        for(int a=0;a<player.length;a++){
          
          sortedList = new ArrayList<Tile>();
 
          tileList = player[a].getTileList();
          
          tileList.sort(Comparator.comparing(Tile::getNumber));
          
          for(int i=0;i<tileList.size();i++)
          {
              Tile currentTile = tileList.get(i);
              
              if(sortedList.contains(currentTile))
              continue;
              
              if(currentTile.isIsOkey())
                continue;
              // check if okey
              
              sameColorSortList = new ArrayList<Tile>();
              sameNumberSortList = new ArrayList<Tile>();
              
              int sameColorCount = sameColorSorted(player[a],currentTile);
              int sameNumberCount = sameNumberSorted(player[a],currentTile);
              
              addToSortedList(player[a],sameColorCount,sameNumberCount);              
              
          }
           
        // add okey if exist
    }
        
        checkTheGame(player);

    }
    
    private static void checkTheGame(Player[] player)
    {
        int minimumSize = player[0].getGameList().size();
        
        
        Player winnerPlayer = new Player();
        
        winnerPlayer = player[0];
        
        for(int i=1;i<player.length;i++)
          {
              if(minimumSize > player[i].getGameList().size())
              {
                 winnerPlayer = new Player();
                 winnerPlayer = player[i];
                 minimumSize = player[i].getGameList().size();
              }
             
              
          }
        System.out.println("Player "+winnerPlayer.getPlayerNumber()+" won the game!");
        
         for(int i=0;i<winnerPlayer.getGameList().size();i++)
          {
              List<Tile> tile = winnerPlayer.getGameList().get(i);
             for(int j=0;j<tile.size();j++)
          { 
             System.out.println(tile.get(j).getColor() + " " + tile.get(j).getNumber());
          }
              
          }   
        
    }
    
    
    private static void addToSortedList(Player player,int sameColorCount,int sameNumberCount)
    {
        
        
        if(sameColorCount>=sameNumberCount)
        {
            for(int i=0;i<sameColorSortList.size();i++)
            {
                if(!sortedList.contains(sameColorSortList.get(i))){
                sortedList.add(sameColorSortList.get(i));           
                }
                
            }
            player.getGameList().add(sameColorSortList);
     
            
        }
        else
        {
            for(int i=0;i<sameNumberSortList.size();i++)
            {
                if(!sortedList.contains(sameNumberSortList.get(i)))
                sortedList.add(sameNumberSortList.get(i));
            }
            
            player.getGameList().add(sameNumberSortList);

        }
    }
    
    private static int sameColorSorted(Player player, Tile currentTile)
          {
              int count =0;  
                           
              Tile newTile1 = new Tile(currentTile.getColor(),currentTile.getNumber()+count,"first");             
              Tile newTile2 = new Tile(currentTile.getColor(),currentTile.getNumber()+count,"second");
              
              while((tileList.contains(newTile1) && !sortedList.contains(newTile1)) 
                      || (tileList.contains(newTile2) && !sortedList.contains(newTile2)))
              {  
                 if(tileList.contains(newTile1))
                 sameColorSortList.add(newTile1);
                 else
                 sameColorSortList.add(newTile2);   
                 //ind++;
                 count++;
                 
                 newTile1 = new Tile(currentTile.getColor(),currentTile.getNumber()+count,"first");
                 newTile2 = new Tile(currentTile.getColor(),currentTile.getNumber()+count,"second");
                 
                 if((player.isHasOkey()) && 
                         (!sortedList.contains(okeyTile[0]) || !sortedList.contains(okeyTile[1]))) {     
                    if(!tileList.contains(newTile1) || !tileList.contains(newTile2))
                    {
                     newTile1 = new Tile(currentTile.getColor(),currentTile.getNumber()+count+1,"first");             
                     newTile2 = new Tile(currentTile.getColor(),currentTile.getNumber()+count+1,"second");
                     
                     if(tileList.contains(newTile1) || tileList.contains(newTile2))
                     {
                        if(tileList.contains(okeyTile[0])) 
                        sameColorSortList.add(okeyTile[0]);
                        else
                        sameColorSortList.add(okeyTile[1]);   
                        count++;
                     }
                     
                    }
              }
                 }
                                           
              
              return count;
          }
    
     private static int sameNumberSorted(Player player,Tile currentTile)
          {
              int count =0;  
              
              for(int i = 0;i<color.length;i++)
              {
                  Tile colorTile1 = new Tile(color[i],currentTile.getNumber(),"first");
                  Tile colorTile2 = new Tile(color[i],currentTile.getNumber(),"second");
                  
                  if((tileList.contains(colorTile1) && !sortedList.contains(colorTile1))
                          || ( tileList.contains(colorTile2) && !sortedList.contains(colorTile2)))
                    {
                        if(tileList.contains(colorTile1))
                        sameNumberSortList.add(colorTile1);
                        else
                        sameNumberSortList.add(colorTile2);
                        count++; 
                    }
              }
              
              return count;
          } 
    
}
