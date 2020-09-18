package com.juiniot.common.utils;

public class IdUtils {

    public static Long  snowflakeID(){
        Snowflake snowflake = new Snowflake();
        return snowflake.nextId();
    }
}
