package Controller;

import Interface.IController;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A Class that control AccessController, PlannerController and TemplateController.
 */
public class ActionController implements IController{
    private final PlannerController plannerController;
    private final AccessController accessController;
    private final TemplateController templateController;

    private String currRetriever;
    private String currPlannerId;
    private String currTemplateId;


    /**
     * Initialize the object with new managers and load the data at the same time.
     */
    public ActionController() {
        this.accessController = new AccessController();
        this.templateController = new TemplateController();
        this.plannerController = new PlannerController();

        accessController.load();
        templateController.load();
        plannerController.load();
    }


    /**
     * Login the user, return true if the login is success.
     * @param retriever A string representing the User ID or Email.
     * @param password A String representing the password.
     * @return A boolean value that representing whether the login is success or not.
     */
    @Override
    public boolean logIn(String retriever, String password) {
        if (this.accessController.logIn(retriever, password)) {
            this.currRetriever = retriever;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String accountRole(String retriever) {
        return accessController.isAdmin(retriever);
    }

    @Override
    public String accountRole() {
        return this.accessController.isAdmin(this.currRetriever);
    }

    @Override
    public String getCurrentId() {
        return this.currRetriever;
    }

    @Override
    public String viewUserPlanners() {
        StringBuilder res = new StringBuilder();
        ArrayList<String> plannerIds = this.accessController.getPlanners(this.currRetriever);
        System.out.println(plannerIds.toString());
        if (plannerIds.size() == 0) {
            return "No personal planners available yet.";
        } else {
            for (String plannerId : plannerIds) {
                res.append(this.plannerController.toString(Integer.parseInt(plannerId)));
                res.append("==================================");
            }
            return res.toString();
        }
    }

    @Override
    public String viewPublicPlanners() {
        StringBuilder res = new StringBuilder();
        ArrayList<Integer> publicPlanners = plannerController.getPublicPlanners();
        for (int plannerId: publicPlanners) {
            res.append(plannerController.toString(plannerId));
            res.append("==================================");
        }
        return res.toString();
    }

    @Override
    public String createAccount(String email, String userName, String password) {
        String  retriever =  accessController.createAccount(email, userName, password);
        this.saveProgram();
        return retriever;
    }

    @Override
    public String createDailyPlanner() {
        String id =  ((Integer)plannerController.createNewDailyPlanner()).toString();
        this.plannerController.setPlannerAuthor(Integer.parseInt(id), this.currRetriever);
        this.accessController.setPlanner(this.currRetriever, id);
        this.saveProgram();
        return id;
    }

    @Override
    public String createProjectPlanner() {
        String id =  ((Integer)plannerController.createNewProjectPlanner()).toString();
        this.plannerController.setPlannerAuthor(Integer.parseInt(id), this.currRetriever);
        this.accessController.setPlanner(this.currRetriever, id);
        this.saveProgram();
        return id;
    }

    @Override
    public String viewTemplates() {
        return templateController.detailViewAllTemplates();
    }

    @Override
    public boolean checkTemplate(String id) {
        for (String tempId: templateController.getAllTemplateIds()) {
            if (Objects.equals(id, tempId)) {
                this.currTemplateId = id;
                return true;
            }
        }
        return false;
    }

    @Override
    public String viewPlanner(String id) {
        return plannerController.toString(Integer.parseInt(id));
    }

    @Override
    public String viewPlanner() {
        return plannerController.toString(Integer.parseInt(this.currPlannerId));
    }

    @Override
    public boolean getPlannerStatus() {
        return this.plannerController.getPrivacyStatus(Integer.parseInt(this.currPlannerId)).equals("private");
    }

    @Override
    public boolean getPlannerStatus(String id) {
        return this.plannerController.getPrivacyStatus(Integer.parseInt(id)).equals("private");
    }

    @Override
    public void setPlannerStatus() {
        if (getPlannerStatus()) {
            this.plannerController.changePrivacyStatus(Integer.parseInt(this.currPlannerId), "public");
        } else {
            this.plannerController.changePrivacyStatus(Integer.parseInt(this.currPlannerId), "private");
        }
    }

    @Override
    public boolean deletePlanner(String id) {
        boolean flag = plannerController.deletePlanner(Integer.parseInt(id));
        if (flag) {
            this.accessController.removePlanner(currRetriever, id);
            this.accessController.save();
            this.saveProgram();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkPlanner(String id) {
        ArrayList<String> plannerIds = this.accessController.getPlanners(this.currRetriever);
        ArrayList<Integer> publicIds = this.plannerController.getPublicPlanners();
        if (plannerIds.contains(id) || publicIds.contains(Integer.parseInt(id))) {
            this.currPlannerId = id;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean editDailyPlanner(String timeSlot, String agenda) {
        boolean res = this.plannerController.edit(Integer.parseInt(this.currPlannerId), timeSlot, agenda);
        this.saveProgram();
        return res;
    }

    @Override
    public String getPlannerType() {
        return this.plannerController.getType(Integer.parseInt(this.currPlannerId));
    }

    @Override
    public void saveProgram() {
        this.accessController.save();
        this.plannerController.save();
        this.templateController.save();
    }

    @Override
    public void deleteAccount() {
        for (String plannerId: this.accessController.getPlanners(currRetriever)) {
            if (this.plannerController.getPrivacyStatus(Integer.parseInt(plannerId)).equals("private")) {
                this.plannerController.deletePlanner(Integer.parseInt(plannerId));
            }
        }
        this.accessController.removeAccount(this.currRetriever);
    }

    @Override
    public boolean changePassword(String original, String newPassword) {
        return this.accessController.changePassword(currRetriever, original, newPassword);
    }

    @Override
    public String getAccountInfo() {
        return this.accessController.getInfo(currRetriever);
    }

    @Override
    public void changeUserName(String newName) {
        this.accessController.changeUserName(currRetriever, newName);
    }
}
