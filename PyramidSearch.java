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

    public static final DesertPyramid Pyramid = new DesertPyramid(VERSION);


        public static final RPos[] POSITIONS = new RPos[] {
            new RPos(0, 0, Pyramid.getSpacing() * 16),
            new RPos(0, 1, Pyramid.getSpacing() * 16),
            new RPos(1, 0, Pyramid.getSpacing() * 16),
            new RPos(1, 1, Pyramid.getSpacing() * 16)
    };



    public void run() {
        ChunkRand chunkRand = new ChunkRand();
      


        for (long structureSeed = 0; structureSeed < 1L << 48; structureSeed++) {
    


            List<CPos> cPosList = new ArrayList<>(POSITIONS.length);
             for (RPos rPos : POSITIONS) {
         


                CPos PyramidRegion = Pyramid.getInRegion(structureSeed, rPos.getX(), rPos.getZ(), chunkRand);
             


                if (PyramidRegion != null) {
                    cPosList.add(PyramidRegion);
               

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
          
            {
                long worldSeed = StructureSeed.toWorldSeed(structureSeed, upperBits);
                

                BiomeSource biomeSource = BiomeSource.of(Dimension.OVERWORLD, VERSION, worldSeed);
              

                boolean canSpawnAtBoth = true;
            
                for (CPos cPos : cPosList) {
                    canSpawnAtBoth &= Pyramid.canSpawn(cPos, biomeSource);
                }


                if (canSpawnAtBoth) System.out.println(worldSeed);
            

            }
        }
    }








    public static void main(String[] args) {
        new PyramidSearch().run();
    }
    
}
