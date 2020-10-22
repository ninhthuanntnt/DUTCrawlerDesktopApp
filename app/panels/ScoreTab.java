package com.ntnt.dutcrawler.app.panels;

import com.ntnt.dutcrawler.Models.*;
import com.ntnt.dutcrawler.app.utils.SemesterUtils;
import com.ntnt.dutcrawler.services.AppService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class ScoreTab extends JPanel {

    private JLabel scoreLabel;
    private JTable scoreTable;
    private JTable totalScoreTable;
    private String scoreColumns[];
    private String totalScoreColumns[];

    private JwtResponse jwtResponse;
    private AppService appService;

    public ScoreTab(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
        this.appService = AppService.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initUI();
    }

    public void panelDidMount() {
        this.loadScore();
    }

    private void initUI() {
        scoreLabel = new JLabel("Lịch học");

        scoreColumns = new String[]{
                "Học kì",
                "Mã lớp",
                "Tên lớp",
                "Số TC",
                "Bài tập",
                "Cuối kì",
                "Đồ án",
                "Giữa kỳ",
                "Lý thuyết",
                "Thực hành",
                "Thang 10",
                "Thang 4",
                "Chữ"
        };

        totalScoreColumns = new String[]{
                "Học kì",
                "Tổng số tín chỉ",
                "Số tín chỉ học lại",
                "Điểm TBC học kỳ T4",
                "Điểm TBC học bổng",
                "Điểm TBC học kỳ T10",
                "Xếp loại học tập",
                "Điểm rèn luyện"
        };

        scoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        scoreLabel.setMinimumSize(new Dimension(120, 20));
        scoreLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(scoreLabel);
    }

    private void loadScore() {
        ScoreResponse scoreResponse = appService.getScores(jwtResponse.getToken());
        String[][] scoreRows = new String[scoreResponse.getScores().size()][];
        String[][] totalScoreRows = new String[scoreResponse.getTotalScores().size()][];
        int i = 0;
        for (ScoreEntity s : scoreResponse.getScores()) {
            scoreRows[i] = new String[]{
                    s.getSemester(),
                    s.getCode(),
                    s.getSubject(),
                    String.valueOf(s.getCredit()),
                    String.valueOf(s.getScore1()),
                    String.valueOf(s.getScore2()),
                    String.valueOf(s.getScore3()),
                    String.valueOf(s.getScore4()),
                    String.valueOf(s.getScore5()),
                    String.valueOf(s.getScore6()),
                    String.valueOf(s.getScore7()),
                    String.valueOf(s.getScore8()),
                    String.valueOf(s.getScore9()),
            };
            i++;
        }

        i = 0;
        for (TotalScoreEntity s : scoreResponse.getTotalScores()) {
            totalScoreRows[i] = new String[]{
                    s.getSemester(),
                    String.valueOf(s.getTotalCredit()),
                    String.valueOf(s.getRestCredit()),
                    String.valueOf(s.getScore1()),
                    String.valueOf(s.getScore2()),
                    String.valueOf(s.getScore3()),
                    String.valueOf(s.getResultType()),
                    String.valueOf(s.getActivityScore()),
            };
            i++;
        }
        scoreTable = new JTable(scoreRows, scoreColumns);
        totalScoreTable = new JTable(totalScoreRows, totalScoreColumns);

        scoreTable.setAutoCreateRowSorter(true);
        scoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scoreTable.setGridColor(Color.BLACK);
        scoreTable.setRowHeight(50);
        scoreTable.setRowMargin(5);
        scoreTable.setDefaultEditor(Object.class, null);

        totalScoreTable.setAutoCreateRowSorter(true);
        totalScoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        totalScoreTable.setGridColor(Color.BLACK);
        totalScoreTable.setRowHeight(50);
        totalScoreTable.setRowMargin(5);
        totalScoreTable.setDefaultEditor(Object.class, null);

        try{
            this.getComponent(1);
            this.getComponent(2);
            this.remove(1);
            this.remove(1);
        }catch (Exception e){
        }

        JScrollPane sp1 = new JScrollPane(scoreTable);
        JScrollPane sp2 = new JScrollPane(totalScoreTable);

        sp1.setBorder(new EmptyBorder(20, 10, 0, 10));

        sp2.setMinimumSize(new Dimension(400,80));
        sp2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        sp2.setPreferredSize(new Dimension(720,200));
        sp2.setBorder(new EmptyBorder(20, 10, 0, 10));


        this.add(sp1);
        this.add(sp2);
    }
}
