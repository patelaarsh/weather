/*11/21/19
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aarsh_kelsey;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Aarsh
 */
    
   /*Kelsey 11/19/19
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//imports for java fx user interface
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;

//imports for jar file and extracting weather data
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;



/**
 *
 * @author Kelsey
 */
    public class AARSH_KELSEY extends Application {

    
public Button waterButton;
public Button waterButton2;
public Button waterButton3;
public Label CityLabel;
public Label WeatherLabel;
public Label CountryLabel;
public Label WeatherDescriptionLabel;
public Label TemperatureLabel;
public Label roseLabel;
public Label marigoldLabel;
public Label petuniaLabel;

  public static Map<String,Object> jsonToMap(String str){

        Map<String,Object> map = new Gson().fromJson(str,new TypeToken<HashMap<String,Object>> () {}.getType());
        return map;
    }
     
        
    public static void main(String[] args) 
    {
       
           
        //Launch the application
        launch(args);
    
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        
          String API_KEY = "14bc3c13c84f557708d9ecc439e10171";
          String LOCATION = "los+angeles,us";
          String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";                         
          //if there is data missing what does it do
          //fake gson files
          //not interact with the code, separate test files
          try{
              StringBuilder result = new StringBuilder();
              URL url = new URL(urlString);
              URLConnection conn = url.openConnection();
              try (BufferedReader rd = new BufferedReader(new InputStreamReader (conn.getInputStream()))) {
                  String line;
                  while ((line = rd.readLine()) != null){
                      result.append(line);
                  }
              }
              
          Map<String, Object > respMap = jsonToMap (result.toString());
          Map<String, Object > mainMap = (Map<String, Object >)respMap.get("main");
          Map<String, Object > sysMap = (Map<String, Object >)respMap.get("sys");
          String  nameMap = (String) respMap.get("name");
          List<Map<String, Object >> weather = (List<Map<String, Object>>) (respMap.get("weather"));
          Map<String, Object> weatherMap = weather.get(0);
          
         WeatherLabel = new Label();
         CityLabel = new Label();
         CountryLabel = new Label();
         WeatherDescriptionLabel= new Label();
         TemperatureLabel = new Label();
         
         
         CityLabel.setText("City: " + nameMap); 
         CountryLabel.setText("Country: " + sysMap.get("country"));         
         WeatherDescriptionLabel.setText("Description: "+ weatherMap.get("main"));          
         TemperatureLabel.setText("Current Temperature: " + mainMap.get("temp") +" F" );
         
        
        roseLabel = new Label();
        marigoldLabel = new Label();
        petuniaLabel = new Label();
       
          String plantline;
          try (Stream<String> lines = Files.lines(Paths.get("Plants.txt"))) {
          roseLabel.setText(plantline = lines.skip(0).findFirst().get());
         }
          
        String plantline2;
        try (Stream<String> lines = Files.lines(Paths.get("Plants.txt"))) {
        marigoldLabel.setText(plantline2 = lines.skip(1).findFirst().get());
        }
        
        String plantline3;
        try (Stream<String> lines = Files.lines(Paths.get("Plants.txt"))) {
        petuniaLabel.setText(plantline3 = lines.skip(2).findFirst().get());
        }          
       
     
     
        Object description =  weatherMap.get("main");
        roseLabel.setStyle("-fx-font-size:24pt");
        marigoldLabel.setStyle("-fx-font-size:24pt");
        petuniaLabel.setStyle("-fx-font-size:24pt");
       
        int k;
        
        if (description.equals("Thunderstorm") || description.equals("Snow")||description.equals("Rain") || description.equals("Drizzle") || description.equals("Clear") ){
            k=1;
        }            
        else{
            k=0;
        }
          
        
     /*  File file = new File("count.txt");
        Scanner scanner = new Scanner(file);
        int count =0;
        while(scanner.hasNextInt()){
            count = scanner.nextInt();
       }*/
        //Create Button to complete watering
        waterButton = new Button("Water");
      
        waterButton.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerRose());
      
        //Create Button to complete watering
        waterButton2 = new Button("Water");
        waterButton2.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerMarigold());
        
        //Create Button to complete watering
        waterButton3 = new Button("Water");
        waterButton3.setOnAction((EventHandler<ActionEvent>) new WaterButtonHandlerPetunia());
          
        
       //weather conditions require water
       if (k == 0){
                           
        //rose
        //System.out.println( plantline + " : Time to Water");
       waterButton.setStyle("-fx-base: green");
       //marigold
       //System.out.println( plantline2 + " : Time to Water");
       waterButton2.setStyle("-fx-base: green");
       //petunia
       //System.out.println( plantline3 + " : Time to Water");
       waterButton3.setStyle("-fx-base: green");
        }
     
     else {
    
    //rain
    //System.out.println("Do not need to water any plants today due to weather conditions");
         waterButton.setStyle("-fx-base: #ff0000; ");
        waterButton2.setStyle("-fx-base: #ff0000; ");
        waterButton3.setStyle("-fx-base: #ff0000; ");
    System.exit(0);
    }
          }catch (IOException e){
              System.out.println(e.getMessage());
          }
             
          
         /* BufferedWriter out = null;
    try {

        BufferedReader br = new BufferedReader(new FileReader("count.txt"));
        String storedScore="0";
        int count = 0;
        while ((storedScore = br.readLine()) != null) {
            count=(Integer.parseInt(storedScore==null?"0":storedScore));
        }
        out = new BufferedWriter(new FileWriter("count.txt", false));
        out.write(String.valueOf(count+1));

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                
                e.printStackTrace();
            }
         }
    }*/
   


