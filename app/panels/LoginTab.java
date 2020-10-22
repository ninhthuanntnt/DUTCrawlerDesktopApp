package com.ntnt.dutcrawler.app.panels;

import com.ntnt.dutcrawler.Models.JwtResponse;
import com.ntnt.dutcrawler.app.DutCrawlerApp;
import com.ntnt.dutcrawler.services.AppService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginTab extends JPanel {
    private JLabel loginLabel;
    private JPanel formPanel;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton submitBtn;

    private final DutCrawlerApp DUTAPP;

    private AppService appService;

    public LoginTab(DutCrawlerApp DUTAPP) {
        this.DUTAPP = DUTAPP;
        this.appService = AppService.getInstance();

        this.setLayout(new GridBagLayout());
        initUI();
    }

    private void initUI() {
        this.loginLabel = new JLabel("ĐĂNG NHẬP");
        this.formPanel = new JPanel();
        this.usernameLabel = new JLabel("Mã số sinh viên:");
        this.passwordLabel = new JLabel("Mật khẩu:");
        this.usernameTextField = new JTextField();
        this.passwordField = new JPasswordField();
        this.submitBtn = new JButton("Đăng Nhập");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        loginLabel.setMinimumSize(new Dimension(120, 30));
        loginLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        loginLabel.setAlignmentX(CENTER_ALIGNMENT);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBorder(new EmptyBorder(20, 0, 50, 0));
        loginLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

        usernameLabel.setPreferredSize(new Dimension(150, 30));
        passwordLabel.setPreferredSize(new Dimension(150, 30));

        submitBtn.setMinimumSize(new Dimension(0, 0));
        submitBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.setAlignmentX(CENTER_ALIGNMENT);
        submitBtn.setHorizontalAlignment(SwingConstants.CENTER);

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2, true),
                new EmptyBorder(10,10,10,10)));

        usernameTextField.setColumns(30);
        usernameTextField.setMargin(new Insets(10,5,5,10));
        passwordField.setColumns(30);
        passwordField.setMargin(new Insets(10,5,5,10));

        setContentFont(new Font(Font.DIALOG, Font.BOLD, 16),
                        usernameLabel, passwordLabel,
                        usernameTextField, passwordField,
                        submitBtn);

        bindingEvent();

        panel1.add(usernameLabel);
        panel1.add(usernameTextField);
        panel2.add(passwordLabel);
        panel2.add(passwordField);

        formPanel.add(loginLabel);
        formPanel.add(panel1);
        formPanel.add(panel2);
        formPanel.add(submitBtn);

        this.add(formPanel);
    }

    private void bindingEvent(){
        this.submitBtn.addActionListener((e)->{
            String username = this.usernameTextField.getText();
            String password = String.valueOf(this.passwordField.getPassword());

            DUTAPP.showMessage("Đang đăng nhập...");
            JwtResponse jwtResponse = appService.login(username, password);
            DUTAPP.authenticate(jwtResponse);
            DUTAPP.hideMessage();
        });
    }

    private void setContentFont(Font font, JComponent ...components){
        for(JComponent component : components){
            component.setFont(font);
        }
    }
}
