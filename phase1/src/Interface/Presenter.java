package Interface;

import Controller.AccessController;
import Controller.TemplateController;
import UseCase.PlannerManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import java.util.Scanner;

public class Presenter {

    private TemplateController templateController;
    private PlannerManager plannerManager;
    private AccessController accessController;

    //================================================================================================================
    // Messages
    private String welcomeMessage = "Welcome to your planner manager!";
    private String closingMessage = "Thank you for using the program, have a productive day!";
    private String savingInfoMessage = "Saving information...";
    private String savingSuccessfulMessage = "Saving successful!";
    private String invalidInputMessage = "Invalid input, please try again.";
    private String returnToMainMessage = "Returning to main menu...";
    private String returnToPrevMessage = "Returning to previous menu...";
    private String confirmPasswordMessage = "Please enter the password again to confirm.\n";
    private String passwordUnmatchedMessage = "Password does not match. Please try again.\n";
    private String accountCreatedMessage = "A new account has been created.%nPlease remember your username: %s.%n";
    private String loginFailedMessage = "Invalid username or password. Please try again.\n";
    private String loginSuccessfulMessage = "Login successful.";
    private String checkAccountPermMessage = "Checking your account type and its permissions...";
    private String requiresAdminMessage = "Sorry, this feature requires an admin account.";
    private String returnToAccountSettingsMessage = "Returning to account setting options...";
    private String returnToAccountMenuMessage = "Returning to Account Menu...";

    private String idForEditQuestion = "Which %s would you like to edit? Please enter the ID.%n";
    private String objectIntroMessage = "You have selected %s with ID %d. " +
            "This is what the %s currently looks like: %n";
    private String editNewNameQuestion = "Please enter the desired new name of the %s.%n";
    private String ifContinueEditQuestion = "Would you like to make another edit? Enter \"yes\" or \"no\".\n";
    private String confirmDeleteQuestion = "Are you sure you want to delete this %s? Enter \"yes\" or \"no\".%n";
    private String featureUnavailableMessage = "Feature is not yet available. Please check back later!";
    private String templatePromptsIntroMessage = "Here are the current prompts: \n";
    private String idForEditPromptQuestion = "Please enter the ID of the prompt you'd like to %s.%n";
    private String updatingTemplateMessage = "Please wait while we are updating your template...";
    private String updateCompletedMessage = "Update is completed: ";
    private String returnToEditPromptsMenuMessage = "Returning to the edit prompts menu...";
    private String returnToTemplateEditMenuMessage = "Returning to template edit options...";
    private String returnToTemplateMenuMessage = "Returning to Template Menu...";

    private String showPlannerTypeMessage = "You have selected a planner of type: %s.%n";
    private String plannerCreatedMessage = "%s planner successfully created. This is what it looks like: %n";
    private String plannerEditTimeQuestion = "Please enter the time slot for which you'd like to edit the agenda. If " +
            "the time slot entered is not in planner, the agenda of the closest time will be edited.";
    private String plannerEditAgendaQuestion = "Please enter the new agenda.";
    private String plannerEditIndexQuestion = "Please enter the index for which you'd like to edit the agenda.";
    private String plannerReEnterIndexMessage = "The index entered is out of range (exceeds the number of existing " +
            "agendas). Please try again.";
    private String returnToPlannerEditMenuMessage = "Returning to planner edit options...";
    private String returnToPlannerMenuMessage = "Returning to Planner Menu...";

