package org.freely.commom.contans;

public final class RabbitMqConstants {

    public static String ImClusterExchangeName="im_exchange";

    private static String QueuePrefix="im_queue";

    public static String MergeQueue(String address){
        return String.format("{0}_{1}",QueuePrefix,address);
    }

}
