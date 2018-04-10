import spark.Spark;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

class Controller {

    static void run(Mastermind game) {

        Spark.staticFiles.location("/public");

        before("/", (req, res) -> {
            if (req.protocol().equals("HTTP/1.1")) {
                res.redirect("https://www.java-mastermind.com");
            }
        });

        get("/", (req, res) -> {
            game.reset();
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        get("/solution", (req, res) -> game.getCode());

        post("/guess", (req, res) -> game.guess(req.queryParams("guess")) );

    }

    static void kill() {
        stop();
    }

}