//Sahil Goswami
//2021281

import java.io.*;
import java.util.*;
interface User
{
    public void deliveries();
    public void coupons(float coupon);
}
class category
{
    private int C_ID;
    private String C_name;
    private ArrayList<Product> all_product = new ArrayList<>();
    public int getC_ID()
    {
        return this.C_ID;
    }

    public void setC_ID(int id)
    {
        this.C_ID=id;
    }

    public void setC_name(String name)
    {
        this.C_name=name;
    }

    public String getC_name()
    {
        return this.C_name;
    }
    public ArrayList<Product> getAll_product()
    {
        return this.all_product;
    }
}
class Product{
    private float P_ID;
    private String P_name;
    private String others;
    private float price;
    private int quantity;
    HashMap<Float, Integer[]> discount_admin = new HashMap<>();
    public float getP_ID()
    {
        return this.P_ID;
    }
    public void setQuantity(int quantity1)
    {
        quantity=quantity1;
    }
    public void dec_Quantity(int quantity1)
    {
        quantity-=quantity1;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setP_ID(float id)
    {
        this.P_ID=id;
    }
    public void setP_name(String name)
    {
        this.P_name=name;
    }
    public String getP_name()
    {
        return this.P_name;
    }
    public void setOthers(String others)
    {
        this.others=others;
    }
    public String getOthers()
    {
        return this.others;
    }
    public void setPrice(float price)
    {
        this.price=price;
    }
    public float getPrice()
    {
        return this.price;
    }

}
class admin{
    private final category C1;
    private final Product P1;
    private ArrayList<category> all_category = new ArrayList<>();
    private ArrayList<Deal> all_deals = new ArrayList<>();
    public ArrayList<Deal> getAll_deals()
    {
        return all_deals;
    }
    Scanner sc;
    admin(category C, Product P)
    {
        C1=C;
        P1=P;
        sc=new Scanner(System.in);
    }
    public String auth()
    {
        boolean check;
        Scanner sc= new Scanner(System.in);
        System.out.println("Dear Admin,");
        System.out.println("Please enter your username and password");
        String name= sc.nextLine();
        String password=sc.nextLine();
        if((name.equals("Beff Jezos") && password.equals("1234")) || (name.equals("Gill Bates") && password.equals("4321")))
        {
            check=true;
        }
        else{
            this.auth();
        }
        return name;
    }

    public void add_category(category C) throws IOException {
        int id;
        while(true)
        {   boolean check=false;
            System.out.println("Add category ID");
            id=sc.nextInt();
            sc.nextLine();
            for(category i:all_category){
                if(i.getC_ID()==id)
                {
                    System.out.println("Dear Admin, the category ID is already used!!! Please set a different and a unique category ID");
                    check=true;
                }
            }
            if(!check)
            {
                break;
            }
        }
            C.setC_ID(id);
            System.out.println("Add name of the category");
            String name=sc.nextLine();
            C.setC_name(name);
            this.add_product(C);
            all_category.add(C);
    }

    public void delete_category(int id, String name)
    {
        for(int i=0;i<all_category.size();i++)
        {
            if(all_category.get(i).getC_ID()==id && all_category.get(i).getC_name().equals(name))
            {
                all_category.get(i).getAll_product().clear();
                all_category.remove(i);
            }
        }
    }

    public void add_product(category C1) throws IOException {
        System.out.println("Add a Product");
        String line = "";
        String input = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bufferedReader= new BufferedReader(isr);
        do
        {
            line = bufferedReader.readLine();
            if(!line.isBlank())
                 input = input + line + "\n";
        }while(!line.isBlank());
        String[] strArr = input.split("\\R");
        int a = strArr[0].length();
        int b = strArr[1].length();
        int c =strArr[strArr.length-1].length()+strArr[strArr.length-2].length();
        String others = input.substring((a+b)+1,(input.length()-c)-3);
        String[] p=strArr[strArr.length-2].split(" ");
        String[] q=strArr[strArr.length-1].split(" ");
        Product P1=new Product();
        P1.setP_name(strArr[0].substring(14,a));
        P1.setP_ID(Float.parseFloat(strArr[1].substring(12,b)));
        P1.setOthers(others);
        P1.setPrice(Integer.parseInt(p[1]));
        P1.setQuantity(Integer.parseInt(q[1]));
//        C1.all_product.add(P1);
//        C1.setAll_product(P1);
        C1.getAll_product().add(P1);
    }

