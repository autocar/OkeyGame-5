/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

/**
 *
 * @author mustafabayraktar
 */
public class Tile {
    
    private String color;
    private int number;
    private String countOf;
    private boolean isOkey;
    private boolean isPointer;

    public Tile(String color, int number, String countOf, boolean isOkey, boolean isPointer) {
        this.color = color;
        this.number = number;
        this.countOf = countOf;
        this.isOkey = isOkey;
        this.isPointer = isPointer;
    }

    public Tile(String color, int number, String countOf) {
        this.color = color;
        this.number = number;
        this.countOf = countOf;
    }
    
    public Tile(String color, int number) {
        this.color = color;
        this.number = number;
    }
    
    public Tile() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountOf() {
        return countOf;
    }

    public void setCountOf(String countOf) {
        this.countOf = countOf;
    }

    public boolean isIsOkey() {
        return isOkey;
    }

    public void setIsOkey(boolean isOkey) {
        this.isOkey = isOkey;
    }

    public boolean isIsPointer() {
        return isPointer;
    }

    public void setIsPointer(boolean isPointer) {
        this.isPointer = isPointer;
    }
      
    @Override
    public boolean equals(Object obj) {

        return (this.color.equals(((Tile) obj).color) && this.countOf.equals(((Tile) obj).countOf) &&
                this.number == ((Tile) obj).number);

    }
    
    
    
}
