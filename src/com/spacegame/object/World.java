package com.spacegame.object;

/**
 * Created by jparrott on 9/3/2016.
 */
public class World {

    private Sector[][] sectors;
    private int width;
    private int height;

    public static World generate(int width, int height){
        
        Sector[][] sectors = new Sector[width][height];

        //create sectors
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){

                //randomly generate each sector
                sectors[x][y] = Sector.generate(x, y);
            }
        }
        return new World(sectors);
    }

    public World(Sector[][] sectors){
        this.sectors = sectors;
        this.width = sectors.length;
        this.height = sectors[0].length;
    }

    public Sector getSector(int x, int y){
        //todo: add some code to do error handling
        return sectors[x][y];
    }




}
