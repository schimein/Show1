import java.awt.*;
import java.awt.event.*;

abstract class MyShow extends Component {
  private int cor=0, cog=0 , cob=0; //konsist
  public int getCor() { return cor; }
  public int getCog() { return cog; }
  public int getCob() { return cob; }
  public void setCor(int xCor) { cor = xCor; }
  public void setCog(int xCog) { cog = xCog; }
  public void setCob(int xCob) { cob = xCob; }
  public int varCor(int xCor) { return cor += xCor; }
  public int varCog(int xCog) { return cog += xCog; }
  public int varCob(int xCob) { return cob += xCob; }
  abstract public void paint(Graphics g);
  //~ MyShow.fläche = null;
}

class Show1 extends MyShow {
  private int c, x1, y1; //+cob konsist
  public Show1() {c=1; x1=-1; y1=-1;}
  public Show1(int xX1, int xY1) {c=1; x1=xX1; y1=xY1;}
  public void paint(Graphics g) {
    if(getCob() >= 255)  c=-1; 
    if(getCob() <= 0)  c=1; 
    varCob(c); 
    int x = getSize().width, y = getSize().height; 
    if (x1>x || x1<0 || y1>y || y1<0) { x1=x/2; y1=y/2; } 
    g.setColor(new Color(getCor(), getCog(), getCob())); 
    g.drawLine(
      x1+=((int) Math.floor(Math.random() * 5)) - 2, 
      y1+=((int) Math.floor(Math.random() * 5)) - 2,
      x/2,
      y/2
    ); 
  }
}

class Show2 extends MyShow {
  private int c=1, i;
  public Show2() {c=1;}
  public void paint(Graphics g) {
    int x = getSize().width; int y = getSize().height;
    if(getCob() == 255)  c=-1;  
    if(getCob() == 0)  c=1; 
    varCob(c);
    if(++i >= x) i = 0;  
    g.setColor(new Color(getCor(), getCog(), getCob()));
    g.drawLine(i,0,x-i,y);
    g.drawLine(0,y-i,x,i); 
  }
}

class Show3 extends MyShow {
  private int c=1, i;
  public Show3() {c=1;}
  public void paint(Graphics g) {
    int x = getSize().width; int y = getSize().height;
    if(getCob() == 255)  c=-1;  
    if(getCob() == 0)  c=1; 
    varCob(c);
    if(++i >= x) i = 0; 
    g.setColor(new Color(getCor(), getCog(), getCob()));
    g.drawLine(i,0,x-i,y); 
    g.drawLine(x-i,0,i,y);
  }
}

class Show4 extends MyShow {
  private int c=1, i;
  public Show4() {c=1;}
  public void paint(Graphics g) {
    int x = getSize().width; int y = getSize().height;
    if(getCob() == 255)  c=-1;  
    if(getCob() == 0)  c=1; 
    varCob(c);
    if(++i >= x) i = 0;  
    g.setColor(new Color(getCor(), getCog(), getCob()));
    g.drawLine(i,0,x-i,y); 
    g.drawLine(0,y-i,x,i); 
    g.drawLine(x-i,0,i,y); 
    g.drawLine(0,i,x,y-i);
  }
}

class Show5 extends MyShow  {
  private int i;
  public Show5() {}
  public void paint(Graphics g) {
    int x = getSize().width;  int y = getSize().height;
    if(varCor(1) > 255) setCor((int) Math.floor(Math.random() * 256));
    if(varCog(1) > 255) setCog((int) Math.floor(Math.random() * 256));
    if(varCob(1) > 255) setCob((int) Math.floor(Math.random() * 256));
    if(++i > y) i = 0;
    g.setColor(new Color (getCor(), getCog(), getCob())); 
    g.drawLine(x-i,0,2*i,2*i);
    g.drawLine(2*i,2*i,0,x-i);
  }
}

class Show6 extends MyShow  {
  private int zir, zig, zib, i; //konsistent
  public Show6() {zir=255; zig=255; zib=255;}
  public void paint(Graphics g) {
    int x = getSize().width; int y = getSize().height;
    if(getCor() > zir) varCor(-1);  if(getCor() < zir) varCor(1); 
    if(getCog() > zig) varCog(-1);  if(getCog() < zig) varCog(1); 
    if(getCob() > zib) varCob(-1);  if(getCob() < zib) varCob(1); 
    if( getCor()==zir && getCog()==zig && getCob()==zib) {
      zir = (int)Math.floor(Math.random() * 256);
      zig = (int)Math.floor(Math.random() * 256);
      zib = (int)Math.floor(Math.random() * 256);
    }
    if(++i > y) i = 0;
    g.setColor(new Color (getCor(), getCog(), getCob())); 
    g.drawLine(x-i,0,2*i,2*i);
    g.drawLine(0,x-i,2*i,2*i);
  }
}

