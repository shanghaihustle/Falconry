package falconry.falcon.item;

import falconry.falcon.entity.EntityFalcon;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
public class ItemFalconEgg extends Item
{
    public ItemFalconEgg()
    {
        super();
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("falconEgg");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
                             int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!(player.isSneaking())) {
            if (world.isRemote) {
            } else {
                Block i1 = world.getBlock(x, y, z);
                x += Facing.offsetsXForSide[par7];
                y += Facing.offsetsYForSide[par7];
                z += Facing.offsetsZForSide[par7];
                double d0 = 0.0D;

                if (par7 == 1 && i1 != null
                        && i1.getRenderType() == 11) {
                    d0 = 0.5D;
                }

                EntityFalcon falcon = spawnFalcon(world, x + 0.5D, y + d0, z + 0.5D);

                if (falcon != null) {
                    if (falcon instanceof EntityLiving && stack.hasDisplayName()) {
                        ((EntityLiving) falcon).setCustomNameTag(stack.getDisplayName());
                    }
                }

                return true;
            }
        }
        return false;
    }

    public EntityFalcon spawnFalcon(World world, double x, double y, double z)
    {
        EntityFalcon robot = new EntityFalcon(world);
        EntityLiving entity = robot;
        robot.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        entity.rotationYawHead = entity.rotationYaw;
        entity.renderYawOffset = entity.rotationYaw;
        //entity.initCreature();
        world.spawnEntityInWorld(robot);
        entity.playLivingSound();
        return robot;
    }
}
