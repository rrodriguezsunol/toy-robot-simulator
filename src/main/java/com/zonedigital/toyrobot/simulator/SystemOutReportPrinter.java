package com.zonedigital.toyrobot.simulator;

public class SystemOutReportPrinter implements ReportPrinter {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