    public void delete_product() throws IOException {
        System.out.println("Enter the name of category:");
        String name2=sc.nextLine();
        System.out.println("Enter the Product ID");
        float id2=sc.nextFloat();
        sc.nextLine();
        for(int i=0;i<all_category.size();i++)
        {
            for(int j=0;j<all_category.get(i).getAll_product().size();j++)
            {
                  if(all_category.get(i).getC_name().equals(name2) && all_category.get(i).getAll_product().get(j).getP_ID()==id2)
                  {
                      all_category.get(i).getAll_product().remove(j);
                      if(all_category.get(i).getAll_product().size()==0)
                      {
                          System.out.println("Category can't be empty you have to add product in this category");
                          System.out.println("if do you want to add product press 1 else if you don't want to add press 0");
                          int ch=sc.nextInt();
                          sc.nextLine();
                          if(ch==1)
                          {
                              this.add_product(all_category.get(i));
                          }
                          else{
                              all_category.remove(i);
                              break;
                          }
                      }
                  }
            }
        }
    }

    public void discount_product(float id)
    {
        System.out.println("Enter discount for Elite customers respectively(in % terms):");
        int dis_E=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter discount for Prime customers respectively(in % terms):");
        int dis_P=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter discount for Normal customers respectively(in % terms):");
        int dis_N=sc.nextInt();
        sc.nextLine();
        Integer[] s=new Integer[3];
        s[0]=dis_E;
        s[1]=dis_P;
        s[2]=dis_N;
        P1.discount_admin.put(id,s);
    }

    public void giveaway_deals(float id1,float id2, float price1, float price2, float price3)
    {
        all_deals.add(new Deal(id1,id2,price1,price2,price3));
    }
    public ArrayList<category> getAll_category()
    {
        return all_category;
    }
}

class customer implements User
{
    private String name;
    private String password;
    protected String category;
    private double wallet;
    protected float discount;
    protected float Delcharges;
    private ArrayList<Order> all_orders;
    private ArrayList<Deal> deals;
    public ArrayList<Integer> coupons;
    customer()
    {
       category="Normal";
       wallet=1000;
       all_orders=new ArrayList<>();
       deals=new ArrayList<>();
    }
    public void SignUp(String name2, String password2)
    {
        this.name=name2;
        this.password=password2;
//          this.setName(name2);
//          this.setPassword(password2);
    }
    public boolean LoginIn(customer C,String name1, String password1)
    {
        boolean check=false;
        if(C.name.equals(name1) && C.password.equals(password1))
        {
            check=true;
        }
        return check;
    }
    public customer Upgrade_status(customer C,double amount1)
    {
        System.out.println("Current Status: "+this.getCategory());
        System.out.print("Choose new status: ");
        Scanner sc=new Scanner(System.in);
        String status=sc.nextLine();
        String name=C.getName();
        String pass=C.getPassword();
        if(status.equals("Elite"))
        {
            C=new E_customer(name, pass);
            C.setWallet(amount1);
            C.subWallet(300);
            System.out.println("Status updated to "+C.getCategory());
        }
        else if(status.equals("Prime"))
        {
            C=new P_customer(name,pass);
            C.setWallet(amount1);
            C.subWallet(200);
            System.out.println("Status updated to "+C.getCategory());
        }
        return C;
    }
    public void product_catalog(admin A)
    {
        int count1=1;
        for(int i=0;i<A.getAll_category().size();i++)
        {   int count2=1;
            System.out.println(count1+") Category ID: "+A.getAll_category().get(i).getC_ID());
            System.out.println("   Category name: "+A.getAll_category().get(i).getC_name());
            for(int j=0;j<A.getAll_category().get(i).getAll_product().size();j++)
            {
                System.out.println("   "+count2+") Product ID: "+A.getAll_category().get(i).getAll_product().get(j).getP_ID());
                System.out.print("      Product Name: "+A.getAll_category().get(i).getAll_product().get(j).getP_name());
                System.out.println("     "+A.getAll_category().get(i).getAll_product().get(j).getOthers());
                System.out.println("      Price: "+A.getAll_category().get(i).getAll_product().get(j).getPrice());
                count2++;
            }
            count1++;
        }
    }

