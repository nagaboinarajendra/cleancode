package com.epam.engx.cleancode.functions.task2;


import java.util.Map;


public class SitePage {

    private static final String HTTP = "http://";
    private static final String EDITABLE = "/?edit=true";
    private static final String DOMAIN = "mysite.com";
    private static final String  URL = HTTP + DOMAIN + EDITABLE;

    private String siteGroup;
    private String userGroup;

    public SitePage(String siteGroup, String userGroup) {
        this.siteGroup = siteGroup;
        this.userGroup = userGroup;
    }

    public String getEditablePageUrl(Map<String, String> params) {
        return URL + generateParamString(params) + getAttributes();
    }

    private String generateParamString(Map<String, String> params) {
        String paramsString = "";
        for (Map.Entry<String, String> param : params.entrySet())
            paramsString += "&" + param.getKey() + "=" + param.getValue();
        return paramsString;
    }

    private String getAttributes() {
        return "&siteGrp=" + getSiteGroup() + "&userGrp=" + getUserGroup();
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getSiteGroup() {
        return siteGroup;
    }




}