    // Menus
    private String loginMenu =
            "=========================================================================\n" +
            "Select one of the options below to get started! " +
            "Please enter the letter associated with that option.\n"+
            "A. Create a new account\n" +
            "B. Log in (have an existing account)\n" +
            "C. Continue as guest (note that any changes made won't be saved)\n" +
            "\nTo close the program, enter \"q\".\n" +
            "=========================================================================";
    private String mainMenu =
            "=========================================================================\n" +
            "This is the Main Menu. " +
            "Please choose one of the options below and enter the letter associated with it.\n" +
            "A. Planners options\n" +
            "B. Templates options\n" +
            "C. Account options\n" +
            "D. Log out and exit\n" +
            "=========================================================================";
    private String accountMenu =
            "=========================================================================\n"+
            "This is the Account Menu. " +
            "Please choose one of the options below and enter the letter associated with it.\n" +
            "A. Log out\n" +
            "B. Edit account info\n" +
            "\nTo return to the MAIN MENU, enter \"m\".\n" +
            "=========================================================================";
    private String templateMenu =
            "=========================================================================\n"+
            "This is the Template Menu. " +
            "Please choose one of the options below and enter the letter associated with it.\n" +
            "A. View all templates\n" +
            "B. Edit an existing template (Admin Only) \n" +
            "C. Create a new template (Admin Only) \n" +
            "\nTo return to the MAIN MENU, enter \"m\".\n" +
            "=========================================================================";
    private String plannerMenu =
            "=========================================================================\n"+
            "This is the Planner Menu. " +
            "Please choose one of the options below and enter the letter associated with it.\n" +
            "A. View planners \n" +
            "B. Edit an existing planner \n" +
            "C. Create a new planner \n" +
            "\nTo return to the MAIN MENU, enter \"m\".\n" +
            "=========================================================================";

    // Sub-menus
    // Account sub-menus
    String editAccountMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing your account. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Edit username \n" +
                    "B. Edit password \n" +
                    "C. Return to Account Menu \n" +
                    "=========================================================================\n";

    // Template sub-menus
    private String templateViewMenu =
                    "=========================================================================\n"+
                    "Options for viewing all templates. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Summary view \n" +
                    "B. Detailed view \n" +
                    "C. Exit to Template menu \n" +
                    "=========================================================================\n";
    private String editTemplateMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing a template. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Edit template name \n" +
                    "B. Edit template prompts \n" +
                    "C. Delete template \n" +
                    "D. Return to Template Menu \n" +
                    "=========================================================================\n";
    private String editTemplatePromptsMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing a prompt. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Rename prompt \n" +
                    "B. Add prompt \n" +
                    "C. Delete prompt \n" +
                    "D. Return to Edit Template Menu \n" +
                    "=========================================================================\n";

    // Planner sub-menus
    private String plannerViewMenu =
                    "=========================================================================\n"+
                    "Options for viewing planners. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. View all personal planners \n" +
                    "B. View all public planners created by others \n" +
                    "C. Exit to Planner Menu \n" +
                    "=========================================================================\n";
    private String editPlannerMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing a planner. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Edit your personal planners \n" +
                    "B. Edit other public planners \n" +
                    "C. Return to Planner Menu \n" +
                    "=========================================================================\n";
    private String editPersonalPlannerMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing a personal planner. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Edit planner agenda \n" +
                    "B. Change privacy setting \n" +
                    "C. Delete Planner \n" +
                    "D. Return to Edit Planner Menu \n" +
                    "=========================================================================\n";
    private String editPublicPlannerMenu =
                    "=========================================================================\n"+
                    "Here are the options for editing a public planner. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Edit planner agenda \n" +
                    "B. Return to Edit Planner Menu \n" +
                    "=========================================================================\n";
    private String plannerCreateMenu =
                    "=========================================================================\n"+
                    "Options for creating planners. " +
                    "Please choose one of the options below and enter the letter associated with it.\n" +
                    "A. Create a daily planner \n" +
                    "B. Create a project planner \n" +
                    "C. Exit to Planner Menu \n" +
                    "=========================================================================\n";
    //================================================================================================================

    public Presenter(TemplateController templateController, PlannerManager plannerManager,
                     AccessController accessController){
        this.templateController = templateController;
        this.plannerManager = plannerManager;
        this.accessController = accessController;
    }

