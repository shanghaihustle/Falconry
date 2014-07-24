package falconry.falcon.client;

import falconry.falcon.entity.EntityFalcon;
import falconry.falcon.proxy.CommonProxy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

/**
 * Created by Clarky158 on 24/07/2014.
 * <p/>
 * Electrolysm is an open source Minecraft mod
 * released under version 3 of the GNU Lesser
 * General Public License. This means that
 * the source of this mod is publicly available
 * and you have certain rights with respective
 * to the code.
 */
public class FalconRenderer extends RenderLiving
{
    ModelFalcon model = new ModelFalcon();

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public FalconRenderer(ModelBase modelBase, float f) {
        super(modelBase, f);
        map.put("stood", 0);
        map.put("fly", 1);
    }

    @Override
    public void doRender(EntityLiving entity, double d1, double d2, double d3, float f1, float f2) {

        EntityFalcon falcon = (EntityFalcon)entity;

        String mode = "fly";

        //Whole Render
        GL11.glPushMatrix();
        this.bindTexture(CommonProxy.FALCON_MODEL);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float) d1 + 0.5F, (float) d2 + 1.5F, (float) d3 + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        //this.renderLeftWing(mode);
        //this.renderLegs(mode);
        //this.renderTail(mode);
        this.renderBody(mode);
        this.renderWingsAsPair(mode, falcon);

        GL11.glPushMatrix();
        GL11.glTranslatef(0, -0.4F, 0.5F - (1F / 16F));
        model.renderTail();
        GL11.glPopMatrix();
        //this.renderRightWing(mode);

        GL11.glPopMatrix();
    }

    public void renderWingsAsPair(String mode, EntityFalcon falcon)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.1F, 0.1F, 0);
        GL11.glRotatef(falcon.wingsAngle, 0, 0, 1F);
        model.renderLeftWing();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.1F, 0.1F, 0);
        GL11.glRotatef(falcon.wingsAngle * (-1), 0, 0, 1F);
        model.renderRightWing();
        GL11.glPopMatrix();
    }


    public void renderBody(String mode)
    {
        float[] angles = new float[] {0, 45};

        GL11.glPushMatrix();
        //glRotatef is in degrees
        GL11.glRotatef(angles[map.get(mode)], 1F, 0F, 0F);

        this.renderNeck(mode);
        this.renderHead(mode);
        model.renderBody();
        GL11.glPopMatrix();

    }

    public void renderHead(String mode)
    {
        GL11.glPushMatrix();
        model.renderHead();
        GL11.glPopMatrix();
    }

    public void renderNeck(String mode)
    {
        GL11.glPushMatrix();
        model.renderNeck();
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return CommonProxy.FALCON_MODEL;
    }
}
