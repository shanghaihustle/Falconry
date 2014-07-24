package falconry.falcon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import falconry.falcon.entity.EntityFalcon;
import falconry.falcon.item.ItemFalconEgg;
import falconry.falcon.proxy.ClientProxy;
import net.minecraft.item.Item;

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

@Mod(modid = "Falconry", name = "Falconry", version = "0.1.003")

public class Falconry
{

    public static Item falconEgg = new ItemFalconEgg();

    @EventHandler
    public void preIntit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerItem(falconEgg, "falconEgg");
        EntityRegistry.registerModEntity(EntityFalcon.class, "Falcon", 2, this, 80, 3, true);
        if(event.getSide() == Side.CLIENT) { ClientProxy.render(); }
    }
}
