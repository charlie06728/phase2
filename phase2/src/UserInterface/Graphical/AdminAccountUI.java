package UserInterface.Graphical;

import Gateway.UIGateway;
import UserInterface.GeneralPresenter;
import strategy.IForm;
import strategy.formGenerator.FormBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * GUI class for displaying the main menu for an admin user.
 * The main menu includes: planner options, edit template, suspend account
 * - Edit Template
 * - Planner options
 *  - change privacy setting
 *  - interact with all planners
 *  - delete planner
 * - Suspend account
 */
public class AdminAccountUI extends GeneralPresenter {
    private boolean flag = false;

    private Map<String, String> labelToStrings = new UIGateway().loadAdminAccountUITexts();

    // GeneralPresenters
    private final GeneralPresenter plannerOptionUI = new AdminCheckPlannerUI(this);
    private final GeneralPresenter templateOptionUI = new AdminTemplateOptionUI(this);
    private final GeneralPresenter accountOptionUI = new AdminAccountOptionUI(this);

    // Components
    private final JPanel panel = new JPanel();
    private JLabel prompt = new JLabel();

    private IForm form;

    public AdminAccountUI(GeneralPresenter parent) {
        this.setParent(parent);
    }

    /**
     * run the presenter from the beginning.
     */
    @Override
    public void run() {
        if (flag) {
            cl.show(main, "adminUserMainMenu");
        } else {
            this.showMenu();
            cl.show(main, "adminUserMainMenu");
            flag = !flag;
        }
    }

    private void showMenu() {
        panel.setLayout(null);
        main.add(panel, "adminUserMainMenu");

        prompt.setText(labelToStrings.get("prompt"));
        prompt.setHorizontalAlignment(JLabel.CENTER);
        prompt.setFont(new Font("MV Boli", Font.PLAIN, 20));
        prompt.setBounds(0, 100, 700, 50);
        panel.add(prompt);

        FormBuilder buttons = new FormBuilder();
        buttons.addSuperButton("plannerButton", labelToStrings.get("plannerButton"), plannerOptionUI);
        buttons.addSuperButton("templateButton", labelToStrings.get("templateButton"), templateOptionUI);
        buttons.addSuperButton("accountButton", labelToStrings.get("accountButton"), accountOptionUI);
        buttons.addSuperButton("logOutButton", labelToStrings.get("logOutButton"), this.getParent());
        buttons.setBounds(150, 150, 400, 200);

        form = buttons.getForm();
        panel.add(form.getPanel());

    }
}


