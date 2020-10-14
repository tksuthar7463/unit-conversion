//Import all the required packages & libraries
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/*<applet code="UnitConversion.class" width=260 height=200></applet>*/

public class UnitConversion extends Applet implements ActionListener,ItemListener,Runnable{

  
		//created a thread and movable text on the window.....
      String msg=" Created by Tarun Kumar. ";
      char ch;
      boolean  stopFlag= true;
      Thread t= null;
      int x=20;
      public void start(){
          t = new Thread(this);
          stopFlag=false;
          t.start();
       }

	
       public void run(){
             for(;;){
                 try{
                repaint();
                Thread.sleep(500);
                ch = msg.charAt(0);
                msg = msg.substring(1,msg.length());
                msg = msg + ch;
                if(stopFlag)
                     break;
                }catch(InterruptedException e) {}
            }
        }

       public void stop(){
           stopFlag=true;
           t = null;
         }

         public void paint(Graphics g){
              g.drawString(msg,x,180);
            	x=x-5;
              if(x>0){
                x=20;
              }
        }

       // end of movable text threading part


	
	//reference var of class Choice
	Choice unitList=null;
	Choice unitList1=null;

	//referance var for font
	Font f1=null;

    //taking input from the user
	TextField t1=new TextField(20);
	Label l=new Label("Area Unit Convertor");
	//display result after unit conversion
	TextField t2=new TextField(20);
	//command to convert unit of area
	Button b1=new Button("Convert");
        
    public void init(){
	f1 = new Font("Arial",Font.BOLD,12);
    setBackground(Color.YELLOW);
	add(l);
	add(t1);

	unitList = new Choice();
	unitList1 = new Choice();
		 
    //add items to the choice
    unitList.add("m2");
    unitList.add("ft2");
    unitList.add("cm2");
	add(unitList);
    //add item listener
    unitList.addItemListener(this);

	add(t2);

	//add items to the choice
    unitList1.add("m2");
    unitList1.add("ft2");
    unitList1.add("cm2");
	add(unitList1);
    //add item listener
    unitList1.addItemListener(this);

	add(b1);
    b1.addActionListener(this);

    }
    public void itemStateChanged(ItemEvent arg0) {
		 // repaint(); 
	 }

    public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			
			float n1=Float.parseFloat(t1.getText());
			double convert_value=n1;

			if(unitList.getSelectedItem()!=unitList1.getSelectedItem()){
				//convert from meter squre to centimeter squre 
				if(unitList.getSelectedItem()=="m2" && unitList1.getSelectedItem()=="cm2"){
					convert_value=n1*10000;
				}
				//convert from meter squre to foot squre 
				if(unitList.getSelectedItem()=="m2" && unitList1.getSelectedItem()=="ft2"){
					convert_value=n1*(10.7639);
				}
				//convert from centimeter squre to meter squre 
				if(unitList.getSelectedItem()=="cm2" && unitList1.getSelectedItem()=="m2"){
					convert_value=n1/10000;
				}
				//convert from centimeter squre to foot squre 
				if(unitList.getSelectedItem()=="cm2" && unitList1.getSelectedItem()=="ft2"){
					convert_value=n1*(0.00107639);
				}
				//convert from foot squre to centimeter squre 
				if(unitList.getSelectedItem()=="ft2" && unitList1.getSelectedItem()=="cm2"){
					convert_value=n1*(929.03);
					
				}
				//convert from foot squre to meter squre 
				if(unitList.getSelectedItem()=="ft2" && unitList1.getSelectedItem()=="m2"){
					convert_value=n1*(0.092903);
					
				}														
			}
			else{
				convert_value=n1;
			}
			t2.setText(" "+convert_value);
			
		}
	}
}
