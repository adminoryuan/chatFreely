package org.freely.commom.contans;

public final class RouteConstants  {
    private static final String SessionKeyPrefix="route_session_";

    public static String MergeRoute(String sessionId){
        return String.format("{0}_{1}",SessionKeyPrefix,sessionId);
    }
}
