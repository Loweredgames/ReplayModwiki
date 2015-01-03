package eu.crushedpixel.replaymod.settings;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraftforge.common.config.Property;
import eu.crushedpixel.replaymod.ReplayMod;

public class ReplaySettings {
	
	private int maximumFileSize = 0;
	private boolean enableRecordingServer = true;
	private boolean enableRecordingSingleplayer = true;
	private boolean showNotifications = true;
	private boolean forceLinearPath = false;
	
	public ReplaySettings(int maximumFileSize, boolean enableRecordingServer,
			boolean enableRecordingSingleplayer, boolean showNotifications, boolean forceLinearPath) {
		this.maximumFileSize = maximumFileSize;
		this.enableRecordingServer = enableRecordingServer;
		this.enableRecordingSingleplayer = enableRecordingSingleplayer;
		this.showNotifications = showNotifications;
		this.forceLinearPath = forceLinearPath;
	}
	
	public int getMaximumFileSize() {
		return maximumFileSize;
	}
	public void setMaximumFileSize(int maximumFileSize) {
		this.maximumFileSize = maximumFileSize;
		rewriteSettings();
	}
	public boolean isEnableRecordingServer() {
		return enableRecordingServer;
	}
	public void setEnableRecordingServer(boolean enableRecordingServer) {
		this.enableRecordingServer = enableRecordingServer;
		rewriteSettings();
	}
	public boolean isEnableRecordingSingleplayer() {
		return enableRecordingSingleplayer;
	}
	public void setEnableRecordingSingleplayer(boolean enableRecordingSingleplayer) {
		this.enableRecordingSingleplayer = enableRecordingSingleplayer;
		rewriteSettings();
	}
	public boolean isShowNotifications() {
		return showNotifications;
	}
	public void setShowNotifications(boolean showNotifications) {
		this.showNotifications = showNotifications;
		rewriteSettings();
	}
	public boolean isLinearMovement() {
		return forceLinearPath;
	}
	public void setLinearMovement(boolean linear) {
		this.forceLinearPath = linear;
		rewriteSettings();
	}
	
	public Map<String, Object> getOptions() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("Enable Notifications", showNotifications);
		map.put("Maximum File Size", maximumFileSize);
		map.put("Record Server", enableRecordingServer);
		map.put("Record Singleplayer", enableRecordingSingleplayer);
		map.put("Force Linear Movement", forceLinearPath);
		
		return map;
	}
	
	public void setOptions(Map<String, Object> map) {
		try {
			
			maximumFileSize = (Integer)map.get("Maximum File Size");
			showNotifications = (Boolean)map.get("Enable Notifications");
			enableRecordingServer = (Boolean)map.get("Record Server");
			enableRecordingSingleplayer = (Boolean)map.get("Record Singleplayer");
			forceLinearPath = (Boolean)map.get("Force Linear Movement");
			
			rewriteSettings();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rewriteSettings() {
		ReplayMod.instance.config.load();
		
		ReplayMod.instance.config.removeCategory(ReplayMod.instance.config.getCategory("settings"));
		Property recServer = ReplayMod.instance.config.get("settings", "enableRecordingServer", enableRecordingServer, "Defines whether a recording should be started upon joining a server.");
		Property recSP = ReplayMod.instance.config.get("settings", "enableRecordingSingleplayer", enableRecordingSingleplayer, "Defines whether a recording should be started upon joining a singleplayer world.");
		Property maxFileSize = ReplayMod.instance.config.get("settings", "maximumFileSize", maximumFileSize, "The maximum File size (in MB) of a recording. 0 means unlimited.");
		Property showNot = ReplayMod.instance.config.get("settings", "showNotifications", showNotifications, "Defines whether notifications should be sent to the player.");
		Property linear = ReplayMod.instance.config.get("settings", "forceLinearPath", forceLinearPath, "Defines whether travelling paths should be linear instead of interpolated.");
		
		ReplayMod.instance.config.save();
	}
}
