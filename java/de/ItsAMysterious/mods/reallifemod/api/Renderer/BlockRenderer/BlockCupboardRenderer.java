package de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;


import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.cupboardTE;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class BlockCupboardRenderer implements ISimpleBlockRenderingHandler
    {
    final int renderID;
    private final cupboardTE cupboard = new cupboardTE();

    public BlockCupboardRenderer(int var1)
    {
        renderID = var1;
    }

    @Override
	public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
    	GL11.glScalef(0.4F, 0.4F, 0.4F);
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -1.4F, -0.5F);
        renderCupboard(var1, var2, var3);
        GL11.glEnable(32826);
    }

    @Override
	public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int i, Block block, int j, RenderBlocks renderblocks)
    {
        return false;
    }

    @Override
	public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
	public int getRenderId()
    {
        return renderID;
    }

    public void renderCupboard(Block par1Block, int par2, float par3)
    {
        if(par1Block == TLMBlocks.cupboard)
            TileEntityRendererDispatcher.instance.renderTileEntityAt(cupboard, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}