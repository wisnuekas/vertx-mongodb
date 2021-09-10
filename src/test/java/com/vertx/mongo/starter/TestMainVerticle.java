package com.vertx.mongo.starter;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {
  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }

  // TODO: Complete testing

  @Test
  void can_create_product(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void cannot_create_product_with_not_valid_data(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void can_show_a_product(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void cannot_show_product_with_wrong_id(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void can_update_product(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void cannot_update_product_with_not_valid_data(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void cannot_update_product_with_wrong_id(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void can_delete_product(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void cannot_delete_product_with_wrong_id(Vertx vertx, VertxTestContext testContext) {
  }

  @Test
  void can_get_list_of_products(Vertx vertx, VertxTestContext testContext) {
  }
}
