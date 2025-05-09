package src.page;

import java.util.Objects;

public class Database {
    public String getUserId(String value){
        String val;
        if(Objects.equals(value, "getUserId")){
            val= String.valueOf(2);
        }else {
            val= String.valueOf(1);
        }
        return val;
    }
}
