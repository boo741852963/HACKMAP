package com.example.android.miwok;

public class words {
    private static final int noimg=-1;
    private String ostr;
    private String tstr;
    private int imgresid=noimg;
    private int audresid;
    public words(String o,String t,int audid)
    {
        ostr=o;
        tstr=t;
        audresid=audid;
    }
    public words(String o,String t,int imgid,int audid)
    {
        ostr=o;
        tstr=t;
        imgresid=imgid;
        audresid=audid;
    }
    public String getOri()
    {
        return ostr;
    }
    public String getTran()
    {
        return tstr;
    }
    public int getImgresid()
    {
        return imgresid;
    }
    public boolean hasImg()
    {
        if(imgresid==noimg)
            return false;
        else
            return true;
    }
    public int getAudresid()
    {
        return audresid;
    }
}
