package com.spacegame.object;

import java.util.Random;

/**
 * Created by jparrott on 9/3/2016.
 */
public class World {

    private static Sector[][] sectors;
    private static int width;
    private static int height;
    private int percentFedOwned;
    private int numOfPlanets;
    private int numOfFactions;
    private int numOfTPS;
    private static int totalSectors;


    public static World generate(int width, int height){
        
        Sector[][] sectors = new Sector[width][height];

        //create sectors
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){

                //randomly generate each sector
                sectors[x][y] = Sector.generate(x, y);
                totalSectors++;
            }
        }
        return new World(sectors);
    }
    public static World generate(int width, int height, int percentFedOwned, int numOfFactions){
        Sector[][] sectors = new Sector[width][height];


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
    public static void setPlanets(int numOfPlanets){
        int averageDist = totalSectors/numOfPlanets;//breaks up the world into segments
        int counter =0;
        Random rand = new Random();
        int random = rand.nextInt(averageDist);//randomly chooses a sector within a segment

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(random == counter){
                    sectors[x][y].setPlanet();
                }
                counter++;
                if(counter == (averageDist)){//signifying the end of this segment
                    random = rand.nextInt(averageDist);//creates new spot to place a planet for the next segment
                    counter = 0;//signifying the start of next segment
                    continue;
               }
            }
        }

    }
    public static void setTradePorts(int numOfTPS){

    }




}
