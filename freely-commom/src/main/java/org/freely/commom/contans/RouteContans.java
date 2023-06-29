package org.freely.commom.contans;

public class RouteContans {
    private static final String SessionKeyPrefix="route_session_";

    public String MergeRoute(String sessionId){
        return String.format("{0}_{1}",SessionKeyPrefix,sessionId);
    }
}
