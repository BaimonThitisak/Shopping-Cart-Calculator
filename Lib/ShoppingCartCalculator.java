package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {
    /**
     * | จะreturn0.0ถ้าตะกร้าเป็น nullหรือ empty
     * | ถ้า CartItem มี Price หรือ Quantity ติดลบจะไม่ถูกนับ
     * | ถ้าสินค้ามี BOGO เมื่อซื้อแล้วชิ้นต่อไปจะฟรี
     * | เมื่อซื้อแบบ BULK แล้วมีจำนวนมากกว่าหรือเท่ากับ6ชิ้น จะลด10%
     * @param รายละเอียดสินค้าที่userจะซื้อ
     * @return ราคาของในตะกร้าทั้งหมดรวมกัน(total)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        double total = 0.0;
        //Check ของตะกร้าว่าเป็น null มั้ย
        if (items == null) {
            return total;
        }
        for (CartItem cartItem : items) {
            //Check ค่าของ Price และ Quantity ถ้าติดลบจะไม่นับ
            if (!(cartItem.price() < 1 || cartItem.quantity() < 1)) {
                if (cartItem.sku().equals("NORMAL")) {
                    total += cartItem.quantity() * cartItem.price();
                } else if (cartItem.sku().equals("BOGO")) {
                    double bogoitem = Math.floor(cartItem.quantity() / 2) + (cartItem.quantity() % 2);
                    total += bogoitem * cartItem.price();
                } else if (cartItem.sku().equals("BULK")) {
                    if (cartItem.quantity() >= 6) {
                        double tmp = (cartItem.quantity() * cartItem.price());
                        total += tmp - (tmp*0.1);
                    }
                }
            }
        }
        return total;
    }
}