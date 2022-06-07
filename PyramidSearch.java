import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.StructureSeed;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.math.DistanceMetric;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;


import java.util.ArrayList;
import java.util.List;


public class PyramidSearch implements Runnable {

    public static final MCVersion VERSION = MCVersion.v1_17;
    //Sets Variable "VERSION" To Minecraft 1.17, Important for generation

    public static final DesertPyramid Pyramid = new DesertPyramid(VERSION);
    //Sets "Pyramid" To a Minecraft DesertPyramid from 1.17


        public static final RPos[] POSITIONS = new RPos[] {
            new RPos(0, 0, Pyramid.getSpacing() * 16),
            new RPos(0, 1, Pyramid.getSpacing() * 16),
            new RPos(1, 0, Pyramid.getSpacing() * 16),
            new RPos(1, 1, Pyramid.getSpacing() * 16)
    };


    //Defines Variable "POSITIONS" as a Pyramid, at the region (0,0) (0,1) (1,0) (1,1)
    //See video showcase about regions

    //Spacing = 32 chunks, (depends on the structure)
    //Times 16 to change it forms making a region as block position into a chunk position



    public void run() {
        ChunkRand chunkRand = new ChunkRand();
        //"Seeds the randomizer to generate the surface terrain blocks (such as grass, sand, ect..) and the bedrock patterns."
        // This is not currently doing anything, only brings chunkRand (it's easier to keep the same name)  into our code, so we can use it later



        for (long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
            // Finds the last 48 bits of a world seed

            //A world seed is 64 bits, spilt into 16|48 the lower 48 is responsible for picking the structure location within a region

            // Long for anything greater than 32 bits, structureSeed is the tech term and library for what we are doing here, the lower looking for the lower 48 bits of data
            // With structure data

            //We are shifting 1 by 48 what is equal to 2^48 (48 bits)



            List<CPos> cPosList = new ArrayList<>(POSITIONS.length);
             for (RPos rPos : POSITIONS) {
                //What we are setting up do  here is finding the chunk position (Cpos) from the region position (Rpos)
                 // Taking our POSITIONS we defined from before we are adding them to a Cpos list



                CPos PyramidRegion = Pyramid.getInRegion(structureSeed, rPos.getX(), rPos.getZ(), chunkRand);
                // We want the CPos
                 //Defining "PyramidRegion" as a new chunk position,

                 // We are then using the library function .getInRegion to shift to Cpos
                // for the .getInRegion function to work it requires our structureSeed we made before, as well as the position
                 //of our regions we are looking for/ have data for, as well the chunkRand

                  //Control B "getInRegion"


                if (PyramidRegion != null) {
                    cPosList.add(PyramidRegion);
                    //Now,if our "cPos" is not null, meaning its not complete nothingness and has a value
                    //It is Added to our cPosList


                }
            }
            if (cPosList.size() < POSITIONS.length) continue;

            if (!(DistanceMetric.EUCLIDEAN.getDistance(
                    cPosList.get(0).getX() - cPosList.get(1).getX(),
                   cPosList.get(0).getY() - cPosList.get(1).getY(),
                    cPosList.get(0).getZ() - cPosList.get(1).getZ()) < 13)) {
               continue;
            }





            for (long upperBits = 0; upperBits < 1L << 16; upperBits++)
            //We now start a similar loop with the remaining 16 bits of the world seed


            {
                long worldSeed = StructureSeed.toWorldSeed(structureSeed, upperBits);
                //To put it simple this is defining "worldseed"  and with help of .toWorldSeed
                //we are shifting by 48 (bits) and putting the structureSeed at the bottom
                //Leaving us with our upper bits in front and lower bits "structureSeed" in the back
                // A full 64 bits, a world seed


                BiomeSource biomeSource = BiomeSource.of(Dimension.OVERWORLD, VERSION, worldSeed);
                //We now want to confirm that it can even spawn in the correct Biome in the first place
                //Taking our world seed variable we just made, as well as the "VERSION" and defining the Dimension as the overwrold
                //we build our biomeSource variable



                boolean canSpawnAtBoth = true;
                //new boolean "CanSpawnAtBoth" is equal to true

                for (CPos cPos : cPosList) {
                    canSpawnAtBoth &= Pyramid.canSpawn(cPos, biomeSource);
                }


                if (canSpawnAtBoth) System.out.println(worldSeed);
                //The following chunk of code checks if our Region Data from cPosList can spawn
                //if that's a possibly a world seed is printed



            }
        }
    }








    public static void main(String[] args) {
        new PyramidSearch().run();
    }
    //allows me to run it
    //^very important
}
