package com.vertx.mongo.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.json.schema.SchemaParser;

public class MainVerticle extends AbstractVerticle {

  private MongoClient mongo;
  SchemaParser schemaParser;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    mongo = MongoClient.createShared(vertx, config());

    // Create a Router
    Router router = Router.router(vertx);

    //Allow to get the body of HTTP request
    router.route().handler(BodyHandler.create());

    // TODO: Create schema validation

    router.post("/api/products").handler(this::createProduct);

    router.get("/api/products/:id").handler(this::getProduct);

    router.put("/api/products/:id").handler(this::updateProduct);

    router.delete("/api/products/:id").handler(this::deleteProduct);

    router.get("/api/products").handler(this::listProducts);

    // Mount the handler for all incoming requests at every path and HTTP method
    router.route().handler(context -> {
      HttpServerResponse response = context.response();

      response.putHeader("content/type", "text/html").end("Welcome to Vert.x");
    });

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8888, http -> {
        if (http.succeeded()) {
          startPromise.complete();
          System.out.println("HTTP server started on port 8888");
        } else {
          startPromise.fail(http.cause());
        }
      });
  }

  private void createProduct(RoutingContext routingContext) {
    JsonObject document = routingContext.getBodyAsJson();

    mongo.insert("products", document, res -> {
      if (res.succeeded()) {
        String id = res.result();
        routingContext.response().putHeader("content-type", "application/json").setStatusCode(201)
          .end(
            Json.encode(new JsonObject()
              .put("status", "success")
              .put("message", "Product successfully created. _id: " + id)
            ));
      } else {
        res.cause().printStackTrace();
        routingContext.response().putHeader("content-type", "application/json")
          .setStatusCode(500)
          .end("Oppsy");
      }
    });
  }

  private void getProduct(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", id);
    JsonObject fields = new JsonObject();

    mongo.findOne("products", query, fields, res -> {
      if (res.succeeded()) {
        if (res.result() == null) {
          routingContext.response().setStatusCode(404).putHeader("content-type", "application/json")
            .end(Json.encode(new JsonObject()
              .put("status", "failed")
              .put("message", "Not found")
            ));
        }
        routingContext.response().putHeader("content-type", "application/json")
          .end(Json.encode(new JsonObject()
            .put("status", "success")
            .put("message", "Successfully get documents")
            .put("data", res.result())
          ));
      } else {
        res.cause().printStackTrace();
        routingContext.response().putHeader("content-type", "application/json")
          .setStatusCode(500)
          .end("Oppsy");
      }
    });
  }

  private void listProducts(RoutingContext routingContext) {
    JsonObject query = new JsonObject();

    mongo.find("products", query, res -> {
      if (res.succeeded()) {
        routingContext.response().putHeader("content-type", "application/json")
          .end(Json.encode(new JsonObject()
            .put("status", "success")
            .put("message", "Successfully get collections")
            .put("data", res.result())));
      } else {
        res.cause().printStackTrace();
        routingContext.response().putHeader("content-type", "application/json")
          .setStatusCode(500)
          .end("Oppsy");
      }
    });
  }

  private void deleteProduct(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", id);

    mongo.findOneAndDelete("products", query, res -> {
      if (res.succeeded()) {
        if (res.result() == null) {
          routingContext.response().setStatusCode(404).putHeader("content-type", "application/json")
            .end(Json.encode(new JsonObject()
              .put("status", "failed")
              .put("message", "Not found")
            ));
        }
        routingContext.response().putHeader("content-type", "application/json")
          .end(Json.encode(new JsonObject()
            .put("status", "success")
            .put("message", "Successfully remove document _id: " + id)));
      } else {
        res.cause().printStackTrace();
        routingContext.response().putHeader("content-type", "application/json")
          .setStatusCode(500)
          .end("Oppsy");
      }
    });
  }

  private void updateProduct(RoutingContext routingContext) {
    String id = routingContext.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", id);
    JsonObject document = routingContext.getBodyAsJson();

    mongo.findOneAndReplace("products", query, document, res -> {
      if (res.succeeded()) {
        if (res.result() == null) {
          routingContext.response().setStatusCode(404).putHeader("content-type", "application/json")
            .end(Json.encode(new JsonObject()
              .put("status", "failed")
              .put("message", "Not found")
            ));
        }
        routingContext.response().putHeader("content-type", "application/json")
          .end(Json.encode(new JsonObject()
            .put("status", "success")
            .put("message", "Successfully update document _id: " + id)));
      } else {
        res.cause().printStackTrace();
        routingContext.response().putHeader("content-type", "application/json")
          .setStatusCode(500)
          .end("Oppsy");
      }
    });
  }
}


