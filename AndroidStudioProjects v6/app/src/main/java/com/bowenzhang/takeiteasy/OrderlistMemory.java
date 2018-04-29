package com.bowenzhang.takeiteasy;

/**
 * Created by liuyi on 4/6/18.
 */

public class OrderlistMemory {
    private static OrderlistMemory instance;
    private String BurgerNum="0";
    private String ChickenNum="0";
    private String OnionNum="0";
    private String FrenchfrisNum="0";
    public String total;
    public String Username="null";
    private OrderlistMemory() {}
    public static OrderlistMemory Instance()
    {
        if(instance==null)
        {
            instance = new OrderlistMemory();
        }
        return instance;
    }
    public String getUsername(){
        return Username;
    }
    public void setUsername(String Username){ this.Username=Username; }

    public String getBurgerNum(){
        return BurgerNum;
    }

    public void setBurgerNum(String BurgerNum){ this.BurgerNum=BurgerNum; }
    public String getChickenNum(){
        return ChickenNum;
    }

    public void setChickenNum(String ChickenNum){ this.ChickenNum=ChickenNum; }
    public String getOnionNum(){
        return OnionNum;
    }

    public void setOnionNum(String OnionNum){ this.OnionNum=OnionNum; }
    public String getFrenchfrisNum(){
        return FrenchfrisNum;
    }
    public void setFrenchfrisNum(String FrenchfrisNum){ this.FrenchfrisNum=FrenchfrisNum; }
    public void calculatecost(String total){

        double numberB=0;
        if(getBurgerNum()!="0"){
            numberB=Double.parseDouble(OrderlistMemory.Instance().getBurgerNum());
        }

        double numberO=0;
        if(getOnionNum()!="0"){
            numberO=Double.parseDouble(OrderlistMemory.Instance().getOnionNum());
        }

        double numberC=0;
        if(getChickenNum()!="0"){
            numberC=Double.parseDouble(OrderlistMemory.Instance().getChickenNum());
        }

        double numberF=0;
        if(getFrenchfrisNum()!="0"){
            numberF=Double.parseDouble(OrderlistMemory.Instance().getFrenchfrisNum());
        }


        double m = (numberB*5.0+numberC*7.0+numberF*2.5+numberO*3.5)*1.3;
        this.total =String.format("%.2f",m);

    }
    public String getTotal(){
        return total;
    }

}
