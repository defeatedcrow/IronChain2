package defeatedcrow.ironchain.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBipedThin extends ModelBiped
{
    public ModelRenderer head;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer body;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;
    /** Records whether the model should be rendered holding an item in the left hand, and if that item is a block. */
    public int heldItemLeft;
    /** Records whether the model should be rendered holding an item in the right hand, and if that item is a block. */
    public int heldItemRight;
    public boolean isSneak;
    /** Records whether the model should be rendered aiming a bow. */
    public boolean aimedBow;
    private static final String __OBFID = "CL_00000840";

    public ModelBipedThin(int b)
    {
        this(0.6F, b);
    }

    public ModelBipedThin(float p_i1148_1_, int b)
    {
        this(p_i1148_1_, 0.0F, 64, 32, b);
    }

    public ModelBipedThin(float f1, float f2, int i3, int i4, int slot)
    {
        this.textureWidth = i3;
        this.textureHeight = i4;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8, f1);
        head.setRotationPoint(0F, 0F + f2, 0F);
        head.showModel = slot == 0;
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-3.5F, 0F, -1.5F, 7, 12, 3, f1);
        body.setRotationPoint(0F, 0F + f2, 0F);
        body.showModel = slot == 1;
        rightArm = new ModelRenderer(this, 36, 16);
        rightArm.addBox(-2.5F, -2F, -1.5F, 3, 12, 3, f1);
        rightArm.setRotationPoint(-3F, 2F + f2, 0F);
        rightArm.showModel = slot == 1;
        leftArm = new ModelRenderer(this, 48, 16);
        leftArm.addBox(-0.5F, -2F, -1.5F, 3, 12, 3, f1);
        leftArm.setRotationPoint(4F, 2F + f2, 0F);
        leftArm.showModel = slot == 1;
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
        rightLeg.setRotationPoint(-2F, 12F + f2, 0F);
        rightLeg.showModel = slot == 3 || slot == 2;
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
        leftLeg.setRotationPoint(2F, 12F + f2, 0F);
        leftLeg.showModel = slot == 3 || slot == 2;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity f1, float f2, float f3, float f4, float f5, float f6, float f7)
    {
        this.setRotationAngles(f2, f3, f4, f5, f6, f7, f1);

        if (this.isChild)
        {
            float f = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f, 1.5F / f, 1.5F / f);
            GL11.glTranslatef(0.0F, 16.0F * f7, 0.0F);
            this.head.render(f7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f, 1.0F / f, 1.0F / f);
            GL11.glTranslatef(0.0F, 24.0F * f7, 0.0F);
            this.body.render(f7);
            this.rightArm.render(f7);
            this.leftArm.render(f7);
            this.rightLeg.render(f7);
            this.leftLeg.render(f7);
            GL11.glPopMatrix();
        }
        else
        {
            this.head.render(f7);
            this.body.render(f7);
            this.rightArm.render(f7);
            this.leftArm.render(f7);
            this.rightLeg.render(f7);
            this.leftLeg.render(f7);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
    	super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    	
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.rightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.rightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.leftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.rightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.leftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.rightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.leftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.rightArm.rotateAngleY = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.rightArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleX += this.body.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            this.rightArm.rotateAngleX = (float)((double)this.rightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
            this.rightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak)
        {
            this.body.rotateAngleX = 0.5F;
            this.rightArm.rotateAngleX += 0.4F;
            this.leftArm.rotateAngleX += 0.4F;
            this.rightLeg.rotationPointZ = 4.0F;
            this.leftLeg.rotationPointZ = 4.0F;
            this.rightLeg.rotationPointY = 9.0F;
            this.leftLeg.rotationPointY = 9.0F;
            this.head.rotationPointY = 1.0F;
        }
        else
        {
            this.body.rotateAngleX = 0.0F;
            this.rightLeg.rotationPointZ = 0.1F;
            this.leftLeg.rotationPointZ = 0.1F;
            this.rightLeg.rotationPointY = 12.0F;
            this.leftLeg.rotationPointY = 12.0F;
            this.head.rotationPointY = 0.0F;
        }

        this.rightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.rightArm.rotateAngleZ = 0.0F;
            this.leftArm.rotateAngleZ = 0.0F;
            this.rightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.head.rotateAngleY;
            this.leftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.head.rotateAngleY + 0.4F;
            this.rightArm.rotateAngleX = -((float)Math.PI / 2F) + this.head.rotateAngleX;
            this.leftArm.rotateAngleX = -((float)Math.PI / 2F) + this.head.rotateAngleX;
            this.rightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.leftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.rightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.leftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.rightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
            this.leftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        }
    }
}