    public void add_productToCart(float id, int quantity1, admin A)
    {
       for(int i=0;i<A.getAll_category().size();i++) {
           for (int j = 0; j < A.getAll_category().get(i).getAll_product().size(); j++) {
               if (A.getAll_category().get(i).getAll_product().get(j).getP_ID() == id) {
                   if (A.getAll_category().get(i).getAll_product().get(j).getQuantity() >= quantity1)
                   {
                       this.setAll_orders(new Order(id, quantity1, A));
//                       A.all_category.get(i).all_product.get(j).dec_Quantity(quantity1);
                       System.out.println("Item successfully added to cart");
                   }
                   else {
                       System.out.println("Product is out of stock");
                   }
               }
           }
       }
    }

    public void deliveries()
    {
        System.out.println("Your order will be delivered within 7-10 Days");
    }
    public void coupons(float amount)
    {

    }
    public void setName(String name2)
    {
        this.name=name2;
    }
    public void setPassword(String password2)
    {
        this.password=password2;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getName()
    {
        return name;
    }
    public String getCategory()
    {
        return this.category;
    }
    public void addWallet(double wallet)
    {
        this.wallet+=wallet;
    }
    public void subWallet(double wallet){ this.wallet-=wallet;}
    public double getWallet()
    {
        return this.wallet;
    }
    public void setWallet(double wallet)
    {
        this.wallet=wallet;
    }
    public void setAll_orders(Order order)
    {
        this.all_orders.add(order);
    }
    public void setDeals(Deal deal)
    {
        this.deals.add(deal);
    }
    public ArrayList<Order> getAll_orders()
    {
        return this.all_orders;
    }
    public ArrayList<Deal> getDeals()
    {
        return this.deals;
    }
    public void setCoupons(int coupon)
    {
        this.coupons.add(coupon);
    }
    public ArrayList<Integer> getCoupons()
    {
        return this.coupons;
    }
    public void setDiscount(float discount1)
    {
        this.discount=discount1;
    }
    public float getDiscount()
    {
        return this.discount;
    }
    public void setDelCharges(float charge)
    {
        this.Delcharges= (float) (100+(charge*0.05));
    }
    public float getDelCharges()
    {
        return this.Delcharges;
    }

}

class P_customer extends customer
{
    P_customer(String name, String password)
    {
        super();
        this.setName(name);
        this.setPassword(password);
        this.category="Prime";
        this.coupons=new ArrayList<>();
    }

    @Override
    public void setDiscount(float discount1) {
        this.discount=discount1;
    }

    @Override
    public float getDiscount() {
        return this.discount;
    }

    @Override
    public void setDelCharges(float charge) {
        this.Delcharges= (float) (100+(charge*0.02));
    }

    @Override
    public float getDelCharges() {
        return this.Delcharges;
    }

    @Override
    public void deliveries() {
        System.out.println("Order placed. It will be delivered in 3-6 days.");
    }
    @Override
    public void coupons(float amount)
    {
            int s=1,e=2;
            //for generating random values between 1 and 2
            int c=(int)Math.floor(Math.random()*(e-s+1)+s);
            for(int i=0;i<c;i++)
            {
                int l=5;
                int u=15;
                //for generating random values between 5%-15%
                int coupon=(int)Math.floor(Math.random()*(u-l+1)+l);
                System.out.println("You got a coupon of "+coupon+"% discount. Congratulations!!");
                this.coupons.add(coupon);

            }
    }
}
class E_customer extends customer
{
    E_customer(String name, String password)
    {
        super();
        this.setName(name);
        this.setPassword(password);
        this.category="Elite";
        this.coupons=new ArrayList<>();

    }
    @Override
    public void setDiscount(float discount1) {
        this.discount=discount1;
    }

    @Override
    public float getDiscount() {
        return this.discount;
    }

    @Override
    public void setDelCharges(float charge) {
        this.Delcharges= (float) 100;
    }

