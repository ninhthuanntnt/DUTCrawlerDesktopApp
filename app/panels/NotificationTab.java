package com.ntnt.dutcrawler.app.panels;

import com.ntnt.dutcrawler.Models.NotificationEntity;
import com.ntnt.dutcrawler.enums.NotiType;
import com.ntnt.dutcrawler.services.AppService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationTab extends JPanel {

    private JLabel notiLabel;
    private JPanel notiPanel;
    private JScrollPane scrollPane;
    private JPanel paginationBtns;
    private JPanel typePanel;
    private JRadioButton generalTypeBtn;
    private JRadioButton classTypeBtn;
    private JButton nextPageBtn;
    private JButton prevPageBtn;
    private JButton curPageBtn;

    private List<NotificationEntity> notifications;
    private int page;
    private NotiType notiType;

    private AppService appService;

    public NotificationTab() {
        this.page = 1;
        this.notiType = NotiType.GENERAL;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.appService = AppService.getInstance();

        this.initUI();
        this.loadNotification();
    }

    private void initUI() {
        notiLabel = new JLabel("Thông báo");
        notiPanel = new JPanel();
        typePanel = new JPanel();
        generalTypeBtn = new JRadioButton("Thông báo chung", true);
        classTypeBtn = new JRadioButton("Thông báo đến lớp học phần");
        scrollPane = new JScrollPane(notiPanel);
        paginationBtns = new JPanel();
        nextPageBtn = new JButton(">");
        prevPageBtn = new JButton("<");
        curPageBtn = new JButton(String.valueOf(page));

        notiLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        notiLabel.setMinimumSize(new Dimension(120, 30));
        notiLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        notiLabel.setPreferredSize(new Dimension(720, 30));
        notiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notiLabel.setHorizontalAlignment(SwingConstants.CENTER);

        typePanel.setMinimumSize(new Dimension(120, 30));
        typePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        typePanel.setPreferredSize(new Dimension(720, 30));

        notiPanel.setLayout(new BoxLayout(notiPanel, BoxLayout.Y_AXIS));
        notiPanel.setMinimumSize(new Dimension(720, 300));
        notiPanel.setMaximumSize(new Dimension(720, Integer.MIN_VALUE));
//        notiPanel.setPreferredSize(new Dimension(720, 480));

        scrollPane.getViewport().setMinimumSize(new Dimension(720, 300));
        scrollPane.getViewport().setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MIN_VALUE));
        scrollPane.getViewport().setPreferredSize(new Dimension(720, 480));

        paginationBtns.setMinimumSize(new Dimension(120, 30));
        paginationBtns.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        paginationBtns.setPreferredSize(new Dimension(720, 30));

        // Binding event
        prevPageBtn.addActionListener(e -> {
            if (page > 1) {
                page--;
                loadNotification();
            }
        });
        nextPageBtn.addActionListener((e) -> {
            page++;
            loadNotification();
        });

        generalTypeBtn.addActionListener((e -> {
            this.notiType = NotiType.GENERAL;
            this.page = 1;
            this.loadNotification();
        }));
        classTypeBtn.addActionListener((e) -> {
            this.notiType = NotiType.CLASS;
            this.page = 1;
            this.loadNotification();
        });

        ButtonGroup typeBtnGroup = new ButtonGroup();
        typeBtnGroup.add(generalTypeBtn);
        typeBtnGroup.add(classTypeBtn);

        typePanel.add(generalTypeBtn);
        typePanel.add(classTypeBtn);
        paginationBtns.add(prevPageBtn);
        paginationBtns.add(curPageBtn);
        paginationBtns.add(nextPageBtn);

        this.add(notiLabel);
        this.add(typePanel);
        this.add(scrollPane);
        this.add(paginationBtns);
    }

    private void loadNotification() {
        this.notifications = appService.getNotifications(notiType, page);
        fetchDataToUI();
    }

    private void fetchDataToUI() {
        this.notiPanel.removeAll();
        this.curPageBtn.setText(String.valueOf(page));

        notiPanel.setVisible(false);
        for (NotificationEntity noti : notifications) {
            JPanel panel = new JPanel();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(noti.getDate());
            JLabel title = new JLabel(String.format("<html><span style=\"color: blue\">(%s):</span>%s</html>", date, noti.getTitle()));
            JTextPane content = new JTextPane();

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(new EmptyBorder(10, 5, 10, 5));

            title.setMinimumSize(new Dimension(120, 30));
            title.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
            title.setPreferredSize(new Dimension(720, getPreferedHeightForFixedHeight(720, noti.getTitle())));
            title.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
            title.setBackground(Color.WHITE);
            title.setOpaque(true);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setHorizontalAlignment(SwingConstants.LEFT);

            content.setContentType("text/html");
            content.setEditable(false);
            content.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            String html =
                    "<body style=\"font-family: Arial, Helvetica, sans-serif; font-size: 1.2em;" +
                            "width:" + (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50) +
                            "\">"
                    + noti.getContent().replaceAll("font-size:([a-z]|[0-9]| |,)+(;|)|font-family:([a-z]|[0-9]| |,)+(;|)", "")
                    + "</body>";
//            content.setSize(new Dimension(720, getPreferedHeightForFixedHeight(720, html)));
            content.setText(html);

            content.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            panel.add(title);
            panel.add(content);
            notiPanel.add(panel);
        }
        notiPanel.setVisible(true);
    }

    private int getPreferedHeightForFixedHeight(int width, String content){
        JTextPane textPane = new JTextPane();
        textPane.setSize(width, Integer.MAX_VALUE);
        textPane.setText(content);

        return textPane.getPreferredSize().height;
    }
}
