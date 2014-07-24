package falconry.falcon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Clarky158 on 24/07/2014.
 * <p/>
 * Falconry is an open source Minecraft mod
 * released under version 3 of the GNU Lesser
 * General Public License. This means that
 * the source of this mod is publicly available
 * and you have certain rights with respective
 * to the code.
 */
public class EntityFalcon extends EntityCreature
{
    public EntityFalcon(World world) {
        super(world);
        this.tasks.addTask(1, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 3.0D, false));
    }

    public float wingsAngle = 0;
    public float goingTo = 0;
    public float[] wingsPos = new float[] {-45, 0, 30};

    @Override
    protected void updateEntityActionState() {
        //super.updateEntityActionState();


    }

    @Override
    public void onUpdate() {

        if(!worldObj.isRemote) { return; }
        //System.out.println(wingsAngle + " : " + goingTo);
        wingsAngle = alterTo(goingTo, wingsAngle);

        if(wingsAngle == goingTo)
        {
            if(goingTo == wingsPos[2]) { goingTo = wingsPos[1]; }
            else if(goingTo == wingsPos[1]) { goingTo = wingsPos[0]; }
            else if(goingTo == wingsPos[0]) { goingTo = wingsPos[2]; }
        }
    }


    public float alterTo(float to, float from)
    {
        float upper = to + 1;
        float lower = to - 1;

        if(from > upper || from < lower) {
            if (to < from) {
                return (float)(from - 3);
            } else if (to > from) {
                return (float)(from + 3);
            }
        }

        return (float)to;
    }
}
