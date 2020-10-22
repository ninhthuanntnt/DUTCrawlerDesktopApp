package com.ntnt.dutcrawler.app.panels;

import com.ntnt.dutcrawler.Models.JwtResponse;
import com.ntnt.dutcrawler.Models.ScheduleEntity;
import com.ntnt.dutcrawler.app.utils.SemesterUtils;
import com.ntnt.dutcrawler.services.AppService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleTab extends JPanel {

    private JLabel scheduleLabel;
    private JComboBox semesterComboBox;
    private JTable scheduleTable;
    private String[] tableColumns;
    private List<String> semesters;
    private JwtResponse jwtResponse;

    private AppService appService;

    public ScheduleTab(JwtResponse jwtResponse){
        this.jwtResponse = jwtResponse;
        this.appService = AppService.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.semesters = SemesterUtils.renderSemestersFromClassName(this.jwtResponse.getAdditionalData());
        initUI();
        this.semesters = new ArrayList<>();
    }

    private void initUI(){
        scheduleLabel = new JLabel("Lịch học");
        semesterComboBox = new JComboBox(semesters.toArray());
        tableColumns = new String[]{"Mã lớp", "Tên lớp", "Số TC", "Học phí", "Nợ", "Giảng viên", "Thời khóa biểu", "Tuần học"};

        scheduleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        scheduleLabel.setMinimumSize(new Dimension(120,20));
        scheduleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        scheduleLabel.setAlignmentX(CENTER_ALIGNMENT);
        scheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        semesterComboBox.setMinimumSize(new Dimension(100,20));
        semesterComboBox.setMaximumSize(new Dimension(150, 30));
        semesterComboBox.setPreferredSize(new Dimension(150,30));
        semesterComboBox.setSelectedIndex(semesters.size() - 1);
        semesterComboBox.setAlignmentX(CENTER_ALIGNMENT);

        //Binding event
        this.semesterComboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                String semester = (String) e.getItem();
                String type = SemesterUtils.getSemesterTypeFromSemester(semester);
                reloadSchedule(type);
            }
        });

        this.add(scheduleLabel);
        this.add(semesterComboBox);
    }

    public void panelDidMount(){
        this.loadSchedule("2010");
    }

    private void reloadSchedule(String type){
        this.remove(2);
        loadSchedule(type);
    }

    private void loadSchedule(String type){
        List<ScheduleEntity> schedules = appService.getSchedules(type, jwtResponse.getToken());
        String[][] rows = new String[schedules.size()][];
        int i = 0;
        for(ScheduleEntity s : schedules){
            rows[i] = new String[]{s.getCode(),
                    s.getName(),
                    String.valueOf(s.getCredit()),
                    String.valueOf(s.getTuition()),
                    s.isPayed()?"":"Nợ",
                    s.getLecturer(),
                    s.getSchedule(),
                    s.getStudyingWeek()
            };
            i++;
        }
        scheduleTable = new JTable(rows, this.tableColumns);
        scheduleTable.setAutoCreateRowSorter(true);
        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scheduleTable.setGridColor(Color.BLACK);
        scheduleTable.setRowHeight(50);
        scheduleTable.setRowMargin(5);
        scheduleTable.setDefaultEditor(Object.class, null);

        try{
            this.getComponent(2);
            this.remove(2);
        }catch (Exception e){
        }

        JScrollPane sp = new JScrollPane(scheduleTable);
        sp.setBorder(new EmptyBorder(20,10,0,10));
        this.add(sp);
    }

    private void renderSemesters(){
        String val = jwtResponse.getAdditionalData().substring(0,2);
        Calendar curDate = Calendar.getInstance();
        int curYear = curDate.get(Calendar.YEAR);
        int startYear = -1;

        int curYearTemp = curYear;
        while(true){
            if(curYearTemp%Math.pow(10,val.length()) == Integer.parseInt(val)){
                startYear = curYearTemp;
                break;
            }
            curYearTemp--;
        }

        if(startYear != -1){
            for(; startYear <= curYear ; startYear++){
                String semester = startYear + "-" + startYear + 1;
                semesters.add("1/" + semester);
                semesters.add("2/" + semester);
                semesters.add("Hè/" + semester);
            }
        }
    }
}

