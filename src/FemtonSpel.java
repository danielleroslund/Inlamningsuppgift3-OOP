import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FemtonSpel extends JFrame {

    private JPanel tilePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JButton newGameButton = new JButton("Nytt spel");
    private JButton winButton = new JButton("Klicka för vinst"); // Alla brickor läggs ut sorterade demonstrera vinst.
    private List<JButton> buttonsGame;

    public FemtonSpel(){
        // Lägg till panel,Layout, "Nytt spel" knapp och "Klicka för vinst" knapp
        tilePanel.setLayout(new GridLayout(4,4));
        bottomPanel.add(newGameButton);
        bottomPanel.add(winButton);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tilePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        newGame(); // Metod finns inte ännu, kommer finnas framöver
        newGameButton.addActionListener(new buttonListener()); // Metod finns inte ännu, kommer finnas framöver
        winButton.addActionListener(new buttonListener());
        add(mainPanel);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    // Lägg alla knappar i en lista, shuffla listan med Collections.shuflle();
    private void makeButtonList(){
        buttonsGame = new ArrayList<>();
        for(int i = 1; i < 16; i++){
            buttonsGame.add(new JButton(String.valueOf(i)));
        }
        buttonsGame.add(new JButton());
        Collections.shuffle(buttonsGame);
    }
    
    // printa ut meddelande vid vinst
    private void youWonOutPrint(){
        JOptionPane.showMessageDialog(null, "Grattis du vann!");
        /*System.exit(0);*/
    }
    //Alla brickor blandas slumpmässigt
    private void newGame(){
        makeButtonList();
        tilePanel.removeAll(); // Tar bort alla komponenter
        tilePanel.revalidate(); // placerar om
        for(JButton button : buttonsGame){
            tilePanel.add(button);
            button.addActionListener(new buttonListener());
        }
    }
    // Kontrollerar om den valda knappen kan byta plats med den tomma platsen
    private void checkIndex(JButton button){
    int index = buttonsGame.indexOf(button);
    if(index % 4 != 0){
        isEmpty(index -1, button);
    }
    if(index - 4 >= 0){
        isEmpty(index - 4, button);
    }
    if(index % 4 != 3){
        isEmpty(index + 1, button);
    }
    if(index + 4 < buttonsGame.size()){
        isEmpty(index + 4, button);
        }
    }
    
    //Kontrollera om platsen är tom, om den är det byt plats med den klickade knappen
    private void isEmpty(int empty, JButton button){
        JButton switchPlace = buttonsGame.get(empty);
        if(switchPlace.getText().equals("")){
            switchPlace.setText(button.getText());
            button.setText("");
        }
    }
     

    public static void main(String[] args){ FemtonSpel fs = new FemtonSpel();}
}