    @Override
    public float getDelCharges() {
        return this.Delcharges;
    }

    @Override
    public void deliveries() {
        System.out.println("Your order will be delivered within 2 days");
    }
    @Override
    public void coupons(float amount)
    {
            int s=1,e=2;
            //for generating random values between 1 and 2
            int c=(int)Math.floor(Math.random()*(e-s+1)+s);
            System.out.println(c);
            for(int i=0;i<c;i++)
            {
                int l=5;
                int u=15;
                //for generating random values between 5%-15%
                int coupon=(int)Math.floor(Math.random()*(u-l+1)+l);
                System.out.println("You got a coupon of "+coupon+"% discount. Congratulations!!");
                this.setCoupons(coupon);
            }
        }
    }


class Deal{
    private float id1;
    private float id2;
    private float N_price;
    private float E_price;
    private float P_price;
    Deal(float id1, float id2,float N_price, float E_price, float P_price)
    {
        this.id1=id1;
        this.id2=id2;
        this.N_price=N_price;
        this.E_price=E_price;
        this.P_price=P_price;
    }
    public float getId1()
    {
        return this.id1;
    }
    public float getId2()
    {
        return this.id2;
    }
    public float getN_price()
    {
        return this.N_price;
    }
    public float getE_price()
    {
        return this.E_price;
    }
    public float getP_price()
    {
        return this.P_price;
    }
}

class Order {
    private float id;
    private int quantity_User;
    private admin A2;

    Order(float id, int quantity, admin A) {
        this.id = id;
        this.quantity_User = quantity;
        A2=A;
    }

    public float getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity_User;
    }
    public float order_price()
    {
        float amount=0;
        int id1=(int)Math.floor(this.id);
        for(int i=0;i< A2.getAll_category().size();i++)
        {
            if(A2.getAll_category().get(i).getC_ID()==id1)
            {
                for(int j=0;j<A2.getAll_category().get(i).getAll_product().size();j++)
                {
                   if(A2.getAll_category().get(i).getAll_product().get(j).getP_ID()==this.id)
                   {
                       float p=A2.getAll_category().get(i).getAll_product().get(j).getPrice();
                       amount+=(p*this.quantity_User);
                       break;
                   }
                }
            }

        }
        return amount;
    }
}
class Cart
{
    admin A1;
    Product P1;
    Cart(admin A, Product P)
    {
        A1=A;
        P1=P;
    }
    public void View_cart(customer C)
    {
        for(int i=0;i<C.getAll_orders().size();i++)
        {
            int id=(int)Math.floor(C.getAll_orders().get(i).getId());
            for(int j=0;j<A1.getAll_category().size();j++)
            {
                if(A1.getAll_category().get(j).getC_ID()==id)
                {
                    System.out.println("Category ID: "+A1.getAll_category().get(j).getC_ID());
                    System.out.println("Category Name: "+A1.getAll_category().get(j).getC_name());
                    for(int k=0;k<A1.getAll_category().get(i).getAll_product().size();k++)
                    {
                        if(A1.getAll_category().get(j).getAll_product().get(k).getP_ID()==C.getAll_orders().get(i).getId())
                        {
                            System.out.println("Product ID: "+A1.getAll_category().get(j).getAll_product().get(k).getP_ID());
                            System.out.println("Product Name: "+A1.getAll_category().get(j).getAll_product().get(k).getP_name());
                            System.out.println(A1.getAll_category().get(j).getAll_product().get(k).getOthers());
                            System.out.println("Price: "+A1.getAll_category().get(j).getAll_product().get(k).getPrice());
                        }
                    }
                }
            }
        }
        int count=1;
        System.out.println(C.getDeals());
        for(int i=0;i<C.getDeals().size();i++)
        {
            System.out.println("Deal No.: "+count);
            System.out.println("ID of Product 1: "+C.getDeals().get(i).getId1());
            System.out.println("ID of Product 2: "+C.getDeals().get(i).getId2());
        }
    }

