package com.ntnt.dutcrawler.app;

import com.ntnt.dutcrawler.Models.JwtResponse;
import com.ntnt.dutcrawler.app.panels.LoginTab;
import com.ntnt.dutcrawler.app.panels.NotificationTab;
import com.ntnt.dutcrawler.app.panels.ScheduleTab;
import com.ntnt.dutcrawler.app.panels.ScoreTab;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DutCrawlerApp extends JFrame {

    public static JwtResponse jwtResponse;
    private JTabbedPane tabbedPane;
    private JPanel panelLogin, panelScore, panelSchedule;
    private JLabel statusLabel;
    private final String DEFAULT_MESSAGE = "Chào mừng bạn đến với ứng dụng DUTCrawler";
    private NotificationTab notiTab;
    private LoginTab loginTab;
    private ScheduleTab scheduleTab;
    private ScoreTab scoreTab;
    private Container container;

    public DutCrawlerApp(){
        this.setTitle("DUTCrawler");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(720,500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.container = this.getContentPane();
        this.setLayout(new BorderLayout());

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        initUI();

        this.setVisible(true);
    }

    private void initUI(){
        this.tabbedPane = new JTabbedPane();
        this.statusLabel = new JLabel(DEFAULT_MESSAGE);
        this.notiTab = new NotificationTab();
        this.loginTab = new LoginTab(this);

        this.tabbedPane.addTab("Thông báo", null, notiTab, "Noti");
        this.tabbedPane.addTab("Đăng nhập", null, loginTab, "Login");
        this.statusLabel.setBorder(new EmptyBorder(10,10,10,10));
        // Binding event
        this.tabbedPane.addChangeListener(e->{
            Class componentClass = tabbedPane.getSelectedComponent().getClass();
            if(componentClass.getName().equalsIgnoreCase(ScheduleTab.class.getName())){
                scheduleTab.panelDidMount();
            }else if(componentClass.getName().equalsIgnoreCase(ScoreTab.class.getName())){
                scoreTab.panelDidMount();
            }
        });

        this.container.add(tabbedPane, BorderLayout.CENTER);
        this.container.add(statusLabel, BorderLayout.SOUTH);
    }

    public void showMessage(String message){
        this.statusLabel.setText(message);
    }

    public void hideMessage(){
        this.statusLabel.setText(DEFAULT_MESSAGE);
    }

    public void authenticate(JwtResponse jwtResponse){
        this.jwtResponse = jwtResponse;

        if(jwtResponse != null){
            tabbedPane.remove(this.loginTab);
            scheduleTab = new ScheduleTab(jwtResponse);
            scoreTab = new ScoreTab(jwtResponse);
            tabbedPane.addTab("Lịch học", null, scheduleTab, "Schedule");
            tabbedPane.addTab("Điểm", null, scoreTab, "Score");
        }

    }
}
