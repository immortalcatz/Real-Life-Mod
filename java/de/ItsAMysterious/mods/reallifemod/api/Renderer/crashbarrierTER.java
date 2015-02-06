package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.crashbarrierTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import static org.lwjgl.opengl.GL11.*;

public class crashbarrierTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	
	public crashbarrierTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/Leitplanke.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		doRender((crashbarrierTE) te, x, y, z, scale);
	}
	
	public void doRender(crashbarrierTE te, double x, double y, double z, float scale) {
		glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslatef((float)x+0.5f, (float)y, (float)z+0.5f);
		    glScalef(0.7f, 0.7f, 0.7f);
		    int facing = te.getFacingDirection();
		    int k = 0;
		    //South
		    if (facing == 2) {k = 180;}
		    //North
		    if (facing == 3) {k = 0;}
		    //East
		    if (facing == 4) {k = -90;}
		    //West
		    if (facing == 5) {k = 90;}
		    glRotatef(k, 0.0F, 1.0F, 0.0F);
		    this.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
		    this.model.renderAll();
	    glPopMatrix();
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
	        Tessellator tess = Tessellator.instance;
	        float brightness = block.getMixedBrightnessForBlock(world, i, j, k);
	        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
	        int modulousModifier = skyLight % 65536;
	        int divModifier = skyLight / 65536;
	        tess.setColorOpaque_F(brightness, brightness, brightness);
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  modulousModifier,  divModifier);
	}
}
