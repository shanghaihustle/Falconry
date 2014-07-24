package falconry.falcon.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import falconry.falcon.client.FalconRenderer;
import falconry.falcon.client.ModelFalcon;
import falconry.falcon.entity.EntityFalcon;

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
public class ClientProxy
{
    public static void render()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityFalcon.class, new FalconRenderer(new ModelFalcon(), 0F));
    }
}