class Show_S extends Frame implements Runnable, ItemListener {
  private Thread prozess;//=null
  private MyShow flaeche;
  public Show_S() {
    flaeche = new Show1();
    setBackground(Color.BLACK);
    setVisible(true); 
    Choice S1 = new Choice();
      S1.addItem("1"); S1.addItem("2"); S1.addItem("3"); S1.addItem("4"); S1.addItem("5"); S1.addItem("6");
    //~ TextField status = new TextField("Anzahl ersetzbar!");
      //~ status.setForeground(new Color(255,200,0));  //Label
      //~ status.setFont(new Font("Arial", Font.BOLD, 16) ); //Textfeld
      //~ status.setBackground(Color.black);
      S1.addItemListener(this);
    Panel buttonPanel = new Panel();
      buttonPanel.add(S1);
    setLayout(new BorderLayout());
      add("Center", flaeche);
      add("South", buttonPanel);
    prozess = new Thread(this); prozess.start();
  }
  public void itemStateChanged (ItemEvent e) {  
    BorderLayout layout = (BorderLayout)getLayout();
    remove(layout.getLayoutComponent(BorderLayout.CENTER)); 
    Graphics g;
    flaeche = null;
    switch ((String) e.getItem())
    {
      case "2": flaeche = new Show2(); break;
      case "3": flaeche = new Show3(); break;
      case "4": flaeche = new Show4(); break;
      case "5": flaeche = new Show5(); break;
      case "6": flaeche = new Show6(); break;
      default: flaeche = new Show1();  break;
      //~ default: flaeche = new Show1(100, 100);  break;
    } 
    add("Center", flaeche);
    setVisible(true); //da Komponenten laufend geändert werden
  }
  public void update (Graphics g) { paint(g); } //erhält den Hintergrund
  public void run() {
    while (prozess != null) {
      try { repaint(); Thread.sleep(5); } 
      catch (InterruptedException e) { }
    }
  }
  static class DemoAdapter extends WindowAdapter {
    public void windowClosing (WindowEvent e) {System.exit(0); }
  }
  public static void main(String[] str) {
    Show_S f = new Show_S(); f.setSize(400, 454); f.addWindowListener(new DemoAdapter() );
    
    //{Struktogramm Methode paint() aus Show1
String µ=
" _______________________________________   \n"+
"| ` · .¸     getCob() >= 255     ¸. · ´ |  \n"+
"|   ja      ` · .¸  ?  ¸. · ´   nein    |  \n"+ 
"|___________________Y___________________|  \n"+
"| c = 1             |                   |  \n"+
"|___________________|___________________|  \n"+
"| ` · .¸     getCob() <= 0       ¸. · ´ |  \n"+
"|   ja      ` · .¸  ?  ¸. · ´   nein    |  \n"+ 
"|___________________Y___________________|  \n"+
"| c = -1            |                   |  \n"+
"|___________________|___________________|  \n"+
"| varCob(c) Farbe mit c variieren       |  \n"+
"|_______________________________________|  \n"+
"| x = getSize().width, Rahmenmaߠ       |  \n"+
"| y = getSize().height,                 |  \n"+
"|_______________________________________|  \n"+
"| ` · .¸(x1>x||x1<0||y1>y||y1<0) ¸. · ´ |  \n"+
"|   ja     ` · .¸   ?  ¸. · ´   nein    |  \n"+ 
"|___________________Y___________________|  \n"+
"| Startpunkt        |                   |  \n"+
"| x1 y1 in Mitte    |                   |  \n"+
"|___________________|___________________|  \n"+
"| Farbe festlegen                       |  \n"+
"|_______________________________________|  \n"+
"| Linie zeichnen Startpunkt bis Endpunkt|  \n"+
"|_______________________________________|  \n";
///~ System.out.println(µ);//}

//{Klassendiagramm
µ=
" _____________________________________________________   \n"+
"| MyShow {abstract}                                   |  \n"+
"|_____________________________________________________|  \n"+
"| [-] cor=0: int                                      |  \n"+
"| [-] cog=0: int                                      |  \n"+
"| [-] cob=0: int                                      |  \n"+
"|_____________________________________________________|  \n"+
"| (+) getCor(): int                                   |  \n"+
"| (+) getCog(): int                                   |  \n"+
"| (+) getCob(): int                                   |  \n"+
"| (+) setCor(xCor: int)                               |  \n"+
"| (+) setCog(xCog: int)                               |  \n"+
"| (+) setCob(xCob: int)                               |  \n"+
"| (+) varCor(xCor: int): int                          |  \n"+
"| (+) varCog(xCog: int): int                          |  \n"+
"| (+) varCob(xCob: int): int                          |  \n"+
"| (+) paint(g: Graphics) {abstract}                   |  \n"+
"|_____________________________________________________|  \n"+
"             ^                           ^               \n"+
" ____________|____________   ____________|____________   \n"+
"| Show1                   | | Show2                   |  \n"+
"|_________________________| |_________________________|  \n"+
"| [-] c: int              | | [-] c: int              |  \n"+
"| [-] x1: int             | |                         |  \n"+
"| [-] y1: int             | |                         |  \n"+
"|_________________________| |_________________________|  \n"+
"| (c) Show1()             | | (c) Show2()             |  \n"+
"| (+) paint(g: Graphics)  | | (+) paint(g: Graphics)  |  \n"+
"|_________________________| |_________________________|  \n"+
"            <>1..*                      <>1..*           \n"+
" ____________|1__________________________|1___________   \n"+
"| Show_S                                              |  \n"+
"|_____________________________________________________|  \n"+
"| [-] prozess: Thread                                 |  \n"+
"| [-] flaeche: MyShow                                 |  \n"+
"| [-] flaeche2: MyShow                                |  \n"+
"|_____________________________________________________|  \n"+
"| (c) Show_S                                          |  \n"+
"| (+) itemStateChanged (e: ItemEvent)                 |  \n"+
"| (+) update (g: Graphics)                            |  \n"+
"| (+) run()                                           |  \n"+
"| (+) main(str[]: String                              |  \n"+
"|_____________________________________________________|  \n";
System.out.println(µ);//}    
    
    
  }
}

