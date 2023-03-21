package edu.qc.seclass.fim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart singleInstance = null;

    // The map stores the product ID as its key and the quantity as its value.
    public Map<String, double[]> cart;
    public double total;

    public ShoppingCart( ) {
        cart = new HashMap<String, double[]>();

    }
    public ShoppingCart(String id, double price, double quan){
        cart= new HashMap<String, double[]>();
        double[] r= {price, quan};
        this.cart.put(id, r);

    }
    public Map<String, double[]> getCart() {
        return cart;
    }

    public void set(Map<String, int[]> cart) {

    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double  getPrice(String a) {
        double[] tmparr= this.cart.get(a);
        return tmparr[0];
    }

    public void setPrice(double p, String a) {
        double[] tmparr= this.cart.get(a);
        tmparr[0]= p;
        cart.put(a,tmparr);

    }
    public double  getQuan(String a) {
        double[] tmparr= this.cart.get(a);
        return tmparr[1];
    }

    public void setQuan(double p, String a) {
        double[] tmparr= this.cart.get(a);
        tmparr[1]= p;
        cart.put(a,tmparr);
    }
    public String getProId(){
        Map.Entry<String,double[]> entry = this.cart.entrySet().iterator().next();
        return entry.getKey();
    }

    public double localTotal(String a){
        if(getQuan(a)>0){
            total= getQuan(a)* getPrice(a);
        }
        return total ;
    }

    public double subTotal(ArrayList<ShoppingCart> sF){
        double subTot=0.0;
        for (ShoppingCart s: sF  ){
            subTot = subTot+ s.getTotal();
        }
        return subTot;

    }

    public static ShoppingCart getInstance() {
        if(singleInstance == null) {
            singleInstance = new ShoppingCart();
        }
        return singleInstance;
    }

}