//Constants for the ListView dimensions
        final double Width = 1100.0, Height = 300.0;
        final double FONT_SIZE = 20; 
        final double FONT = 50;
       
        
        
        //Create Flower Image objects
        Image image1 = new Image("file:rose.jpg");
        Image image2 = new Image("file:marigold.jpg");
        Image image3 = new Image("file:petunia.jpg");
        

        
        //Create Flower ImageView objects
        ImageView RoseView = new ImageView(image1);
        ImageView MarigoldView = new ImageView(image2);
        ImageView PetuniaView = new ImageView(image3);

        
        //Resize flower images
        RoseView.setFitWidth(200);
        RoseView.setPreserveRatio(true);
        
        MarigoldView.setFitWidth(200);
        MarigoldView.setPreserveRatio(true);
        
        PetuniaView.setFitWidth(200);
        PetuniaView.setPreserveRatio(true);
        
        HBox hbox = new HBox(10, RoseView, MarigoldView,PetuniaView);
        hbox.setPadding(new Insets(10));
        
        
        //Labels
        
        
     
      
      
     
        HBox hbox2 = new HBox(10,roseLabel,marigoldLabel,petuniaLabel);
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        
        hbox2.setSpacing(120);
        
        Label weatherdataLabel = new Label("Weather Data");
        
        HBox hbox4 = new HBox(10, CityLabel, CountryLabel);
        hbox4.setAlignment(Pos.CENTER);
        //Set title of site with visual effects
        InnerShadow IS = new InnerShadow();
        Text welcomeText = new Text("Plant Watering");
        welcomeText.setEffect(IS);
        welcomeText.setFont(Font.font("Georgia", FontWeight.BOLD, FONT));
        welcomeText.setFill(Color.CORNFLOWERBLUE);



        //Create hboxes to display labels and comboboxes
        HBox welcomeBox = new HBox(10, welcomeText);
        
       
        welcomeBox.setAlignment(Pos.CENTER);
       
        Label waterLabel = new Label("Click the Water button under each plant if"
                + " it is highlighted green to signify that you have watered it.\n If it is red"
                + ", you do not have to water the plant. ");
        
        //add back
        
        
        GridPane gridpane = new GridPane();
        gridpane.add(waterButton,5,1);
        gridpane.add(waterButton2, 21, 1);
        gridpane.add(waterButton3,36,1);
        gridpane.setVgap(5);
        gridpane.setHgap(10);
        gridpane.setPadding(new Insets(20));
        
        waterLabel.setAlignment(Pos.CENTER);
        waterLabel.setTextAlignment( CENTER );  
        waterLabel.setWrapText(true);
        waterLabel.setPadding(new Insets(10));
        
        
        
        //Add controls to VBox
        VBox vbox1 = new VBox(10, welcomeBox, hbox,hbox2,gridpane, waterLabel, weatherdataLabel, hbox4, 
         WeatherDescriptionLabel, TemperatureLabel);
               
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(10));
       
        
        //Create a Scene and display it
        Scene scene9 = new Scene(vbox1);
        primaryStage.setScene(scene9);
        
        //Set the stage title
        primaryStage.setTitle("Fresno Plant Watering Assitant");
        
         //ListView of VBox
        ListView<VBox> listView1 = new ListView<>();
        listView1.setPrefSize(Width, Height);
        
        listView1.setOrientation(Orientation.VERTICAL);
        listView1.getItems().addAll(vbox1);
        //Stack Pane for VBox
        StackPane root = new StackPane();//(*fix me*) figure out why stackpane isn't working*)
        //when expand screen everything moves around??
        root.getChildren().add(listView1);
        primaryStage.setScene(new Scene(root, 670, 500));
         root.setStyle("-fx-control-inner-background: green");
         
        
        //Show the window
        primaryStage.show();        
    }
    
    class WaterButtonHandlerRose implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event)
        {   //fix once add aarsh's code maybe as an extra class??
        
               waterButton.setStyle("-fx-background-color: #ff0000; ");
           
}
}
     class WaterButtonHandlerMarigold implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event)
        {   
            
           
               waterButton2.setStyle("-fx-background-color: #ff0000; ");
           
}
}
     
     
   class WaterButtonHandlerPetunia implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event)
        {   
            
           
               waterButton3.setStyle("-fx-background-color: #ff0000; ");
           
}
}   
    
    
}


