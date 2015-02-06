package de.ItsAMysterious.mods.reallifemod.core.Generation;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class OreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1  : genNether();
		break;
		case 0: genWorld(world, random, chunkX*16, chunkZ*16);
		break;
		case 1 : genEnd();
		}
	
	}

	private void genWorld(World world,Random rand, int ChunkX,int ChunkZ) {
		for(int i= 0; i <30; i++){
			int randPosX=ChunkX+rand.nextInt(16);
			int randPosY=rand.nextInt(64);
			int randPosZ=ChunkZ+rand.nextInt(16);
			
			//(new WorldGenMinable(TBMEBase.KruppstahlE,0,30,Blocks.stone)).generate(world,rand, randPosX, randPosY, randPosZ );
		}
	}

	private void genEnd() {
	}

	private void genNether() {
	}


}
