package lasermod.tileentity;

import lasermod.api.base.TileEntityMultiSidedReciever;
import lasermod.network.PacketDispatcher;
import lasermod.network.packet.client.LaserDetectorMessage;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

/**
 * @author ProPercivalalb
 */
public class TileEntityLaserDetector extends TileEntityMultiSidedReciever {
	
	@Override
	public void sendUpdateDescription() {
		PacketDispatcher.sendToAllAround(new LaserDetectorMessage(this), this, 512);
	}

	@Override
	public void onLaserPass(World world) {
		world.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
	}

	@Override
	public void onLaserRemoved(World world) {
		world.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
	}
	
	@Override
	public Packet getDescriptionPacket() {
	    return PacketDispatcher.getPacket(new LaserDetectorMessage(this));
	}
}