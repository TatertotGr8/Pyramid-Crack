//ALL LIBRARIES ARE FROM SITE “https://kaptainwutax.seedfinding.com”

//library importing Minecraft biomes information
import kaptainwutax.biomeutils.source.BiomeSource;
//library importing Minecraft Desert Pyramid structure information
import kaptainwutax.featureutils.structure.DesertPyramid;
//library importing a function to find basic information about Minecraft chunks
import kaptainwutax.mcutils.rand.ChunkRand;
//library importing a function that allows us to find the last 48 bits of a seed *TechNameStructureSeed*  (the bits that  determine structure generation, which is what I’m primarily looking at here)
import kaptainwutax.mcutils.rand.seed.StructureSeed;
//library importing a function to identify the dimension (between Overworld, Nether and //End) that the program is working within
import kaptainwutax.mcutils.state.Dimension;
//library importing a math function that takes a value and determines distance between another value
import kaptainwutax.mcutils.util.math.DistanceMetric;
//library defining a region position value
import kaptainwutax.mcutils.util.pos.CPos;
//library defining a region position value
import kaptainwutax.mcutils.util.pos.RPos;
//library importing the version of minecraft we are running
import kaptainwutax.mcutils.version.MCVersion;

//imports a library that lets me make array lists within the program
import java.util.ArrayList;
//imports library that lets me make lists within the program
import java.util.List;

//Defines java class as ‘PyramidSearch’ and makes it a callable function
public class PyramidSearch implements Runnable {
    //creates and defines variable “VERSION” as minecraft version 1.17
    public static final MCVersion VERSION = MCVersion.v1_17;

    //creates and defines variable ‘Pyramid’ as the desert temple structure from
    //game version  1.17
    public static final DesertPyramid Pyramid = new DesertPyramid(VERSION);

//creates and defines variable “POSITIONS” as region positions(0,0),  (0,1) , //(1,0), and (1,1).
// Gets region spacing for Pyramid structure
// (multiplied by 16 to convert from block position because a chunk is 16 blocks), giving me the regions I want temples to spawn in.
        public static final RPos[] POSITIONS = new RPos[] {
            new RPos(0, 0, Pyramid.getSpacing() * 16),
            new RPos(0, 1, Pyramid.getSpacing() * 16),
            new RPos(1, 0, Pyramid.getSpacing() * 16),
            new RPos(1, 1, Pyramid.getSpacing() * 16)
    };

    //creates and defines variable “chunkRand” to be used later as a renamed ChunkRand function
    public void run() {
        ChunkRand chunkRand = new ChunkRand();
            //takes the structure defined above and shifts the values
        // until I have the last 48 bits of a world seed.
        for (long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
            List<CPos> cPosList = new ArrayList<>(POSITIONS.length);
             for (RPos rPos : POSITIONS) {

                 //creates and defines variable “PyramidRegion” by using function “.getInRegion”
                //to find the pyramid’s chunk position within the region with parameters defined
                //by variables above (structureSeed, rPos.getX(), rPos.getZ(), and chunkRand)
                 CPos PyramidRegion = Pyramid.getInRegion(structureSeed, rPos.getX(), rPos.getZ(), chunkRand);



                 //Checks if there is the possibility of pyramids spawning in the region returned
                 // above. If there is that possibility, the seed it corresponds to is added to the list.
                 if (PyramidRegion != null) {
                    cPosList.add(PyramidRegion);
                }
            }
            //confirms region has the possibility to spawn a pyramid; another null check basically
            if (cPosList.size() < POSITIONS.length) continue;

            //Discards seed and loops back to finding pyramid regions of a new seed if the distance between pyramids on the Z axis is more than 13 chunks.
            // This isn’t necessary, but we want our pyramids close y’all.
            //This is mostly meant to practice the math libraries/java
            if (!(DistanceMetric.EUCLIDEAN.getDistance(
                    cPosList.get(0).getX() - cPosList.get(1).getX(),
                   cPosList.get(0).getY() - cPosList.get(1).getY(),
                    cPosList.get(0).getZ() - cPosList.get(1).getZ()) < 13)) {
               continue;
            }



            //There are 16 bits remaining to account for to build a full 64 bit world seed, so I
            // shift back up to find the bits cut out when shifting for structure seed.
            for (long upperBits = 0; upperBits < 1L << 16; upperBits++)


            {
                //creates and defines a variable “worldSeed” by taking structure seed (lower 48 //bits) and
                // combining it with the upperBits (upper 16 bits) to form a full-on, proper //world seed
                long worldSeed = StructureSeed.toWorldSeed(structureSeed, upperBits);


            //pulls from library BiomeSource to define variable ‘biomeSource’ as a //function that that
                // determines what biome the chunks belong to by using the dimension (overworld in this case),
                // version, and world seed we just found.
                //We do this last because biome sourcing takes FOREVER.
                BiomeSource biomeSource = BiomeSource.of(Dimension.OVERWORLD, VERSION, worldSeed);

                //queries for whether the four pyramids can spawn in the positions defined
                // based on biome parsed above
                boolean canSpawnAtBoth = true;
                for (CPos cPos : cPosList) {
                    canSpawnAtBoth &= Pyramid.canSpawn(cPos, biomeSource);
                }

                //if the pyramids can indeed spawn, it prints the world seed on a new line!
                if (canSpawnAtBoth) System.out.println(worldSeed);




            }
        }
    }
    //allows me to run it
    //very important
    public static void main(String[] args) {
        new PyramidSearch().run();
    }
}
