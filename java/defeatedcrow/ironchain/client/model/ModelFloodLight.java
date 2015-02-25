package defeatedcrow.ironchain.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFloodLight extends ModelBase
{
	  //fields
	    ModelRenderer base1;
	    ModelRenderer base2;
	    ModelRenderer base3;
	    ModelRenderer back;
	    ModelRenderer left;
	    ModelRenderer right;
	    ModelRenderer bottom;
	    ModelRenderer up;
	    ModelRenderer light;
	    ModelRenderer innerlight;
	    ModelRenderer cage;
	  
	  public ModelFloodLight()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      base1 = new ModelRenderer(this, 0, 0);
	      base1.addBox(-5F, 0F, -5F, 10, 1, 6);
	      base1.setRotationPoint(0F, 23F, 0F);
	      base1.setTextureSize(64, 32);
	      base1.mirror = true;
	      setRotation(base1, 0F, 0F, 0F);
	      base2 = new ModelRenderer(this, 0, 0);
	      base2.addBox(-6F, 0F, -3F, 1, 10, 2);
	      base2.setRotationPoint(0F, 14F, 0F);
	      base2.setTextureSize(64, 32);
	      base2.mirror = true;
	      setRotation(base2, 0F, 0F, 0F);
	      base3 = new ModelRenderer(this, 0, 0);
	      base3.addBox(5F, 0F, -3F, 1, 10, 2);
	      base3.setRotationPoint(0F, 14F, 0F);
	      base3.setTextureSize(64, 32);
	      base3.mirror = true;
	      setRotation(base3, 0F, 0F, 0F);
	      back = new ModelRenderer(this, 0, 0);
	      back.addBox(-5F, 0F, 0F, 10, 8, 1);
	      back.setRotationPoint(0F, 9F, 0F);
	      back.setTextureSize(64, 32);
	      back.mirror = true;
	      setRotation(back, 0.1745329F, 0F, 0F);
	      left = new ModelRenderer(this, 0, 0);
	      left.addBox(4F, 0F, -6F, 1, 8, 6);
	      left.setRotationPoint(0F, 9F, 0F);
	      left.setTextureSize(64, 32);
	      left.mirror = true;
	      setRotation(left, 0.1745329F, 0F, 0F);
	      right = new ModelRenderer(this, 0, 0);
	      right.addBox(-5F, 0F, -6F, 1, 8, 6);
	      right.setRotationPoint(0F, 9F, 0F);
	      right.setTextureSize(64, 32);
	      right.mirror = true;
	      setRotation(right, 0.1745329F, 0F, 0F);
	      bottom = new ModelRenderer(this, 0, 0);
	      bottom.addBox(-4F, 0F, -3.8F, 8, 1, 5);
	      bottom.setRotationPoint(0F, 16F, 0F);
	      bottom.setTextureSize(64, 32);
	      bottom.mirror = true;
	      setRotation(bottom, 0.1745329F, 0F, 0F);
	      up = new ModelRenderer(this, 0, 0);
	      up.addBox(-4F, 0F, -7F, 8, 1, 7);
	      up.setRotationPoint(0F, 9F, 0F);
	      up.setTextureSize(64, 32);
	      up.mirror = true;
	      setRotation(up, 0.1745329F, 0F, 0F);
	      light = new ModelRenderer(this, 0, 16);
	      light.addBox(-4F, 0F, -6F, 8, 6, 6);
	      light.setRotationPoint(0F, 10F, 0F);
	      light.setTextureSize(64, 32);
	      light.mirror = true;
	      setRotation(light, 0.1745329F, 0F, 0F);
	      innerlight = new ModelRenderer(this, 30, 16);
	      innerlight.addBox(-3F, 0F, -5F, 6, 4, 4);
	      innerlight.setRotationPoint(0F, 11F, 0F);
	      innerlight.setTextureSize(64, 32);
	      innerlight.mirror = true;
	      setRotation(innerlight, 0.1745329F, 0F, 0F);
	      cage = new ModelRenderer(this, 34, 0);
	      cage.addBox(-4F, 0F, -7F, 8, 6, 1);
	      cage.setRotationPoint(0F, 10F, 0F);
	      cage.setTextureSize(64, 32);
	      cage.mirror = true;
	      setRotation(cage, 0.1745329F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    base1.render(f5);
	    base2.render(f5);
	    base3.render(f5);
	    back.render(f5);
	    left.render(f5);
	    right.render(f5);
	    bottom.render(f5);
	    up.render(f5);
	    cage.render(f5);
	  }
	  
	  public void renderLight(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    light.render(f5);
	  }
	  
	  public void renderInner(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    innerlight.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.base1.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.base2.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.base3.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.bottom.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.up.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.left.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.right.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.back.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.innerlight.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.light.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.cage.rotateAngleY = f3 / (180F / (float)Math.PI);
  }
}
