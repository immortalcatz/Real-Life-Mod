package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.radioTE;
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

public class radioTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	private bankBlock theblock;
	
	public radioTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/radio.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((radioTE) te, x, y, z, scale);
	}
	
	public void doRender(radioTE te, double x, double y, double z, float scale) {
		glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslated((float)x+0.5f, (float)y-te.getHeightToSubstract(), (float)z+0.5f);
		    int facing = te.getFacingDirection();
		    int k = 0;
		    //South
		    if (facing == 2) {k = 0;}
		    //North
		    if (facing == 3) {k = 180;}
		    //East
		    if (facing == 4) {k = -90;}
		    //West
		    if (facing == 5) {k = 90;}
		    glRotatef(k, 0.0F, 1.0F, 0.0F);
		    glScalef(0.7F, 0.7F, 0.7F);
		    this.bindTexture(new ResourceLocation("reallifemod:textures/blocks/furniture/radio.png"));
		    this.model.renderAll();
		    glEnable(GL_CULL_FACE);
		    glDisable(GL_ALPHA_TEST);
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
