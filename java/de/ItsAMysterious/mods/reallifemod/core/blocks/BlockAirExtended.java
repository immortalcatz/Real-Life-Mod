package de.ItsAMysterious.mods.reallifemod.core.blocks;

import de.ItsAMysterious.mods.reallifemod.TLM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockAirExtended extends Block{
	public static float TemperaturC;
	
	  public BlockAirExtended()
	    {
		  super(Material.wood);
		  setBlockName("airExtended");
		  TemperaturC=28;
		  this.setCreativeTab(TLM.Outdoor);
	    }

	    /**
	     * The type of render function that is called for this block
	     */
	    @Override
		public int getRenderType()
	    {
	        return -1;
	    }

	    /**
	     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	     * cleared to be reused)
	     */
	    @Override
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	    {
	        return null;
	    }

	    /**
	     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	     */
	    @Override
		public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    /**
	     * Returns whether this block is collideable based on the arguments passed in 
	     * @param par1 block metaData 
	     * @param par2 whether the player right-clicked while holding a boat
	     */
	    @Override
		public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
	    {
	        return false;
	    }

	    /**
	     * Drops the block items with a specified chance of dropping the specified items
	     */
	    @Override
		public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

}
