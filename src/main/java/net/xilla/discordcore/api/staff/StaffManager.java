package net.xilla.discordcore.api.staff;

import net.xilla.discordcore.DiscordCore;
import net.xilla.discordcore.api.config.Config;
import net.xilla.discordcore.api.config.ConfigManager;
import net.xilla.discordcore.api.manager.ManagerObject;
import net.xilla.discordcore.api.manager.ManagerParent;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class StaffManager extends ManagerParent {

    public void reload() {
        super.reload();

        Config config = DiscordCore.getInstance().getConfigManager().getConfig("staff.json");
        JSONObject json = config.toJson();
        for(Object key : json.keySet()) {
            Staff staff = new Staff(config.getMap((String)key));
            addStaff(staff);
        }
    }

    public void save() {
        Config config = DiscordCore.getInstance().getConfigManager().getConfig("staff.json");
        for(ManagerObject object : getVector()) {
            Staff staff = (Staff)object;
            config.toJson().put(staff.getKey(), staff.toJson());
        }
        config.save();
    }

    public ArrayList<Staff> getStaffByUserId(String id) {
        ArrayList<Staff> staffList = new ArrayList<>();
        for(Object object : getVector()) {
            Staff staff = (Staff)object;
            if(staff.isMember(id)) {
                staffList.add(staff);
            }
        }
        return staffList;
    }


    public boolean isAuthorized(String id, int level) {
        if(level == 0)
            return true;

        ArrayList<Staff> staffList = getStaffByUserId(id);
        for(Staff staff : staffList)
            if(staff.getLevel() >= level)
                return true;
        return false;
    }

    public Staff getStaff(String name) {
        return (Staff)getObjectWithKey(name);
    }

    public void addStaff(Staff staff) {
        super.addObject(staff);
    }
}
