package Gateway;

import Entity.Template;
import UseCase.TemplateManager;

import java.util.HashMap;

public class TemplateGateway extends Reader<HashMap<Integer, Template>> {

    private String filePath = "phase1/src/PlannerData/idToTemplate.ser";
    private HashMap<Integer, Template> idToTemplate = new HashMap<Integer, Template>();

    private final TemplateManager tm;

    public TemplateGateway(TemplateManager tm) {
        this.tm = tm;
    }

    /**
     * Load in the data from database, call this function when initialize an Template manager.
     * @return A boolean value representing whether the loading process is successful or not.
     */
    public boolean load() {
        return this.readMaps();
    }

    /**
     * Save the data to the database, call this function when a saving is needed. Must be called
     * when exit the application.
     * @return A boolean value representing whether the loading process is successful or not.
     */
    public boolean save() {
        return this.writeMaps();
    }

    //Private methods
    private boolean readMaps() {
        try {
            HashMap<Integer, Template> hm = super.readSer(this.filePath);
            if (hm == null) {return true;} else {
                this.idToTemplate = hm;
            }
            for (Template temp: this.idToTemplate.values()) {
                tm.addTemplate(temp);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean writeMaps() {
        for (Template temp: tm.getTemplates().values()) {
            this.idToTemplate.put(temp.getId(), temp);
        }
        return super.writeSer(this.filePath, this.idToTemplate);
    }
}