    public void empty_cart(customer C)
    {
        C.getAll_orders().clear();
        C.getDeals().clear();
        System.out.println("Cart successfully emptied!!!!");
    }
    public float total(customer C)
    {
        float amount=0;
        for(int i=0;i<C.getAll_orders().size();i++)
        {
            amount+=C.getAll_orders().get(i).order_price();
            float id_p=C.getAll_orders().get(i).getId();
            int id_c=(int)Math.floor(id_p);
            for(int r=0;r<A1.getAll_category().size();r++)
            {
                if(A1.getAll_category().get(r).getC_ID()==id_c)
                {
                    for(int s=0;s<A1.getAll_category().get(r).getAll_product().size();s++)
                    {
                        if(A1.getAll_category().get(r).getAll_product().get(s).getP_ID()==id_p)
                        {
                                A1.getAll_category().get(r).getAll_product().get(s).dec_Quantity(C.getAll_orders().get(i).getQuantity());
                        }
                    }
                }

            }
        }
        if(C.getDeals().size()!=0)
        {
            for(int j=0;j<C.getDeals().size();j++)
            {
                if(C.getCategory().equals("Normal"))
                {
                    amount+=C.getDeals().get(j).getN_price();
                }
                else if(C.getCategory().equals("Elite"))
                {
                    amount+=C.getDeals().get(j).getE_price();
                }
                else {
                    amount+=C.getDeals().get(j).getP_price();
                }
            }
        }
        return amount;
    }
    public void checkout_cart(customer C)
    {
        float amount=0;
        int coupon_max=0;
        float p=0;
        float decrease=0;
        if(C.getCoupons().size()!=0)
        {
            coupon_max= Collections.max(C.getCoupons());
        }
        float dis_admin=0;
        float max_discount=0;
        for(int i=0;i<C.getAll_orders().size();i++)
        {
           for(float j:P1.discount_admin.keySet())
           {
               if(j==C.getAll_orders().get(i).getId())
               {
                   Integer[] s=P1.discount_admin.get(j);
                   if(C.category.equals("Normal"))
                   {
                       dis_admin=s[2];
                   }
                   else if(C.category.equals("Elite"))
                   {
                       dis_admin=s[0];
                   }
                   else if(C.category.equals("Prime"))
                   {
                       dis_admin=s[1];
                   }
                   break;
               }
           }
           if(C.getCategory().equals("Normal"))
           {
               C.setDiscount(0);
               float a=this.total(C);
               C.setDelCharges(this.total(C));
               max_discount= Math.max(Math.max(coupon_max,dis_admin), C.getDiscount());
           }
           else if(C.getCategory().equals("Elite"))
           {
               C.setDiscount(10);
               C.setDelCharges(100);
               max_discount= Math.max(Math.max(coupon_max,dis_admin), C.getDiscount());

           }
           else if(C.getCategory().equals("Prime")) {
               C.setDiscount(5);
               float a=this.total(C);
               C.setDelCharges(this.total(C));
               max_discount= Math.max(Math.max(coupon_max,dis_admin), C.getDiscount());
           }
            for(int m=0;m<C.getAll_orders().size();m++)
            {
                float id1=C.getAll_orders().get(i).getId();
                int id2=(int)Math.floor(id1);
                for(int l=0;l<A1.getAll_category().size();l++)
                {
                    if(A1.getAll_category().get(l).getC_ID()==id2)
                    {
                        for(int j=0;j<A1.getAll_category().get(l).getAll_product().size();j++)
                        {
                            if(A1.getAll_category().get(l).getAll_product().get(j).getP_ID()==id1)
                            {
                                System.out.println("Product Name: "+A1.getAll_category().get(l).getAll_product().get(j).getP_name());
                                System.out.println("Product ID: "+A1.getAll_category().get(l).getAll_product().get(j).getP_ID());
                                System.out.println(A1.getAll_category().get(l).getAll_product().get(j).getOthers());
                                System.out.println("Price: "+A1.getAll_category().get(l).getAll_product().get(j).getPrice());
                            }
                        }
                    }
                }
                p=C.getAll_orders().get(m).order_price();
                decrease=(float) p*(max_discount/100);
                amount+=(p-decrease);
            }
//            System.out.println(p);
//            System.out.println(decrease);
//            System.out.println(amount);
//            System.out.println(max_discount);
//            System.out.println(C.getDelCharges());
        }
        float price_deals=0;
        if(C.getDeals().size()!=0)
        {
            for(int n=0;n<C.getDeals().size();n++)
            {
                if(C.getCategory().equals("Normal"))
                {   price_deals+=C.getDeals().get(n).getN_price();
                    amount+=C.getDeals().get(n).getN_price();
                }
                else if(C.getCategory().equals("Elite"))
                {   price_deals+=C.getDeals().get(n).getN_price();
                    amount+=C.getDeals().get(n).getE_price();
                }
                else {
                    price_deals+=C.getDeals().get(n).getN_price();
                    amount+=C.getDeals().get(n).getP_price();
                }
            }
        }
//        System.out.println(price_deals);
        if(C.getWallet()>p+C.getDelCharges()+price_deals)
        {
            System.out.println("Your order is placed successfully. Details: ");
            System.out.println("Delivery Charges: Rs "+C.getDelCharges());
            System.out.println("Discount: "+max_discount+"% of "+p+" = "+decrease);
            amount+=C.getDelCharges();
            System.out.println("Total Cost = Rs "+amount);
            C.deliveries();
            if((C.getCategory().equals("Elite") && C instanceof E_customer)|| (C.getCategory().equals("Prime") && C instanceof P_customer))
            {
                if(amount>5000)
                {
                    C.coupons(amount);
                }
            }
            if(max_discount==coupon_max)
            {
                C.getCoupons().remove(max_discount);
            }
            C.subWallet(amount);
            this.empty_cart(C);
        }
        else {
            System.out.println("Insufficient balance!! Please try again");
        }
    }
}
public class flipzon{
    public static void main(String[] args) throws IOException {
        category C = new category();
        Product P = new Product();
        customer Cust = new customer();
        ArrayList<customer> registered_cust = new ArrayList<>();
        admin A = new admin(C,P);
        Cart cart=new Cart(A,P);
        Scanner sc = new Scanner(System.in);
        boolean entry=true;
        while(entry)
        {
            String enter;
            enter=sc.nextLine();
            l1: switch(enter)
            {
                case "Enter":
                    while(true)
                    {
                        System.out.println("WELCOME TO FLIPZON");
                        System.out.println();
                        System.out.println("    1) Enter as Admin");
                        System.out.println("    2) Explore the Product Catalog");
                        System.out.println("    3) Show Available Deals");
                        System.out.println("    4) Enter as Customer");
                        System.out.println("    5) Exit the Application");
                        int choice1;
                        choice1=sc.nextInt();
                        sc.nextLine();
                        l2: switch(choice1)
                        {
                            case 1:
                                String n=A.auth();
                                while(true)
                                {
                                    System.out.println("Welcome "+n);
                                    System.out.println("Please choose any one of the following actions: ");
                                    System.out.println("    1) Add category");
                                    System.out.println("    2) Delete category");
                                    System.out.println("    3) Add Product");
                                    System.out.println("    4) Delete Product");
                                    System.out.println("    5) Set Discount on Product");
                                    System.out.println("    6) Add giveaway deal");
                                    System.out.println("    7) Back");
                                    int choice2=sc.nextInt();
                                    sc.nextLine();
                                    l3: switch(choice2)
                                    {
                                        case 1:
                                            category dummy = new category();
                                            A.add_category(dummy);
                                            break;

                                        case 2:
                                            System.out.println("Enter the name of category which you want to delete:");
                                            String name1 = sc.nextLine();
                                            System.out.println("Enter the id of category:");
                                            int id = sc.nextInt();
                                            sc.nextLine();
                                            A.delete_category(id, name1);
                                            break;

                                        case 3:
                                            System.out.println("Enter the Category ID");
                                            int id1 = sc.nextInt();
                                            sc.nextLine();
                                            boolean check=false;
                                            for(int i=0;i<A.getAll_category().size();i++)
                                            {
                                                if(A.getAll_category().get(i).getC_ID()==id1)
                                                {
                                                    A.add_product(A.getAll_category().get(i));
                                                    check=true;
                                                }
                                            }
                                            if(!check)
                                            {   category dummy1=new category();
                                                A.add_category(dummy1);
                                            }
                                            break;

                                        case 4:
                                            A.delete_product();
                                            break;

                                        case 5:
                                            System.out.println("Dear Admin give the Product ID you want to add discount for");
                                            System.out.println("Enter the Product ID:");
                                            float id3=sc.nextFloat();
                                            A.discount_product(id3);
                                            break;

                                        case 6:
                                            System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for");
                                            System.out.println("Enter the first Product ID:");
                                            float id4=sc.nextFloat();
                                            sc.nextLine();
                                            System.out.println("Enter the second product ID:");
                                            float id5=sc.nextFloat();
                                            sc.nextLine();
                                            System.out.println("Enter the combined price(Should be less than their combined price):");
                                            System.out.println("Enter combined price for normal customer:");
                                            float price1=sc.nextFloat();
                                            sc.nextLine();
                                            System.out.println("Enter combined price for elite customer:");
                                            float price2=sc.nextFloat();
                                            sc.nextLine();
                                            System.out.println("Enter combined price for prime customer:");
                                            float price3=sc.nextFloat();
                                            sc.nextLine();
                                            A.giveaway_deals(id4,id5,price1,price2,price3);
                                            break;

                                        case 7:
                                            break l2;
                                    }
                                }

                            case 2:
                                Cust.product_catalog(A);
                                break;

                            case 3:
                                if(A.getAll_deals().size()==0)
                                {
                                    System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
                                }
                                else {
                                    for(Deal i: A.getAll_deals())
                                    {
                                        System.out.println("ID of Product 1: "+i.getId1());
                                        System.out.println("ID of Product 2: "+i.getId2());
                                        System.out.println("Combined Price for normal customer is: "+i.getN_price());
                                        System.out.println("Combined Price for elite customer is: "+i.getE_price());
                                        System.out.println("Combined Price for prime customer is: "+i.getP_price());
                                    }
                                }
                                break;

                            case 4:
                                while(true)
                                {
                                    System.out.println("1) Sign up");
                                    System.out.println("2) log in");
                                    System.out.println("3) Back");
                                    int choice3=sc.nextInt();
                                    sc.nextLine();
                                    l4: switch(choice3)
                                    {
                                        case 1:
                                            System.out.println("enter name:");
                                            String name=sc.nextLine();
                                            System.out.println("Enter password");
                                            String password=sc.nextLine();
                                            customer dummy=new customer();
                                            dummy.SignUp(name,password);
                                            registered_cust.add(dummy);
                                            System.out.println("customer successfully registered!!");
                                            break;

                                        case 2:
                                            System.out.println("Enter name:");
                                            String name1=sc.nextLine();
                                            System.out.println("Enter password:");
                                            String password1=sc.nextLine();
                                            boolean come=false;
                                            for(int i=0;i<registered_cust.size();i++)
                                            {
                                                come = registered_cust.get(i).LoginIn(registered_cust.get(i),name1,password1);
                                                if(come)
                                                {
                                                    while(true)
                                                    {
                                                        System.out.println("Welcome "+registered_cust.get(i).getName());
                                                        System.out.println("    1) Browse Products");
                                                        System.out.println("    2) Browse Deals");
                                                        System.out.println("    3) Add a Product to cart");
                                                        System.out.println("    4) Add Products in deal to Cart");
                                                        System.out.println("    5) View Coupons");
                                                        System.out.println("    6) Check Account Balance");
                                                        System.out.println("    7) View Cart");
                                                        System.out.println("    8) Empty Cart");
                                                        System.out.println("    9) Checkout Cart");
                                                        System.out.println("    10) Upgrade Customer Status");
                                                        System.out.println("    11) Add amount to Wallet");
                                                        System.out.println("    12) Back");
                                                        int choice4= sc.nextInt();
                                                        sc.nextLine();
                                                        l5: switch(choice4)
                                                        {
                                                            case 1:
                                                                int count3=1;
                                                                for(int k=0;k<A.getAll_category().size();k++)
                                                                {   int count4=1;
                                                                    System.out.println(count3+") Category ID and Name are: "+A.getAll_category().get(k).getC_ID()+" "+A.getAll_category().get(k).getC_name());
                                                                    for(int j=0;j<A.getAll_category().get(k).getAll_product().size();j++)
                                                                    {
                                                                        System.out.println("   "+count4+") Product ID: "+A.getAll_category().get(k).getAll_product().get(j).getP_ID());
                                                                        System.out.println("     Product Name: "+A.getAll_category().get(k).getAll_product().get(j).getP_name());
                                                                        System.out.println("     "+A.getAll_category().get(k).getAll_product().get(j).getOthers());
                                                                        System.out.println("     Price: "+A.getAll_category().get(k).getAll_product().get(j).getPrice());
                                                                        count4++;
                                                                    }
                                                                    count3++;
                                                                }
                                                                break l5;

                                                            case 2:
                                                                int count5=1;
                                                                for(Deal d:A.getAll_deals())
                                                                {
                                                                    System.out.println("Deal No.: "+count5);
                                                                    System.out.println("Id of Product 1: "+d.getId1());
                                                                    System.out.println("ID of Product 2: "+d.getId2());
                                                                    System.out.println("Combined deal price for normal customers: "+d.getN_price());
                                                                    System.out.println("Combined deal price for elite customers: "+d.getE_price());
                                                                    System.out.println("Combined deal price for prime customers: "+d.getP_price());
                                                                    System.out.println();
                                                                    count5++;
                                                                }
                                                                break l5;

                                                            case 3:
                                                                System.out.println("Enter Product ID: ");
                                                                float id=sc.nextFloat();
                                                                sc.nextLine();
                                                                System.out.println("Enter the Quantity: ");
                                                                int quantity=sc.nextInt();
                                                                sc.nextLine();
                                                                registered_cust.get(i).add_productToCart(id,quantity,A);
                                                                break l5;

                                                            case 4:
                                                                System.out.println("Enter ID of Product 1 in deal:");
                                                                float id2=sc.nextFloat();
                                                                sc.nextLine();
                                                                System.out.println("Enter ID of Product 2 in deal:");
                                                                float id3=sc.nextFloat();
                                                                sc.nextLine();
                                                                for(int j=0;j<A.getAll_deals().size();j++)
                                                                {
                                                                    if(A.getAll_deals().get(j).getId1()==id2 && A.getAll_deals().get(j).getId2()==id3)
                                                                    {
                                                                        registered_cust.get(i).setDeals(A.getAll_deals().get(j));
                                                                    }
                                                                }
                                                                break l5;

                                                            case 5:
                                                                int count8=1;
                                                                for(int j=0;j<registered_cust.get(i).getCoupons().size();j++)
                                                                {
                                                                    System.out.println(count8+") "+registered_cust.get(i).getCoupons().get(j)+"%");
                                                                }
                                                                break l5;

                                                            case 6:
                                                                double amount2=registered_cust.get(i).getWallet();
                                                                System.out.println("Current account balance is Rs."+amount2);
                                                                break l5;
                                                            case 7:
                                                                if(registered_cust.get(i).getAll_orders().size()==0 && registered_cust.get(i).getDeals().size()==0)
                                                                {
                                                                    System.out.println(registered_cust.get(i).getName()+" your cart is empty!!!!");
                                                                }
                                                                else {
                                                                    cart.View_cart(registered_cust.get(i));
                                                                }
                                                                break l5;

                                                            case 8:
                                                                cart.empty_cart(registered_cust.get(i));
                                                                break l5;

                                                            case 9:
                                                                cart.checkout_cart(registered_cust.get(i));
                                                                break l5;

                                                            case 10:
                                                                double amount1=registered_cust.get(i).getWallet();
                                                                customer new_customer=registered_cust.get(i).Upgrade_status(registered_cust.get(i),amount1);
                                                                registered_cust.remove(i);
                                                                registered_cust.add(i,new_customer);
                                                                break l5;

                                                            case 11:
                                                                System.out.println("Enter amount to add");
                                                                double amount=sc.nextFloat();
                                                                registered_cust.get(i).addWallet(amount);
                                                                System.out.println("Amount successfully added");
                                                                break l5;

                                                            case 12:
                                                                break l4;
                                                        }
                                                    }

                                                }
                                            }
                                            break;

                                        case 3:
                                            break l2;
                                    }
                                }
                            case 5:
                                break l1;
                        }
                    }

                case "Exit":
                    entry=false;
                    break;
            }
        }
    }

}