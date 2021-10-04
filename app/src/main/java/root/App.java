/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package root;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

import org.apache.logging.log4j.core.Logger;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public String getGreeting() {
        return "Hello World! That's Bil481 project";
    }

    public static void main(String[] args) {
        //Logger logger = LogManager.getLogManager(App.class);
        //logger.error("xxERRORxx");
        System.out.println(new App().getGreeting());
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Welcome to my Bil481 Project. /nPlease go to /compute.");

        get("/compute",
            (rq, rs) -> {
                Map<String, String> map = new HashMap<String, String>();
                map.put("result", "not computed yet!");
                return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine()
        )  ;

        post("/compute", 
            (req, res) -> {
            //System.out.println(req.queryParams("input1"));
            //System.out.println(req.queryParams("input2"));

            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList.add(value);
            }
            sc1.close();
            System.out.println(inputList);

            String input2 = req.queryParams("input2").replaceAll("\\s","");
            int input2AsInt = Integer.parseInt(input2);
            String input3 = req.queryParams("input2").replaceAll("\\s","");
            int input3AsInt = Integer.parseInt(input2);
            String input4 = req.queryParams("input2").replaceAll("\\s","");
            int input4AsInt = Integer.parseInt(input2);

            boolean result = App.search(inputList, input2AsInt,input3AsInt,input4AsInt);

            Map<String, Boolean> map = new HashMap<String, Boolean>();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
            }, 
            new MustacheTemplateEngine()
        );

    }

    public static boolean search(ArrayList <Integer> labs, int midterm, int fin, int proje){
        System.out.println("inside search");

        if(labs == null)return false;

        double sum=0;
        for(int element : labs){
            sum+=element;
        }
        double scoreFromLabs = sum/labs.size();

        double grade=scoreFromLabs + (midterm*3.0/10) + (fin*5.0/10) + (proje*2.0/10);

        if(grade >= 50)return true;
        return false;
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
        } return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
        }

}
