package com.telusinternational.careercity.enums;

public enum Skill {

    // Soft
    GOOD_VERBAL("Good Verbal Communication Skills", "Soft"),
    STRONG_VERBAL("Strong Verbal Communication Skills", "Soft"),
    GOOD_WRITTEN("Good Written Communication Skills", "Soft"),
    STRONG_WRITTEN("Strong Written Communication Skills", "Soft"), LISTENING("Active Listening", "Soft"),
    MULTITASKING("Multitasking", "Soft"), PROBLEM_SOLVING("Problem Solving / Analytical Skills", "Soft"),
    GATD("Good attention to detail", "Soft"), UPSELLING("Upselling", "Soft"), CHANGE_MGMT("Change Management", "Soft"),
    PROJECT_MGMT("Project Management", "Soft"), BCP("Business Continuity Planning", "Soft"),

    // Technical
    NAVIGATION("Internet Savvy / Navigation", "Technical"),
    INTERNET_TRBL("Basic Troubleshooting - Basic Internet Network / Internet Connectivity", "Technical"),
    COMPUTER_TRBL("Basic Troubleshooting - Computer Parts / Peripherals", "Technical"),
    MOBILE_TRBL("Basic Troubleshotting - Mobile Devices", "Technical"), BPS_WORD("BPS - MS Word", "Technical"),
    BPS_PPT("BPS - MS Powerpoint", "Technical"), BPS_EXCEL("BPS - MS Excel", "Technical"),
    OS_WINDOWS("Operating System Knowledge - Windows", "Technical"),
    OS_MAC("Operating System Knowledge - Mac", "Technical"), DEPLOYMENT("Build Deployment", "Technical"),
    RELEASE_MGMT("Release Management", "Technical"),

    // IT
    POWERSHELL("Scripting Knowledge - Powershell", "IT"), BASH("Scripting Knowledge - BASH", "IT"),
    NETWORKING("Networking", "IT"), UNIX("Unix", "IT"), LINUX("Linux", "IT"), AIX("AIX", "IT"), HPUX("HP UX", "IT"),
    SOLARIS("Solaris", "IT"), SOLARWINDS("SolarWinds", "IT"), DNS("DNS", "IT"), VMWARE("VMWare", "IT"),
    CSHARP("C#", "IT"), ASP("ASP", "IT"), VBNET("VB.NET", "IT"), NICE("Nice IA", "IT"),
    WIN_SERVER("Windows Server", "IT"), SQL("SQL", "IT"), TCPIP("TCP/IP", "IT"), ITAUDIT("IT_AUDIT", "IT"),
    IT_DISASTER_REC("IT Disaster Recovery", "IT"), ORACLE("Oracle Database", "IT"), MSSQL("MSSQL", "IT"),
    MSX("MS Exchange", "IT"), MYSQL("MySQL", "IT"), CONTROLIM("ControlIM", "IT"), INFORMATICA("Informatica", "IT"),
    NETBACKUP("NetBackup", "IT"), NETAPP("NetApp", "IT"), SNAPPROTECT("SnapProtect", "IT"),
    EMC("EMC VMAX (v2/3)", "IT"), CISCO_ASA("CISCO ASA Firewalls", "IT"), FORTINET("Fortinet", "IT"),
    TICKETING_SNOW("Ticketing Tools (SNow)", "IT"), TICKETING_REMEDY("Ticketing Tools (Remedy)", "IT"),
    WEBLOGIC("Weblogic", "IT"), WEBSPHERE("Webspher", "IT"), MONITORING("App Support / Monitoring", "IT"),
    APP_INCIDENT_MGMT("App Incident Management", "IT"), JAVA("Java", "IT"), J2EE("J2EE", "IT"), JSP("JSP", "IT"),
    HTML("HTML", "IT"), JS("Javascript", "IT"), CSS("CSS", "IT"), SPRING("Spring", "IT"),
    REST("RESTful Webservices", "IT"), TELUS("TELUS Framework", "IT");

    private String skillName;
    private String category;

    Skill(String skillName, String category) {

        this.skillName = skillName;
        this.category = category;

    }

    public String getSkillName() {

        return this.skillName;

    }

    public String getCategory() {

        return this.category;

    }

}
