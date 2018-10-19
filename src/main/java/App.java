import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;
import spark.ModelAndView;
import static spark.debug.DebugScreen.enableDebugScreen;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.http.MetaData.Request;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        enableDebugScreen();

        get("/", (request, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.allStylist());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients/new",(request, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.allStylist());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylist", (request, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            String firstname = request.queryParams("firstname");
            String secondname = request.queryParams("secondname");
            String lastname = request.queryParams("lastname");
            Integer phonenumber = Integer.parseInt(request.queryParams("phonenumber"));
            Integer idnumber = Integer.parseInt(request.queryParams("idnumber"));
            String email = request.queryParams("email");
            Stylist newStylist = new Stylist(firstname, secondname, lastname, phonenumber, idnumber, email);
            newStylist.save();
            model.put("template", "templates/stylists-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/stylist", (reqeust, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.allStylist());
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/client", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams(":id")));
            String name = request.queryParams("name");
            Integer age = Integer.parseInt(request.queryParams("age"));
            String firstappearance = request.queryParams("firstappearance");
            String neighbourhood = request.queryParams("neighbourhood");
            Client newClient = new Client(name, age,firstappearance, neighbourhood, stylist.getId());
            newClient.save();
            model.put("stylist", stylist);
            model.put("template", "templates/client-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylist/:id", (request, response)->{
            Map<String, Object> model= new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("clients", Client.all());
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylist/:id/viewClients", (request, response)->{
            Map<String, Object> model= new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("clients", stylist.getClients());
            model.put("client", client);
            model.put("stylist", stylist);
            model.put("template", "templates/viewClients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Post method for deleting client
        post("/client/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            client.deleteClient();
            model.put("stylist", client);
            model.put("template", "templates/viewClients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}