/**
 * This class contains integration tests for the Product API endpoints.
 * Test methods are ordered with '@Order' for study purposes only. In real scenarios,
 * each test should be independent to ensure reliable and reproducible testing.
 * 
 * Esta classe contém testes de integração para os endpoints da API de Produtos.
 * Os métodos de teste estão ordenados com '@Order' apenas para fins de estudo. Em cenários reais,
 * cada teste deve ser independente para garantir a confiabilidade e correta execução.
 * 
 * @author [João Pedro Nolasco]
 */

package com.example.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ProductRestTest extends FunctionalTest {
	
	/*
	@Test
	public void verifyProductsEndpointReturns200() {
		given()
			.when()
				.get("/products")
			.then()
				.statusCode(200);
	}
	*/
	
	@Order(1)
	@Test
	public void verifyProductsEndpointReturns200() {
	    int statusCode = given()
	                        .when()
	                        	.get("/products")
	                        .getStatusCode();
	    
	    assertEquals(200, statusCode, "Status code is not 200");
	}
	
	@Order(2)
	@Test
	public void invalidProductId() {
		given()
			.when()
				.get("/products/10")
			.then()
				.statusCode(404);
	}
	
	@Order(3)
	@Test
	public void verifySpecificProductNamesInResponse() {
		given()
			.when()
				.get("/products")
			.then()
				.body("name",hasItems("TV", "Smartphone", "Computer", "Laptop", "Tablet"));
	}
	
	@Order(4)
	@Test
	public void verifyCompleteProductsResponseMatchesExpectedJSON() {
		given()
			.when()
				.get("/products")
			.then()
				.body(equalTo("[{\"id\":1,\"name\":\"TV\",\"price\":19.99,\"quantity\":100},{\"id\":2,\"name\":\"Smartphone\",\"price\":29.99,\"quantity\":50},{\"id\":3,\"name\":\"Computer\",\"price\":9.99,\"quantity\":200},{\"id\":4,\"name\":\"Laptop\",\"price\":49.99,\"quantity\":75},{\"id\":5,\"name\":\"Tablet\",\"price\":14.99,\"quantity\":150}]"));
	}
	
	@Order(5)
	@Test
    public void updateProductWithPut() {
        String requestBody = "{\"name\": \"Gold\", \"price\": 10000.99, \"quantity\": 1}";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .put("products" + "/1") // Assuming updating product with ID 1
        .then()
            .statusCode(200); // Assuming 200 OK is returned
    }
	
	@Order(6)
	@Test
    public void addNewProductWithPost() {
        String requestBody = "{\"name\": \"Iphone Y\", \"price\": 99.99, \"quantity\": 100}";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(200); 
    }
	
	@Order(7)
	@Test
    public void deleteProduct() {
        given()
            .when()
                .delete("products" + "/5")
            .then()
                .statusCode(200)
                .extract().asString();

        String expectedMessage = "Product deleted successfully";
        assertEquals(expectedMessage, "Product deleted successfully", "Message does not match");
    }

}