    /**
     * Prints out interface screen with a message.
     * @param message is the message being printed on the interface screen.
     */
    public void interfaceScreen(String message){
        System.out.println(message);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out message for welcome screen at the start of the program.
     */
    public void showWelcomeScreen(){
        System.out.println(welcomeMessage);
    }

    /**
     * Prints out message for closing screen at the end of the program.
     */
    public void showClosingScreen(){
        System.out.println(closingMessage);
    }

    /**
     * Prints out log in options.
     */
    public void showLoginMenu(){
        System.out.println(loginMenu);
    }

    /**
     * Prints out Main Menu after user is logged in.
     */
    public void showMainMenu(){
        System.out.println(mainMenu);
    }

    /**
     * Prints out message showing that the program is saving information.
     */
    public void showSavingInfoScreen(){
        System.out.println(savingInfoMessage);
    }

    /**
     * Prints out message showing that information has been successfully saved in the program.
     */
    public void showSavingSuccessfulScreen(){
        System.out.println(savingSuccessfulMessage);
    }

    /**
     * Prints out message showing that the user input is invalid (does not match with any of the options provided).
     */
    public void showInvalidInputScreen(){
        System.out.println(invalidInputMessage);
    }

    /**
     * Prints out message showing that the system is returning back to the main menu.
     */
    public void showReturnToMainScreen(){
        interfaceScreen(returnToMainMessage);
    }

    /**
     * Prints out message showing that the system is returning back to the previous menu.
     */
    public void showReturnToPrevMenu(){
        interfaceScreen(returnToPrevMessage);
    }

    /**
     * Prints out message asking for user input when creating a new account.
     * @param index Index of the element in the String array that user wants to print out.
     *              0 - Intro message + Email, 1 - Username, 2 - Password.
     */
    public void showCreateNewAccountScreen(int index){
        String createNewAccountIntro = "You have chosen to create a new account.\n";
        String createNewAccountInfo = "Please enter %s.\n";
        String[] createNewAccountPrompts = {String.format(createNewAccountIntro + createNewAccountInfo, "Email"),
                                            String.format(createNewAccountInfo, "Username"),
                                            String.format(createNewAccountInfo, "Password")};
        for (String s: createNewAccountPrompts) {
            System.out.println(s);
            Scanner scanner = new Scanner(System.in);

        }
        System.out.println(createNewAccountPrompts[index]);
    }

    /**
     * Prints out message asking user to enter password again to confirm.
     */
    public void showConfirmPasswordScreen(){
        System.out.println(confirmPasswordMessage);
    }

    /**
     * Prints out message showing that password does not match and asking user to try again.
     */
    public void showPasswordUnmatchedScreen(){
        System.out.println(passwordUnmatchedMessage);
    }

    /**
     * Prints out message showing that a new account has been created with the specified username.
     * @param username Username of the new account.
     */
    public void showAccountCreatedScreen(String username){
        System.out.printf((accountCreatedMessage), username);
    }

    /**
     * Prints out message asking for user input when logging in with an existing account.
     * @param index Index of the element in the String array that user wants to print out.
     *              0 - Intro message + Username or Email, 1 - Password.
     */
    public void showLoginScreen(int index){
        String loginIntro = "You have chosen to log in with an existing account.\n";
        String loginInfo = "Please enter your %s.\n";
        String[] loginPrompts = {String.format(loginIntro + loginInfo, "Username or Email"),
                String.format(loginInfo, "Password")};
        System.out.println(loginPrompts[index]);
    }

    /**
     * Prints out message showing that invalid username or password is entered and asking user to try again.
     */
    public void showLoginFailedScreen(){
        System.out.println(loginFailedMessage);
    }

    /**
     * Prints out message showing that user has successfully logged in.
     */
    public void showLoginSuccessfulScreen(){
        System.out.println(loginSuccessfulMessage);
    }

    /**
     * Prints out message showing that user account type and permissions are being checked, with delay.
     */
    public void showCheckAccountPermMessage(){
        interfaceScreen(checkAccountPermMessage);
    }

    /**
     * Prints out message showing that feature requires an admin account.
     */
    public void showRequiresAdminMessage(){
        System.out.println(requiresAdminMessage);
    }

    /**
     * Prints out Template Menu.
     */
    public void showTemplateMenu(){
        System.out.println(templateMenu);
    }

    /**
     * Prints out Template view options.
     */
    public void showTemplateViewMenu(){
        System.out.println(templateViewMenu);
    }

    /**
     * Prints out detail view of all existing templates.
     */
    public void showDetailViewAllTemplates(){
        System.out.println(templateController.detailViewAllTemplates());
    }

    /**
     * Prints out preview of all existing templates.
     */
    public void showPreviewAllTemplates(){
        System.out.println(templateController.previewAllTemplates());
    }

    /**
     * Prints out detail view of template with templateID.
     * @param templateID ID of template to get detail view for.
     */
    public void showDetailViewTemplate(int templateID){
        System.out.println(templateController.detailViewTemplate(templateID));
    }

    /**
     * Prints out message asking user for the ID of the template or planner they would like to edit.
     * @param obj String representing the type of object. Can be "template" or "planner".
     */
    public void showIDForEditQuestion(String obj){
        System.out.printf(idForEditQuestion, obj);
    }

    /**
     * Prints out intro message for showing what an existing template or planner with ID currently looks like.
     * @param obj Type of object - can be "planner" or "template".
     * @param ID ID of the template or planner selected.
     */
    public void showObjIntroMessage(String obj, int ID){
        System.out.printf((objectIntroMessage), obj, ID, obj);
    }

    /**
     * Prints out options for editing template.
     */
    public void showEditTemplateMenu(){
        System.out.println(editTemplateMenu);
    }

    /**
     * Prints out message asking user to enter the desired new name.
     * @param obj Object of which user will give a new name. "obj" can be "template", "prompt", etc.
     */
    public void showEditNewNameQuestion(String obj){
        System.out.printf((editNewNameQuestion), obj);
    }

    /**
     * Prints out message asking user if they would like to continue to make another edit.
     */
    public void showIfContinueEditQuestion(){
        System.out.println(ifContinueEditQuestion);
    }

    /**
     * Prints out message asking user to confirm if they want to delete obj (obj can be "template", "account", etc.
     * @param obj Object that user wants to delete.
     */
    public void showConfirmDeleteQuestion(String obj){
        System.out.printf((confirmDeleteQuestion), obj);
    }

    /**
     * Prints out message showing that a feature/a functionality in the program is not yet available.
     */
    public void showFeatureUnavailableScreen(){
        System.out.println(featureUnavailableMessage);
    }

    /**
     * Prints out message showing the options for editing a template prompt.
     */
    public void showEditTemplatePromptsMenu(){
        System.out.println(editTemplatePromptsMenu);
    }

    /**
     * Prints out intro message for showing current template prompts.
     */
    public void showTemplatePromptsIntroScreen(){
        System.out.println(templatePromptsIntroMessage);
    }

    /**
     * Prints out message asking user to enter ID of the prompt they'd like to rename.
     * @param action Action user would like to perform - can be "rename", "add", or "delete"
     */
    public void showIDForEditPromptQuestion(String action){
        System.out.printf((idForEditPromptQuestion), action);
    }

    /**
     * Prints out interface screen with message showing that template is being updated.
     */
    public void showUpdatingTemplateMessage(){
        interfaceScreen(updatingTemplateMessage);
    }

    /**
     * Prints out message showing that update is completed. Universal message as it does not specify what update
     * has been completed.
     */
    public void showUpdateCompletedMessage(){
        System.out.println(updateCompletedMessage);
    }

    /**
     * Prints out message showing that the program is returning to the edit prompts menu with delay.
     */
    public void showReturnToEditPromptsMenuMessage(){
        interfaceScreen(returnToEditPromptsMenuMessage);
    }

    /**
     * Prints out message showing that the program is returning to the template edit menu with delay.
     */
    public void showReturnToTemplateEditMenuMessage(){
        interfaceScreen(returnToTemplateEditMenuMessage);
    }

    /**
     * Prints out message showing that the program is returning to the Template Menu with delay.
     */
    public void showReturnToTemplateMenuMessage(){
        interfaceScreen(returnToTemplateMenuMessage);
    }

    /**
     * Prints out account menu.
     */
    public void showAccountMenu(){
        System.out.println(accountMenu);
    }

    /**
     * Prints out options for editing account.
     */
    public void showEditAccountMenu(){
        System.out.println(editAccountMenu);
    }

    /**
     * Prints out message asking for user input when editing account info.
     * @param index Index of the element in the String array that user wants to print out.
     *              0 - new username, 1 - current password, 2 - new password.
     */
    public void showEditAccountPrompts(int index){
        String editAccountInfo = "Please enter your %s.\n";
        String[] editAccountPrompts = {String.format(editAccountInfo, "new username"),
                String.format(editAccountInfo, "current password"),
                String.format(editAccountInfo, "new password")};
        System.out.println(editAccountPrompts[index]);
    }

    /**
     * Prints out message showing that the program is returning to account setting options with delay.
     */
    public void showReturnToAccountSettingsMessage(){
        interfaceScreen(returnToAccountSettingsMessage);
    }

    /**
     * Prints out message showing that the program is returning to the Account Menu with delay.
     */
    public void showReturnToAccountMenuMessage(){
        interfaceScreen(returnToAccountMenuMessage);
    }

    /**
     * Prints out Planner Menu.
     */
    public void showPlannerMenu(){
        System.out.println(plannerMenu);
    }

    /**
     * Prints out options for editing planner.
     */
    public void showEditPlannerMenu(){
        System.out.println(editPlannerMenu);
    }

    /**
     * Prints out options for editing personal planner.
     */
    public void showEditPersonalPlannerMenu(){
        System.out.println(editPersonalPlannerMenu);
    }

    /**
     * Prints out options for editing public planner.
     */
    public void showEditPublicPlannerMenu(){
        System.out.println(editPublicPlannerMenu);
    }

    /**
     * Prints out details of all existing personal planners and planners of others that have been made public.
     */
    public void showAllPlanners(){
        System.out.println(plannerManager.showAllPlanners());
    }

    /**
     * Prints out details of all existing personal planners.
     */
    public void showAllPersonalPlanners(String retriever){
        ArrayList<String> plannerIDs =  this.accessController.getPlanners(retriever);
        StringBuilder personalPlanners = new StringBuilder();
        for (String plannerID: plannerIDs){
            personalPlanners.append(plannerManager.findPlanner(Integer.parseInt(plannerID)).toString());
            personalPlanners.append("\n");
        }
        System.out.println(personalPlanners);
    }

    /**
     * Prints out details of all existing public planners.
     */
    public void showAllPublicPlanners(){
        plannerManager.getPublicPlanners();
    }

    /**
     * Prints out detail view of planner with plannerID.
     * @param plannerID ID of planner to get detail view for.
     */
    public void showDetailViewPlanner(int plannerID){
        System.out.println(plannerManager.toString(plannerID));
    }

    /**
     * Prints out message showing the type of the planner with plannerID
     * @param type Type of the planner.
     */
    public void showPlannerType(String type){
        System.out.printf(showPlannerTypeMessage, type);
    }

    /**
     * Prints out Planner view options.
     */
    public void showPlannerViewMenu(){
        System.out.println(plannerViewMenu);
    }

    /**
     * Prints out Planner creation options.
     */
    public void showPlannerCreateMenu(){
        System.out.println(plannerCreateMenu);
    }

    /**
     * Prints out message showing that planner of type plannerType has been created successfully.
     * @param plannerType Type of the created planner.
     */
    public void showPlannerCreatedMessage(String plannerType){
        System.out.printf(plannerCreatedMessage, plannerType);
    }

    /**
     * Prints out message asking for the time slot in the planner that the user would like to edit the agenda for.
     */
    public void showPlannerEditTimeQuestion(){
        System.out.println(plannerEditTimeQuestion);
    }

    /**
     * Prints out message asking user to enter the new agenda for the planner.
     */
    public void showPlannerEditAgendaQuestion(){
        System.out.println(plannerEditAgendaQuestion);
    }

    /**
     * Prints out message asking for the index in the planner that the user would like to edit the agenda for.
     */
    public void showPlannerEditIndexQuestion(){
        System.out.println(plannerEditIndexQuestion);
    }

    /**
     * Prints out message showing that the index entered for planner is out of range, and asking user to try again.
     */
    public void showPlannerReEnterIndexMessage(){
        System.out.println(plannerReEnterIndexMessage);
    }

    /**
     * Prints out message showing that the program is returning to the planner edit menu with delay.
     */
    public void showReturnToPlannerEditMenuMessage(){
        interfaceScreen(returnToPlannerEditMenuMessage);
    }

    /**
     * Prints out message showing that the program is returning to the Planner Menu with delay.
     */
    public void showReturnToPlannerMenuMessage(){
        interfaceScreen(returnToPlannerMenuMessage);
    }
}