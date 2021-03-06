package lasermod.client.render.block;

import lasermod.api.LaserCollisionBoxes;
import lasermod.api.LaserInGame;
import lasermod.api.LaserToRender;
import lasermod.helper.ClientHelper;
import lasermod.tileentity.TileEntitySmallColourConverter;
import lasermod.util.LaserUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author ProPercivalalb
 */
public class TileEntitySmallColourConverterRenderer extends TileEntitySpecialRenderer {

    public void renderColourConverter(TileEntitySmallColourConverter colourConverter, double x, double y, double z, float tick) {
    	if(colourConverter.getOutputLaser(ForgeDirection.getOrientation(colourConverter.getBlockMetadata())) == null)
    		return;
    	LaserInGame laserInGame = colourConverter.getOutputLaser(ForgeDirection.getOrientation(colourConverter.getBlockMetadata()));
    	float alpha = laserInGame.shouldRenderLaser(ClientHelper.getPlayer());

    	if(alpha == 0.0F)
    		return;
    	
		AxisAlignedBB boundingBox = LaserUtil.getLaserOutline(colourConverter, ForgeDirection.getOrientation(colourConverter.getBlockMetadata()), x, y, z);
		LaserCollisionBoxes.addLaserCollision(new LaserToRender(laserInGame, boundingBox, x, y, z, colourConverter.xCoord, colourConverter.yCoord, colourConverter.zCoord, ForgeDirection.getOrientation(colourConverter.getBlockMetadata()), alpha, true));

    }
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        renderColourConverter((TileEntitySmallColourConverter)tileEntity, x, y, z, tick);
    }
}
