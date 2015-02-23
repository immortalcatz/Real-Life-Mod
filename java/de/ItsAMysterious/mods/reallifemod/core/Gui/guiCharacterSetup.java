package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import de.ItsAMysterious.mods.reallifemod.api.gui.GuiEdit;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadiogroup;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadiobutton;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class guiCharacterSetup extends GuiScreen{

	private GuiButton NameChanging;
    private EntityPlayer thePlayer;
    private int GuiLifePage;
	private int middleX, mouseX;
	private int middleY, mouseY;
	private float playerRot;
	public int state;
	public String date, theGender;
	private GuiEdit Name,Surname;
	public GuiRadiogroup Gender=new GuiRadiogroup(130, this.height/2+50);;
	private float rotation=0;

	public guiCharacterSetup(){
		this.thePlayer=Minecraft.getMinecraft().thePlayer;
        Keyboard.enableRepeatEvents(true);
        this.state=0;
	}
	
	@Override
	public void updateScreen(){
		this.Gender.doUpdate();
		Name.updateCursorCounter();
		Surname.updateCursorCounter();
		this.rotation+=0.5;
        super.updateScreen();
	}
	
	@Override
	public void initGui()
	{
		Date zeitstempel = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		this.date=simpleDateFormat.format(zeitstempel);
		this.theGender=ClientProxy.Genders.getOrDefault(Minecraft.getMinecraft().thePlayer.getDisplayName(),"male");
		GuiRadiobutton male=new GuiRadiobutton(130, this.height/2+50, "male");
		if(this.theGender=="male"){
			male.checked=true;
			GuiRadiogroup.currentButton=male;
		}
		GuiRadiogroup.buttonList.put(0, male);
		GuiRadiobutton female=new GuiRadiobutton(130+fontRendererObj.getStringWidth("female")+5, this.height/2+50, "female");
		if(this.theGender=="female"){
			female.checked=true; 
			GuiRadiogroup.currentButton=female;
		}
		GuiRadiogroup.buttonList.put(1, female);
		if(ClientProxy.EntityNames.get(Minecraft.getMinecraft().thePlayer.getDisplayName().toString())==null)
		state=0;
		else this.state=1;
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0,this.width-45,this.height-25,40,20,"Next"));
        Name=new GuiEdit(fontRendererObj,180, 50, 100, 15);
		Surname=new GuiEdit(fontRendererObj, 180, 75, 100, 15);
		Name.setText(ClientProxy.EntityNames.getOrDefault(Minecraft.getMinecraft().thePlayer.getDisplayName().toString(),"Steve"));
		Surname.setText((ClientProxy.EntitySurnames.getOrDefault(Minecraft.getMinecraft().thePlayer.getDisplayName().toString(),"No Surname!")));
		date=ClientProxy.BirthDates.getOrDefault(this.thePlayer.getDisplayName(), this.date);
		super.initGui();
		ClientProxy.doNameUpdate();
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	@Override
	public void drawScreen(int p1, int p2, float p3) 
    {
		if(this.state==0){
			this.drawCenteredString(fontRendererObj, "Welcome "+Minecraft.getMinecraft().thePlayer.getDisplayName(), this.width/2, 50, Color.white.getRGB());
			this.drawCenteredString(fontRendererObj, "Thank you for downloading the Real life mod! You will now", this.width/2, 65, Color.white.getRGB());
			this.drawCenteredString(fontRendererObj, "have to choose your ingame name and setup your character.", this.width/2, 80, Color.white.getRGB());
		}else
		if(this.state==1){
			this.buttonList.clear();
			this.buttonList.add(new GuiButton(1,this.width-45,this.height-25,40,20,"Finish"));
		this.drawBackground(0);
		this.drawGradientRect(0, 30, this.width, 40, Color.black.getRGB(), 0);
		this.drawGradientRect(0, height-40, this.width, this.height-30,0, Color.black.getRGB());
		//this.drawRect(20, this.height/2-60, 100, height/2+60, Color.black.getRGB());
		guiCharacterSetup.renderEntity(70, this.height/2+90, 90, this.rotation, this.mouseY+this.height/2-40, this.thePlayer);
		this.drawCenteredString(fontRendererObj, "Character Setup", this.width/2, 15, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Name: " , 130, 50, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Surname: " , 130, 75, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Date of birth: "+EnumChatFormatting.WHITE	+this.date, 130, 100, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Haircolor: "+EnumChatFormatting.WHITE	+"Not included yet", 130, 115, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Eyecolor:"+EnumChatFormatting.WHITE+" Not included yet", 130, 130, Color.white.getRGB());
		this.drawString(fontRendererObj, EnumChatFormatting.BLUE+"Skincolor:"+ EnumChatFormatting.WHITE+" Not included yet", 130, 145, Color.white.getRGB());
		this.Name.drawTextBox();
		this.Surname.drawTextBox();
		this.Gender.draw();
		}
	    super.drawScreen(p1, p2, p3);

    }
	
    @Override
	protected void actionPerformed(GuiButton button) 
	{
    	if(this.state==0){
    		this.state+=1;
    	}
    	if(this.state==1){
		if(button.id==1){
			if(this.Name.getText()!=""&&this.Surname.getText()!=""&& this.Gender.getSelectedbutton()!=null && ClientProxy.Money.get(mc.thePlayer.getDisplayName())!=null)
			{
				ClientProxy.setName(mc.thePlayer,this.Name.getText(), this.Surname.getText(),this.date, this.Gender.getSelectedbutton().getText(), ClientProxy.Money.get(mc.thePlayer.getDisplayName()));
			}
			mc.displayGuiScreen(null);
		}
    	}

		super.actionPerformed(button);
	}
    
	@Override
	public void keyTyped(char taste,int id){
		if(this.state==1){
		if(Name.textboxKeyTyped(taste, id)){};
		if(id==Keyboard.KEY_TAB) 
				if(this.Name.isFocused()){
			this.Name.setFocused(false);
			this.Surname.setFocused(true);
		}else
		if(this.Surname.isFocused()){
			this.Surname.setFocused(false);
			this.Name.setFocused(true);
		}else
			if(!(this.Name.isFocused()&&this.Surname.isFocused()))this.Name.setFocused(true);
		if(Surname.textboxKeyTyped(taste, id)){
		};
		if(id==Keyboard.KEY_RETURN){
			this.actionPerformed((GuiButton)this.buttonList.get(0));
		}
		}
		super.keyTyped(taste, id);
	}
	
    public static void renderEntity(int p_147046_0_, int p_147046_1_, int p_147046_2_, float rot, float p_147046_4_, EntityLivingBase p_147046_5_)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(p_147046_0_, p_147046_1_, 100.0F);
        GL11.glScalef((-p_147046_2_), p_147046_2_, p_147046_2_);
        GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
        float f2 = p_147046_5_.renderYawOffset;
        float f3 = p_147046_5_.rotationYaw;
        float f4 = p_147046_5_.rotationPitch;
        float f5 = p_147046_5_.prevRotationYawHead;
        float f6 = p_147046_5_.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135, 0.0F, 1.0F, 0.0F);
        //GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        p_147046_5_.renderYawOffset = (float)Math.atan(rot / 40.0F) * 20.0F;
        p_147046_5_.rotationYaw = (float)Math.atan(rot / 40.0F) * 40.0F;
        p_147046_5_.rotationPitch = -((float)Math.atan(p_147046_4_ / 40.0F)) * 20.0F;
        p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
        p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
        GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
        RenderManager.instance.playerViewY = rot;
        RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        p_147046_5_.renderYawOffset = f2;
        p_147046_5_.rotationYaw = f3;
        p_147046_5_.rotationPitch = f4;
        p_147046_5_.prevRotationYawHead = f5;
        p_147046_5_.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    @Override
	public void handleMouseInput()
    {
    	super.handleMouseInput();
        this.mouseX =-(Mouse.getEventX() * this.width / this.mc.displayWidth);
        this.mouseY =-(this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1);
    }
    
	@Override
	public void mouseClicked(int x,int y,int id){
		this.Gender.onMouseClicked(x, y, id);
		Name.mouseClicked(x, y, id);
		Surname.mouseClicked(x, y, id);
		super.mouseClicked(x, y, id);
	}


}
