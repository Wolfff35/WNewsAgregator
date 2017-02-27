package com.wolfff.wnewsagregator.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wolff on 22.10.2016.
 */

public class ConvertValues {
    public String date_longToString(long d,String dateFormat){
        //"dd/MM/yy hh:mm"
        Date date=new Date(d);
        SimpleDateFormat df2 = new SimpleDateFormat(dateFormat);
        return df2.format(date);

    }

}
